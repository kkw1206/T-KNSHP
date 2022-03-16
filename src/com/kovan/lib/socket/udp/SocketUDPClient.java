package com.kovan.lib.socket.udp;

import com.kovan.lib.socket.SocketOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;


public class SocketUDPClient {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final int RET_SUCCESS = 0;
    public final int RET_FAIL = -1;
    private DatagramSocket sock;
    private DatagramPacket datagramPacket;

    private String connectHost = "";
    private int connectPort = 0;
    private int connectTimeout = 1000;
    private int recvTimeout = 1000;
    private String charset = "UTF-8";

    public SocketUDPClient() {
    }

    public byte[] getPacketData() {
        return this.datagramPacket.getData();
    }

    public String getPacketDataString() throws Exception {
        return new String(this.datagramPacket.getData(), 0, datagramPacket.getLength(), this.charset);
    }
    public void setSocketOption(SocketOption socketOption) {
        this.connectHost = socketOption.getConnectHost();
        this.connectPort = socketOption.getConnectPort();
        this.connectTimeout = socketOption.getConnectTimeout();
        this.recvTimeout = socketOption.getRecvTimeout();
        this.charset = socketOption.getCharset();
    }
    public void makeSocket() throws Exception{
        this.sock = new DatagramSocket();
        this.sock.setSoTimeout(this.recvTimeout);
    }
    public void makeSocket(int port) throws Exception{
        this.sock = new DatagramSocket(port);
        this.sock.setSoTimeout(this.recvTimeout);
    }
    public void send(byte[] packet) throws Exception{
        this.datagramPacket = new DatagramPacket(packet, packet.length, InetAddress.getByName(this.connectHost), this.connectPort );
        this.sock.send(this.datagramPacket);
    }
    public void send(String packet) throws Exception{
        this.datagramPacket = new DatagramPacket(packet.getBytes(charset),
                packet.getBytes(charset).length,
                InetAddress.getByName(this.connectHost), this.connectPort );

        this.sock.send(this.datagramPacket);
    }
    public void recv() throws Exception {
        byte[] buffer = new byte[1024*6];
        this.datagramPacket = new DatagramPacket(buffer, buffer.length);
        this.sock.receive(this.datagramPacket);
    }

    public void close() throws Exception{
        this.sock.close();
    }

}
