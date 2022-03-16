package com.kovan.app.server.hp;

import com.kovan.app.define.DefineCode;
import com.kovan.app.model.KnsBodyInfo;
import com.kovan.app.util.ConfigManager;
import com.kovan.lib.util.exception.UserDefineException;
import com.kovan.lib.util.string.StringUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HpOutboundCodec extends MessageToMessageEncoder<HpPacketInfo> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConfigManager configManager = ConfigManager.getConfigManager();

    @Override
    protected void encode(ChannelHandlerContext ctx, HpPacketInfo packetInfo, List<Object> out) throws Exception {
        try {
            InetSocketAddress recipient = new InetSocketAddress(packetInfo.getClientIp(), Integer.parseInt(packetInfo.getClientPort()));
            DatagramPacket packet = new DatagramPacket(Unpooled.wrappedBuffer(packetInfo.getMsg().getBytes(StandardCharsets.UTF_8)), recipient);
            out.add(packet);

            logger.debug(String.format("%-15s", "[Send Message") + "][" + packetInfo.getClientIp() + ":" + packetInfo.getClientPort() + "][" + packetInfo.getMsg() + "]");
        }catch (Exception e){
            logger.error(StringUtil.getErrorLogForEMS(DefineCode.ERROR_EC5001, DefineCode.ERROR_EC5001_MSG));
            throw new UserDefineException(DefineCode.ERROR_EC5001, DefineCode.ERROR_EC5001_MSG);
        }

        logger.debug("=============================================================================");
        logger.debug("        Transaction End [ HpOutboundCodec::decode ] ");
        logger.debug("=============================================================================");

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("exceptionCaught...");
        ctx.flush();
    }
}
