package com.kovan.lib.util.lb;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class LoadBalanceManager {
    private Scheduler scheduler;

    public LoadBalanceManager() {
    }

    public void init(String filePath) throws ConfigurationException {
        Configuration config = new PropertiesConfiguration(filePath);
        if (config.getString("RB_MODE").equals("MS")) {
            this.scheduler = new MasterSlaveScheduler(config);
        } else {
            throw new ConfigurationException("[LoadBanlanceManager]RB_MODE 설정 에러");
        }
    }

    public HostNode popNextHostNode() {
        return this.scheduler.popNextHostNode();
    }

    public void printNodeListStatus() {
        this.scheduler.printNodeListStatus();
    }

    public String getNodeListStatus() {
        return this.scheduler.getNodeListStatus();
    }
}
