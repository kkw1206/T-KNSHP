package com.kovan.app.api;
//git test
import com.kovan.app.define.DefineCode;
import com.kovan.app.model.KnsBodyInfo;
import com.kovan.app.server.hp.HpPacketInfo;
import com.kovan.app.server.hp.HpServerHandler;
import com.kovan.app.service.knsas.KnsAsService;
import com.kovan.app.util.ConvertUtil;
import com.kovan.lib.codec.CodecFixedLenNJson;
import com.kovan.lib.util.exception.UserDefineException;
import com.kovan.lib.util.string.StringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HpApiHandler extends SimpleChannelInboundHandler<HpPacketInfo> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Channel inboundChannel = null;

    private String msgType_kns;
    private String msgType;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HpPacketInfo pi) throws Exception{
        inboundChannel = ctx.channel();
        try {
            msgType_kns = pi.getMsg().substring(0, 4);
            msgType = pi.getMsg().substring(8, 12);
        } catch (Exception e){
            logger.error(StringUtil.getErrorLogForEMS(DefineCode.ERROR_EC5019, DefineCode.ERROR_EC5019_MSG));
            throw new UserDefineException(DefineCode.ERROR_EC5019, DefineCode.ERROR_EC5019_MSG);
        }

        if( msgType_kns.equals(DefineCode.MSG_TYPE_SND_TERMINAL)){
            logger.info("msgType_kns : " + msgType_kns);
            /* KNSAS-FS에서 받아 터미널로 내려줌. */
            sendToTerminalAndResp(pi);
            return;
        }

        switch (msgType){
            case DefineCode.MSG_TYPE_NETREG:
            case DefineCode.MSG_TYPE_HEALTHCHECK:
                KnsAsService asService = new KnsAsService();
                String recvData = asService.runProc(pi);
                /* 응답 메시지 내려줌 */
                if( recvData != null){
                    HpPacketInfo respPacket = new HpPacketInfo();
                    respPacket.setClientIp(pi.getClientIp());
                    respPacket.setClientPort(pi.getClientPort());
                    respPacket.setMsg(recvData);
                    inboundChannel.writeAndFlush(respPacket);
                }
                break;
            default:
                logger.error(StringUtil.getErrorLogForEMS(DefineCode.ERROR_EC5007, DefineCode.ERROR_EC5007_MSG));
                throw new UserDefineException(DefineCode.ERROR_EC5007, DefineCode.ERROR_EC5007_MSG);
        }
    }

    private void sendToTerminalAndResp(HpPacketInfo pi){
        CodecFixedLenNJson recvCodec = new CodecFixedLenNJson();
        KnsBodyInfo knsBodyInfo = null;
        try {
            recvCodec.setHeaderTemplate(HpServerHandler.knsHeaderTemplate);
            recvCodec.decodeHeader(pi.getMsg());
            String bodyString = pi.getMsg().substring(recvCodec.getHeaderLength());

            /* 1. 단말기 메시지 전송 */
            knsBodyInfo = ConvertUtil.convertJsonToVO(bodyString, KnsBodyInfo.class);
            HpPacketInfo respPacket = new HpPacketInfo();
            respPacket.setClientIp(knsBodyInfo.getCLIENT_IP());
            respPacket.setClientPort(knsBodyInfo.getCLIENT_PORT());
            respPacket.setMsg(knsBodyInfo.getSEND_DATA());
            inboundChannel.writeAndFlush(respPacket);


            /* 2. KNSAS에 응답 내림 */
            CodecFixedLenNJson resCodec = new CodecFixedLenNJson();
            resCodec.setHeaderTemplate(HpServerHandler.knsHeaderTemplate);
            resCodec.setAttributeHeader("MSG_TYPE", recvCodec.getAttributeHeader("MSG_TYPE"));
            resCodec.setAttributeHeader("MSG_VERSION", recvCodec.getAttributeHeader("MSG_VERSION"));
            resCodec.setAttributeHeader("MSG_DATA_TYPE", recvCodec.getAttributeHeader("MSG_DATA_TYPE"));
            resCodec.setAttributeHeader("MSG_CHARSET", recvCodec.getAttributeHeader("MSG_CHARSET"));
            resCodec.setAttributeHeader("MSG_DATA_CIPHER", recvCodec.getAttributeHeader("MSG_DATA_CIPHER"));


            KnsBodyInfo respBodyInfo = new KnsBodyInfo();
            respBodyInfo.setRESP_CODE(DefineCode.ERROR_EC0000);
            resCodec.setBodyMap(ConvertUtil.convertVOToMap(respBodyInfo, true));
            String resStr = resCodec.encode();
            HpPacketInfo respPacket2 = new HpPacketInfo();

            respPacket2.setClientIp(pi.getClientIp());
            respPacket2.setClientPort(pi.getClientPort());
            respPacket2.setMsg(resStr);

            inboundChannel.writeAndFlush(respPacket2);


        } catch (Exception e){
            logger.error(StringUtil.getErrorLogForEMS(DefineCode.ERROR_EC5019, DefineCode.ERROR_EC5019_MSG));
            throw new UserDefineException(DefineCode.ERROR_EC5019, DefineCode.ERROR_EC5019_MSG);
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.debug("[HpApiHandler] Exception 처리 로직");
        logger.error("", cause);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        logger.debug("channelReadComplete");
        ctx.flush();
    }
}

