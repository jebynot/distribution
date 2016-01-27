package com.vsc.monitoring.processors;

import com.orbitz.monitoring.api.Monitor;
import com.orbitz.monitoring.lib.processor.MonitorProcessorAdapter;
import org.apache.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
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
    private static String API_KEY  = "3845911e-ab82-4a80-8195-7f40361920f8";
    private static String CARBON_END_POINT = "aa478349.carbon.hostedgraphite.com";
    private static int CARBON_PORT = 2003;
    private static ConcurrentMap<String, AtomicInteger> counts = new ConcurrentHashMap<String, AtomicInteger>();
    private CounterTask counterTask;


    public HostedGraphiteProcessor() {

        CounterTask counterTask = new CounterTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(counterTask, 0, 60 * 1000);
    }

    @Override
    public void process(Monitor monitor) {

        try {
            if (monitor.hasAttribute("latency")) {
                sendResponseTimeMetric(monitor);
            } else {
                sendEventMetric(monitor);
            }
        } catch (Exception e) {
            //Ignoring the error, this requsest/txn shouldnt fail because we cannot monitor it.
            logger.debug("Error sending metrics to Hosted Graphite : " + monitor, e);
        }


    }

    private void sendResponseTimeMetric(Monitor monitor) {


        Map<String, Long> metrics = new HashMap<>();
        String monitorName = getMonitorName(monitor);
        long latency = monitor.getAsLong("latency");
        metrics.put(monitorName + ".latency", new Long(latency));
        addCountMetrics(monitorName);
        if (monitor.getAsBoolean("failed")) {
            addCountMetrics(monitorName + ".failed");
        } else {
            addCountMetrics(monitorName + ".success");
        }
        sendMetricsToGraphite(metrics);
    }

    private void sendEventMetric(Monitor monitor) {
        String monitorName = getMonitorName(monitor);
        addCountMetrics(monitorName);
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
        if  (monitor.hasAttribute("system")) {
            monitorName.append(monitor.get("system").toString()).append(PERIOD).append(monitor.get(Monitor.NAME));
        } else {
            monitorName.append(monitor.get(Monitor.NAME));
        }
        return monitorName.toString();
    }

    private void sendMetricsToGraphite(Map<String, Long> metrics) {

        StringBuilder messages = new StringBuilder();
        for (String metricName : metrics.keySet()) {
            messages.append(API_KEY);
            messages.append(PERIOD);
            messages.append(metricName);
            messages.append(" ");
            messages.append(metrics.get(metricName).longValue());
            messages.append("\n");
        }
        System.out.println(messages.toString());
        DatagramSocket sock   = null;
        try {
            Socket conn          = new Socket(CARBON_END_POINT, CARBON_PORT);
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(messages.toString());
            conn.close();
        } catch (IOException e) {
            logger.debug(e);
        } finally {
            if (sock != null) {
                sock.close();
            }

        }
    }

    private class CounterTask extends TimerTask {
        @Override
        public void run() {

            StringBuilder messages = new StringBuilder();
            for (String metricName : counts.keySet()) {
                AtomicInteger count = counts.get(metricName);
                    messages.append(API_KEY);
                    messages.append(PERIOD);
                    messages.append(metricName);
                    messages.append(" ");
                    messages.append(count.longValue());
                    messages.append("\n");
                    counts.put(metricName, new AtomicInteger(0));
            }

            System.out.println(messages.toString());
            DatagramSocket sock   = null;
            try {
                Socket conn          = new Socket(CARBON_END_POINT, CARBON_PORT);
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(messages.toString());
                conn.close();
            } catch (IOException e) {
                logger.debug(e);
            } finally {
                if (sock != null) {
                    sock.close();
                }

            }

        }

        public void shutdown() {
            counterTask.cancel();
        }
    }
}
