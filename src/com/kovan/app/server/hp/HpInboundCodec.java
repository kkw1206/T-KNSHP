package com.kovan.app.server.hp;

import com.kovan.app.define.DefineCode;
import com.kovan.lib.util.exception.UserDefineException;
import com.kovan.lib.util.string.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

public class HpInboundCodec extends MessageToMessageDecoder<DatagramPacket> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Channel inboundChannel;
    private ByteBuf byteBuf;
    HpPacketInfo recvPacketDecoder = new HpPacketInfo();

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) throws Exception {

        logger.debug("=============================================================================");
        logger.debug("        Transaction Start [ HpInboundCodec::decode ] ");
        logger.debug("=============================================================================");

        String tempStrMsg = null;
        String clientNatIp = null;
        String clientNatPort = null;
        try {
            tempStrMsg = packet.content().toString(Charset.defaultCharset());
            clientNatIp = packet.sender().getAddress().getHostAddress();
            clientNatPort = Integer.toString(packet.sender().getPort());

            recvPacketDecoder.setMsg(tempStrMsg);
            recvPacketDecoder.setClientIp(clientNatIp);
            recvPacketDecoder.setClientPort(clientNatPort);
        }
        catch (Exception e){
            logger.debug(String.format("%-15s", "[Recv Temp Message") + "]" + tempStrMsg);
            logger.error(StringUtil.getErrorLogForEMS(DefineCode.ERROR_EC5001, DefineCode.ERROR_EC5001_MSG));
            throw new UserDefineException(DefineCode.ERROR_EC5001, DefineCode.ERROR_EC5001_MSG);
        }

        logger.debug(String.format("%-15s", "[Recv Message") + "][" + clientNatIp + ":" + clientNatPort + "][" + tempStrMsg + "]");

        out.add(recvPacketDecoder);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 에러시 에러메시지 출력만 함.
        logger.error("", cause);
        ctx.flush();
    }
}
