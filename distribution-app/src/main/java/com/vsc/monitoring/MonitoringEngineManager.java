package com.vsc.monitoring;

import com.orbitz.monitoring.api.Decomposer;
import com.orbitz.monitoring.api.MonitorProcessorFactory;
import com.orbitz.monitoring.lib.BaseMonitoringEngineManager;
import org.apache.log4j.Logger;

/**
 * Created by jebynot on 1/27/16.
 */
public class MonitoringEngineManager extends BaseMonitoringEngineManager {
    private static final Logger log = Logger.getLogger(MonitoringEngineManager.class);

    public MonitoringEngineManager() {
        super();
    }

    public MonitoringEngineManager(MonitorProcessorFactory factory) {
        super(factory);
    }

    public MonitoringEngineManager(MonitorProcessorFactory factory, Decomposer decomposer) {
        super(factory, decomposer);

    }

    public void startup() {
        super.startup();
    }

}
