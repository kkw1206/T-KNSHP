package com.kovan.lib.socket;

public class SocketOption {
    private String connectHost = "";
    private int connectPort = 0;
    private int connectTimeout = 1000;
    private int recvTimeout = 1000;
    private String charset = "UTF-8";

    public SocketOption() {
    }

    public String getConnectHost() {
        return this.connectHost;
    }

    public int getConnectPort() {
        return this.connectPort;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getRecvTimeout() {
        return this.recvTimeout;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setConnectHost(String connectHost) {
        this.connectHost = connectHost;
    }

    public void setConnectPort(String connectPort) throws Exception {
        this.connectPort = Integer.parseInt(connectPort);
    }

    public void setConnectTimeout(String connectTimeout) throws Exception {
        this.connectTimeout = Integer.parseInt(connectTimeout);
    }

    public void setRecvTimeout(String recvTimeout) throws Exception {
        this.recvTimeout = Integer.parseInt(recvTimeout);
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
