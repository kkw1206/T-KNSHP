package com.kovan.lib.util.lb;

public class HostNode {
    private String ip;
    private String port;
    private String name;
    private boolean activeFlag = true;
    private long tryIntervalSec;
    private long currentExecutionTime;

    public HostNode() {
    }

    public HostNode(String nodeName, String nodeIp, String nodePort, int tryIntervalSec) {
        this.name = nodeName;
        this.ip = nodeIp;
        this.port = nodePort;
        this.tryIntervalSec = (long)tryIntervalSec;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return this.activeFlag;
    }

    public void setInactive() {
        this.activeFlag = false;
    }

    public void setActive() {
        this.activeFlag = true;
    }

    public long getTryIntervalSec() {
        return this.tryIntervalSec;
    }

    public void setTryIntervalSec(int tryIntervalSec) {
        this.tryIntervalSec = (long)tryIntervalSec;
    }

    public boolean isValid() {
        long now = System.currentTimeMillis();
        return this.activeFlag || now - this.currentExecutionTime > this.tryIntervalSec * 1000L;
    }

    public void setCurrentExecutiontime() {
        this.currentExecutionTime = System.currentTimeMillis();
    }
}
