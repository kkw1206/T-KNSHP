package com.kovan.app.service.knsas;

import com.kovan.app.define.DefineCode;
import com.kovan.app.server.hp.HpPacketInfo;
import com.kovan.app.server.hp.HpServerHandler;
import com.kovan.app.util.ConfigManager;
import com.kovan.app.util.ConvertUtil;
import com.kovan.lib.socket.SocketOption;
import com.kovan.lib.socket.tcp.SocketTCPClient;
import com.kovan.lib.util.lb.HostNode;
import com.kovan.lib.util.lb.LoadBalanceManager;
import com.kovan.lib.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnsAsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ConfigManager configManager = ConfigManager.getConfigManager();
    LoadBalanceManager loadBalanceManager;

    public String runProc(HpPacketInfo pi) throws Exception {

        /* knsas-ft로 전송될 전문 새로 생섬함 */
        /* 최초, 전문뒤에 붙이려고 했으나 전문이 밀리는 상황을 고려해 JSON 형태로 변환함 */
        HpPacketInfo hpPacket = new HpPacketInfo();
        hpPacket.setMsg(pi.getMsg());
        hpPacket.setClientIp(pi.getClientIp());
        hpPacket.setClientPort(pi.getClientPort());
        hpPacket.setKnshpId(configManager.getValue(DefineCode.SERVER_ID));
        String newMsg = ConvertUtil.convertVOToJson(hpPacket);
        String sendData = StringUtil.getFixedLengthString(Integer.toString(newMsg.length()), "4", "0", "right" ) + newMsg;

        logger.debug(String.format("%-15s", "[KNSAS-FT Send") + "][" + sendData + "]");

        loadBalanceManager = HpServerHandler.knsAsFtLoadBalanceManager;

        SocketTCPClient socket = new SocketTCPClient();
        String recvData = null;

        try {

            int ret = 0;
            byte[] recvPacket = null;
            String strPacketLength = null;
            int intPacketLength;
            String recvString = null;

            SocketOption socketOption = new SocketOption();
            socketOption.setCharset(configManager.getValue(DefineCode.ENCODING));
            socketOption.setConnectTimeout(configManager.getValue(DefineCode.KNSAS_CONNECT_TIMEOUT));
            socketOption.setRecvTimeout(configManager.getValue(DefineCode.KNSAS_RECV_TIMEOUT));

            socket.setSocketOption(socketOption);

            connectGracefully(socket);

            /// 송신
            socket.send(sendData);



            /// 수신
            ret = socket.recv(4);
            if (ret == DefineCode.RET_FAIL)
                throw  new Exception();

            strPacketLength = new String(socket.getPacket(), configManager.getValue(DefineCode.ENCODING));
            intPacketLength = Integer.parseInt(strPacketLength);

            ret = socket.recv(intPacketLength);
            if (ret == DefineCode.RET_FAIL)
                throw  new Exception();

            recvData = strPacketLength + new String(socket.getPacket(), configManager.getValue(DefineCode.ENCODING));


            logger.debug(String.format("%-15s", "[KNSAS-FT Recv") + "][" + recvData + "]");


        } catch (Exception e) {
            logger.error("", e);

        } finally {
            // setResponseMap();
            try {
                socket.close();
            } catch (Exception e) {
                logger.error("KNSAS 통신 중 소켓을 닫는데 실패하였습니다. 이미 닫혀있어서 발생한 에러일 수 있습니다.");
            }
        }

        return recvData;

    }


    private void connectGracefully(SocketTCPClient socketTcpClient) throws Exception {

        int count=1;

        do{

            logger.debug(count+"번째 시도############################");
            logger.debug(loadBalanceManager.getNodeListStatus());

            HostNode hostNode = loadBalanceManager.popNextHostNode();

            // 사용가능한 호스트가 없는 경우
            if(hostNode == null){
                logger.error(loadBalanceManager.getNodeListStatus());
                logger.error("사용가능 한 호스트가 없습니다. 연결 시도을 중단합니다.");

                throw new Exception("Load Balnace 목록 내  사용가능 한 호스트가 없습니다. 연결 시도를 중단합니다.");
            }

            logger.debug("====Connection Try====="+String.format("%-15s", "["+hostNode.getName())+"]"+hostNode.getIp()+" "+hostNode.getPort());

            try{
                socketTcpClient.connect(hostNode.getIp(), hostNode.getPort());

                logger.error("===Connection Success=="+String.format("%-15s", "["+hostNode.getName())+"]"+hostNode.getIp()+" "+hostNode.getPort());

                hostNode.setActive();
                break;
            }catch(Exception e){

                logger.error("====Connection Fail===="+String.format("%-15s", "["+hostNode.getName())+"]"+hostNode.getIp()+" "+hostNode.getPort()+"\tCause: "+e.toString());
                logger.error(StringUtil.getErrorLogForEMS(DefineCode.ERROR_EC5018, DefineCode.ERROR_EC5018_MSG));
                hostNode.setInactive();

                count++;
            }


        }while(true);
    }
}
