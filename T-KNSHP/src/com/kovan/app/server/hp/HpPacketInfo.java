package com.kovan.app.server.hp;

public class HpPacketInfo {
    private String msg;
    private String clientIp;
    private String clientPort;
    private String knshpId;

    public HpPacketInfo(){
    }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public String getClientIp() { return clientIp; }
    public void setClientIp(String clientIp) { this.clientIp = clientIp; }
    public String getClientPort() { return clientPort; }
    public void setClientPort(String clientPort) { this.clientPort = clientPort; }
    public String getKnshpId() { return knshpId; }
    public void setKnshpId(String knshpId) { this.knshpId = knshpId; }
}
