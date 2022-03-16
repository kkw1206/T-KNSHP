package com.kovan.app.server.hp;

import com.kovan.app.api.HpApiHandler;
import com.kovan.app.define.DefineCode;
import com.kovan.app.util.ConfigManager;
import com.kovan.app.util.headertemplate.HeaderTemplateAbstract;
import com.kovan.app.util.headertemplate.KnsHeaderTemplate;
import com.kovan.lib.codec.HeaderTemplate;
import com.kovan.lib.util.formatter.FormatterManager;
import com.kovan.lib.util.lb.LoadBalanceManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class HpServerHandler extends ChannelInitializer<DatagramChannel> {
    private static Logger logger = LoggerFactory.getLogger(HpServerHandler.class);
    private static ConfigManager configManager = ConfigManager.getConfigManager();

    public static LoadBalanceManager knsAsFtLoadBalanceManager = new LoadBalanceManager();
    private static FormatterManager formatterManager = FormatterManager.getFormatterManager();

    private static EventExecutorGroup executorGroup = new DefaultEventExecutorGroup(DefineCode.WORKER_BIZ_GROUP_THREAD_NO);

    private static HeaderTemplateAbstract ht;
    public static HeaderTemplate knsHeaderTemplate;

    public HpServerHandler(){}

    public static void init() {
        try {
            logger.debug("SERVERINIT START");

            logger.debug("====================== [ HeaderTemplate Init Start] ======================");
            initHeaderTemplate();
            logger.debug("====================== [ HeaderTemplate Init End] ======================");

            logger.debug("====================== [ Load Balancer Init Start		  ] ======================");
            knsAsFtLoadBalanceManager.init(configManager.getValue(DefineCode.KNSAS_FT_LOAD_BALANCER_CONFIG));
            logger.debug("====================== [ Load Balancerl Init End        ] ======================");

//            logger.debug("====================== [ Formatter Init Start] ======================");
//            logger.debug("====================== [ Formatter Init End  ] ======================");

            logger.debug("SERVERINIT END");

        } catch (Exception e) {
            logger.error("Server Init Fail");
            logger.error("", e);
            System.exit(-1);
        }
    }

    @Override
    public void initChannel(DatagramChannel channel) throws Exception {
        Charset charSet = Charset.forName(configManager.getValue(DefineCode.ENCODING));
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new StringDecoder(charSet));
        pipeline.addLast(new HpInboundCodec());
        pipeline.addLast(executorGroup, new HpApiHandler());
        pipeline.addLast(new StringEncoder(charSet));

        pipeline.addLast(new HpOutboundCodec());
    }


    public void runServer() {
        logger.debug("SERVER LISTEN PORT" + configManager.getValue(DefineCode.SERVER_LISTEN_PORT));
        int serverPort = Integer.parseInt(configManager.getValue(DefineCode.SERVER_LISTEN_PORT));

        EventLoopGroup workerGroup = new NioEventLoopGroup(DefineCode.WORKER_GROUP_N_THREADS);
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(workerGroup);
            bootstrap.channel(NioDatagramChannel.class);

            bootstrap.handler(new LoggingHandler(LogLevel.DEBUG));
            bootstrap.handler(new HpServerHandler());

            // Optional 설정
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.option(ChannelOption.SO_REUSEADDR, true);

            Channel ch = bootstrap.bind(serverPort).sync().channel();

            ch.closeFuture().await();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    private static void initHeaderTemplate() throws Exception {
        String knsTemplateName = configManager.getValue(DefineCode.KNS_HEADER_TEMPLATE_NAME);

        switch (knsTemplateName){
            case "com.kovan.app.util.headertemplate.KnsHeaderTemplate":
                ht = new KnsHeaderTemplate();
                break;
            default:
                logger.error("config 항목 내 HEADER_TEMPLATE_NAME와 일치하는 템플릿 클래스가 없습니다.");
                logger.error("knsTemplateName [" + knsTemplateName +"]");
                throw new IllegalStateException();
        }

        knsHeaderTemplate = ht.headerTemplate;

    }

}
