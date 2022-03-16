package com.kovan.app.model;

public class KnsBodyInfo {
    private String SEND_DATA;
    private String CLIENT_IP;
    private String CLIENT_PORT;
    private String RESP_CODE;

    public String getSEND_DATA() { return SEND_DATA; }
    public void setSEND_DATA(String SEND_DATA) { this.SEND_DATA = SEND_DATA; }
    public String getCLIENT_IP() { return CLIENT_IP; }
    public void setCLIENT_IP(String CLIENT_IP) { this.CLIENT_IP = CLIENT_IP; }
    public String getCLIENT_PORT() { return CLIENT_PORT; }
    public void setCLIENT_PORT(String CLIENT_PORT) { this.CLIENT_PORT = CLIENT_PORT; }
    public String getRESP_CODE() { return RESP_CODE; }
    public void setRESP_CODE(String RESP_CODE) { this.RESP_CODE = RESP_CODE; }
}
