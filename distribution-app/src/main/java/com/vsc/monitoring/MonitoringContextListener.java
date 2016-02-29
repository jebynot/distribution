package com.vsc.monitoring;

import com.orbitz.monitoring.api.Decomposer;
import com.orbitz.monitoring.api.MonitorProcessorFactory;
import com.orbitz.monitoring.api.MonitoringEngine;
import com.orbitz.monitoring.lib.decomposer.AttributeDecomposer;
import com.orbitz.monitoring.lib.factory.ProcessGroup;
import com.orbitz.monitoring.lib.factory.SimpleMonitorProcessorFactory;
import com.orbitz.monitoring.lib.timertask.VMStatTimerTask;
import com.vsc.monitoring.processors.HostedGraphiteProcessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by jebynot on 1/26/16.
 */
public class MonitoringContextListener implements ServletContextListener {
    MonitoringEngineManager monitoringEngineManager ;



    public void contextInitialized(ServletContextEvent contextEvent) {


        System.out.print("Initializing monitoring processors");
        ProcessGroup processGroup = new ProcessGroup(new HostedGraphiteProcessor());
        ProcessGroup[] processGroups = {processGroup};
        Decomposer decomposer = new AttributeDecomposer();

        MonitorProcessorFactory monitorProcessorFactory = new SimpleMonitorProcessorFactory(processGroups);

        VMStatTimerTask vmStatTimerTask = new VMStatTimerTask();
        List<TimerTask> timerTaskList = new ArrayList<>();
        timerTaskList.add(vmStatTimerTask);

        monitoringEngineManager = new MonitoringEngineManager(monitorProcessorFactory, decomposer);
        monitoringEngineManager.setTimerTasks(timerTaskList);
        monitoringEngineManager.startup();
        System.out.print("Completed monitoring processors initialization");
    }

    public void contextDestroyed(ServletContextEvent var1) {

        monitoringEngineManager.shutdown();
    }
}
