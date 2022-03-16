package com.kovan.lib.util.lb;

public interface Scheduler {
    void schedulerInit();

    HostNode popNextHostNode();

    void printNodeListStatus();

    String getNodeListStatus();
}