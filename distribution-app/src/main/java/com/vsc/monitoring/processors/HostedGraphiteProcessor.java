package com.vsc.monitoring.processors;

import com.orbitz.monitoring.api.Monitor;
import com.orbitz.monitoring.lib.processor.MonitorProcessorAdapter;
import org.apache.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jebynot on 1/26/16.
 */
public class HostedGraphiteProcessor  extends MonitorProcessorAdapter {
    private static final Logger logger = Logger.getLogger(HostedGraphiteProcessor.class.getName());
    private static String PERIOD = ".";
    private static String COUNT = "count";
    private static String LATENCY = "latency";
    private static String FAILED = "failed";
    private static String SUCCESS = "success";
    private final static String JVM_STATS = "JvmStats";
    private static String API_KEY  = "86a20554-95ba-4b77-bcc3-0e45021e4fd5";
    private static String CARBON_END_POINT = "d9daaa93.carbon.hostedgraphite.com";
    private static int CARBON_PORT = 2003;
    private static String HOST = "VACAYSTAY";
    private static ConcurrentMap<String, AtomicInteger> counts = new ConcurrentHashMap<String, AtomicInteger>();
    private CounterTask counterTask;
    private ThreadPoolExecutor threadPoolExecutor;
    private boolean monitoringEnabled = false;
    private String apiKey;
    private String carbonEndPoint;
    private int port;
    private String environment;
    private String platform;


