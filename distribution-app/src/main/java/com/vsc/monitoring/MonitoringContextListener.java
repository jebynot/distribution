package com.vsc.monitoring;

import com.orbitz.monitoring.api.Decomposer;
import com.orbitz.monitoring.api.MonitorProcessorFactory;
import com.orbitz.monitoring.api.MonitoringEngine;
import com.orbitz.monitoring.lib.decomposer.AttributeDecomposer;
import com.orbitz.monitoring.lib.factory.ProcessGroup;
import com.orbitz.monitoring.lib.factory.SimpleMonitorProcessorFactory;
import com.vsc.monitoring.processors.HostedGraphiteProcessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jebynot on 1/26/16.
 */
public class MonitoringContextListener implements ServletContextListener {



    public void contextInitialized(ServletContextEvent contextEvent) {


        System.out.print("Initializing monitoring processors");
        ProcessGroup processGroup = new ProcessGroup(new HostedGraphiteProcessor());
        ProcessGroup[] processGroups = {processGroup};
        Decomposer decomposer = new AttributeDecomposer();

        MonitorProcessorFactory monitorProcessorFactory = new SimpleMonitorProcessorFactory(processGroups);
        MonitoringEngine.getInstance().setProcessorFactory(monitorProcessorFactory);
        MonitoringEngine.getInstance().setDecomposer(decomposer);
        MonitoringEngine.getInstance().startup();
        System.out.print("Completed monitoring processors initialization");

    }

    public void contextDestroyed(ServletContextEvent var1) {
        MonitoringEngine.getInstance().shutdown();
    }
}
