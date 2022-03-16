package com.kovan.lib.socket.tcp;

import com.kovan.lib.socket.SocketOption;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketTCPClient {
    public final int RET_SUCCESS = 0;
    public final int RET_FAIL = -1;
    private Socket sock = null;
    protected byte[] packet = null;
    private String connectHost = "";
    private int connectPort = 0;
    private int connectTimeout = 1000;
    private int recvTimeout = 1000;
    private String charset = "UTF-8";

    public SocketTCPClient() {
    }

    public void setSocketOption(SocketOption socketOption) {
        this.connectHost = socketOption.getConnectHost();
        this.connectPort = socketOption.getConnectPort();
        this.connectTimeout = socketOption.getConnectTimeout();
        this.recvTimeout = socketOption.getRecvTimeout();
        this.charset = socketOption.getCharset();
    }

    public byte[] getPacket() {
        return this.packet;
    }

    public String getPacketString() throws Exception {
        return new String(this.packet, this.charset);
    }

    public void connect() throws Exception {
        InetSocketAddress isa = new InetSocketAddress(this.connectHost, this.connectPort);
        this.sock = new Socket();
        this.sock.setSoTimeout(this.recvTimeout);
        this.sock.connect(isa, this.connectTimeout);
    }

    public void connect(String host, String port) throws Exception {
        InetSocketAddress isa = new InetSocketAddress(host, Integer.parseInt(port));
        this.sock = new Socket();
        this.sock.setSoTimeout(this.recvTimeout);
        this.sock.connect(isa, this.connectTimeout);
    }

    public void send(byte[] packet) throws Exception {
        this.sock.getOutputStream().write(packet);
    }

    public void send(String packet) throws Exception {
        this.sock.getOutputStream().write(packet.getBytes(this.charset));
    }

    public int recv(int recvLength) throws Exception {
        this.packet = new byte[recvLength];
        int msglen = 0;
        int tlen = 0;
        int rlen = 0;

        for(InputStream is = this.sock.getInputStream(); tlen < recvLength; tlen += rlen) {
            rlen = is.read(this.packet, tlen, recvLength - tlen);
            if (rlen < 1) {
                break;
            }
        }

        return tlen < recvLength ? -1 : 0;
    }

    public void close() throws Exception {
        this.sock.close();
    }

    public static void main(String[] args) {
        SocketOption socketOption = new SocketOption();

        try {
            socketOption.setConnectHost("127.0.0.1");
            socketOption.setConnectPort("5000");
            socketOption.setConnectTimeout("1000");
            socketOption.setRecvTimeout("1000");
            socketOption.setCharset("UTF-8");
        } catch (Exception var16) {
            var16.printStackTrace();
        }

        SocketTCPClient socketTcpClient = new SocketTCPClient();
        socketTcpClient.setSocketOption(socketOption);

        try {
            String sendMsg = "TEST";
            socketTcpClient.connect();
            System.out.println("send:" + sendMsg);
            socketTcpClient.send(sendMsg);
            socketTcpClient.recv(4);
            String recvString = socketTcpClient.getPacketString();
            System.out.println("recv:" + recvString);
            socketTcpClient.close();
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                socketTcpClient.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }

    }
}