    public HostedGraphiteProcessor() {
        try {
            apiKey = System.getProperty("api.key");
            carbonEndPoint = System.getProperty("carbon.endpoint");
            port = Integer.parseInt(System.getProperty("carbon.port"));
            environment = System.getProperty("environment");
            platform = System.getProperty("platform");

            System.out.println(apiKey + " " + carbonEndPoint + " " + port);
            if (apiKey == null || carbonEndPoint ==  null ||  environment == null || platform == null) {
                monitoringEnabled = false;
            } else {
                monitoringEnabled = true;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        threadPoolExecutor = createThreadPoolExecutor();
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

    @Override
    public void process(Monitor monitor) {

        if (monitoringEnabled) {
            try {
                if (monitor.hasAttribute(LATENCY)) {
                    sendResponseTimeMetric(monitor);
                } else if (JVM_STATS.equals(monitor.get(Monitor.NAME))) {
                    fireJvmMetrics(monitor);
                } else {
                    sendEventMetric(monitor);
                }
            } catch (Exception e) {
                //Ignoring the error, this requsest/txn shouldnt fail because we cannot monitor it.
                logger.debug("Error sending metrics to Hosted Graphite : " + monitor, e);
            }
        }

    }

    public void fireJvmMetrics(Monitor monitor) {
        String monitorPrefix =  monitor.getAsString(Monitor.NAME);
        String type = monitor.getAsString("type");
        type = type.replaceAll("\\s+","");
        StringBuilder messages = new StringBuilder();
        if (monitor.hasAttribute("percent")) {
            Double percent = monitor.getAsDouble("percent");
            messages.append(apiKey).append(PERIOD).append(environment).append(PERIOD).append(platform).append(PERIOD).append(monitorPrefix).append(PERIOD).append(type).append(PERIOD).append("percentage").append(" ").append(percent).append("\n");
        }
        if (monitor.hasAttribute("count")) {
            Long count = monitor.getAsLong("count");
            messages.append(apiKey).append(PERIOD).append(environment).append(PERIOD).append(platform).append(PERIOD).append(monitorPrefix).append(PERIOD).append(type).append(PERIOD).append("count").append(" ").append(count).append("\n");
        }
        if (monitor.hasAttribute("time")) {
            Long time = monitor.getAsLong("time");
            messages.append(apiKey).append(PERIOD).append(environment).append(PERIOD).append(platform).append(PERIOD).append(monitorPrefix).append(PERIOD).append(type).append(PERIOD).append("time").append(" ").append(time).append("\n");

        }
        sendMetricsToHostedGraphite(messages);
    }

    private void sendResponseTimeMetric(Monitor monitor) {

        Map<String, Long> metrics = new HashMap<>();
        String monitorName = getMonitorName(monitor);
        monitorName = monitorName.replaceAll("/",".");
        long latency = monitor.getAsLong(LATENCY);
        metrics.put(monitorName + PERIOD + LATENCY, new Long(latency));
        metrics.put(monitorName + PERIOD + COUNT, new Long(1));
        if (monitor.getAsBoolean(FAILED)) {
            metrics.put(monitorName + PERIOD + FAILED + PERIOD + COUNT, new Long(1));
        } else {
            metrics.put(monitorName + PERIOD + SUCCESS + PERIOD + COUNT, new Long(1));
        }
        sendMetricsToGraphite(metrics);
    }

    private void sendEventMetric(Monitor monitor) {
        String monitorName = getMonitorName(monitor);
        Map<String, Long> metrics = new HashMap<>();
        metrics.put(monitorName + PERIOD + COUNT, new Long(1));
        sendMetricsToGraphite(metrics);
    }

    private void addCountMetrics(String metricsName) {
        metricsName = metricsName + ".count";
        if(counts.containsKey(metricsName) ) {
            counts.get(metricsName).incrementAndGet();
        } else {
            counts.put(metricsName, new AtomicInteger(1));
        }
    }

    private String getMonitorName(Monitor monitor) {
        StringBuilder monitorName = new StringBuilder();
        if  (monitor.hasAttribute("partner")) {
            String system = monitor.getAsString("partner");
            system = system.replaceAll("\\.","");
            monitorName.append(monitor.get("partner").toString()).append(PERIOD).append(monitor.get(Monitor.NAME));
        } else {
            monitorName.append(monitor.get(Monitor.NAME));
        }
        return monitorName.toString();
    }

    private void sendMetricsToGraphite(Map<String, Long> metrics) {

        StringBuilder messages = new StringBuilder();
        for (String metricName : metrics.keySet()) {
            messages.append(apiKey);
            messages.append(PERIOD);
            messages.append(environment);
            messages.append(PERIOD);
            messages.append(platform);
            messages.append(PERIOD);
            messages.append(metricName);
            messages.append(" ");
            messages.append(metrics.get(metricName).longValue());
            messages.append("\n");
        }
        sendMetricsToHostedGraphite(messages);
    }

    public void sendMetricsToHostedGraphite(StringBuilder messages)  {
        threadPoolExecutor.execute(new HostedGraphiteRunnable(messages.toString()));
    }

    private ThreadPoolExecutor createThreadPoolExecutor() {
        BlockingQueue enqueueBuffer = new ArrayBlockingQueue(1000);

        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            private static final long MIN_LOG_WAIT_MILLIS = 60000;
            private long lastLogEventTime = 0;

            public void rejectedExecution(Runnable runnable,
                                          ThreadPoolExecutor threadPoolExecutor) {
                boolean shouldLog = false;
                long currentTime = System.currentTimeMillis();

                synchronized (this) {
                    if ((currentTime - lastLogEventTime) > MIN_LOG_WAIT_MILLIS) {
                        lastLogEventTime = currentTime;
                        shouldLog = true;
                    }
                }

                if (shouldLog) {
                    logger.warn("Rejected erma monitor due to full event queue.");
                }
            }
        };

        // create a thread pool of size 1 with a bounded buffer
        return new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, enqueueBuffer,
                rejectedExecutionHandler);
    }

    protected class HostedGraphiteRunnable implements Runnable {

        String metrics;

        public HostedGraphiteRunnable(String metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            Socket conn  = null;
            try {
                conn          = new Socket(carbonEndPoint, port);
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(metrics);
                dos.close();
            } catch (IOException e) {
                logger.debug(e);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (IOException e) {
                        logger.debug(e);
                    }
                }
            }

        }
    }

    private class CounterTask extends TimerTask {
        @Override
        public void run() {

            StringBuilder messages = new StringBuilder();
            for (String metricName : counts.keySet()) {
                AtomicInteger count = counts.get(metricName);
                messages.append(apiKey);
                messages.append(PERIOD);
                messages.append(metricName);
                messages.append(" ");
                messages.append(count.longValue());
                messages.append("\n");
                counts.remove(metricName);
            }

            System.out.println(messages.toString());
            sendMetricsToHostedGraphite(messages);

        }

        public void shutdown() {
            counterTask.cancel();
        }
    }
}
