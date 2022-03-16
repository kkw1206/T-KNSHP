package com.kovan.lib.util.lb;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MasterSlaveScheduler implements Scheduler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<HostNode> hostNodeList = new ArrayList();
    private Configuration config;
    private int nextHostNodeIndex = 0;

    public MasterSlaveScheduler(Configuration config) {
        this.config = config;
        this.schedulerInit();
    }

    public void schedulerInit() {
        int tryIntervalSec = this.config.getInt("TRY_INTERVAL_SEC");
        String masterNodeName = this.config.getString("MASTER");
        String masterNodeIp = (String)this.config.getList(masterNodeName).get(0);
        String masterNodePort = (String)this.config.getList(masterNodeName).get(1);
        this.hostNodeList.add(new HostNode(masterNodeName, masterNodeIp, masterNodePort, tryIntervalSec));
        List<String> slaveNodeNameList = this.config.getList("SLAVE");
        Iterator var6 = slaveNodeNameList.iterator();

        while(var6.hasNext()) {
            String nodeName = (String)var6.next();
            String nodeIp = (String)this.config.getList(nodeName).get(0);
            String nodePort = (String)this.config.getList(nodeName).get(1);
            this.hostNodeList.add(new HostNode(nodeName, nodeIp, nodePort, tryIntervalSec));
        }

    }

    public HostNode popNextHostNode() {
        this.nextHostNodeIndex = 0;

        HostNode nextHostNode;
        for(nextHostNode = (HostNode)this.hostNodeList.get(this.nextHostNodeIndex); !nextHostNode.isValid(); nextHostNode = (HostNode)this.hostNodeList.get(this.nextHostNodeIndex)) {
            ++this.nextHostNodeIndex;
            if (this.nextHostNodeIndex == this.hostNodeList.size()) {
                return null;
            }
        }

        nextHostNode.setCurrentExecutiontime();
        return nextHostNode;
    }

    public void printNodeListStatus() {
        this.logger.info("==========================Print Host Node Status============================");
        Iterator var4 = this.hostNodeList.iterator();

        while(var4.hasNext()) {
            HostNode nextHostNode = (HostNode)var4.next();
            String formatName = String.format("%-10s", nextHostNode.getName());
            String formatIpNport = String.format("%-20s", nextHostNode.getIp() + " " + nextHostNode.getPort());
            String formatActiveNvalid = String.format("%-7s", nextHostNode.isActive() ? "ACTIVE" : "INACTIVE") + ":" + String.format("%-10s", nextHostNode.isValid() ? "VALID" : "NOT VALID");
            this.logger.info("name[" + formatName + "] ip/port[" + formatIpNport + "] status[" + formatActiveNvalid + "]");
        }

        this.logger.info("============================================================================");
    }

    public String getNodeListStatus() {
        String retMsg = "\n";
        retMsg = retMsg + "==========================Print Host Node Status============================\n";

        String formatName;
        String formatActiveNvalid;
        String formatIpNport;
        for(Iterator var5 = this.hostNodeList.iterator(); var5.hasNext(); retMsg = retMsg + "name[" + formatName + "] ip/port[" + formatIpNport + "] status[" + formatActiveNvalid + "]\n") {
            HostNode nextHostNode = (HostNode)var5.next();
            formatName = String.format("%-10s", nextHostNode.getName());
            formatIpNport = String.format("%-20s", nextHostNode.getIp() + " " + nextHostNode.getPort());
            formatActiveNvalid = String.format("%-7s", nextHostNode.isActive() ? "ACTIVE" : "INACTIVE") + ":" + String.format("%-10s", nextHostNode.isValid() ? "VALID" : "NOT VALID");
        }

        retMsg = retMsg + "============================================================================\n";
        return retMsg;
    }
}
