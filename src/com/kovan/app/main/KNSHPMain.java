package com.kovan.app.main;

import com.kovan.app.define.DefineCode;
import com.kovan.app.server.hp.HpServerHandler;
import com.kovan.app.util.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KNSHPMain {
    protected static Logger logger = LoggerFactory.getLogger(KNSHPMain.class);
    private static ConfigManager configManager = ConfigManager.getConfigManager();

    public boolean init(String path) {
        try {
            configManager.load(ConfigManager.CONFIG_X,path);
        } catch ( Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }

    public void runServer(){
        String serverMode = configManager.getValue(DefineCode.SERVER_MODE);
        logger.debug("SERVER_MODE["+serverMode+"]");

        switch( serverMode ){
            case DefineCode.SERVER_MODE_HP_01:
            case DefineCode.SERVER_MODE_HP_02:
            case DefineCode.SERVER_MODE_HP_03:
            case DefineCode.SERVER_MODE_HP_04:
            case DefineCode.SERVER_MODE_HP_05:
            case DefineCode.SERVER_MODE_HP_06:
            case DefineCode.SERVER_MODE_HP_07:
            case DefineCode.SERVER_MODE_HP_08:
            case DefineCode.SERVER_MODE_HP_09:
                HpServerHandler serverHandler = new HpServerHandler();
                serverHandler.init();
                serverHandler.runServer();
                break;

            default:
                logger.error("serverMode is undefined!!  serverMode[" + serverMode + "]");
                System.exit(0);
                break;
        }

    }

    public static void main(String[] args) {
        if(args.length < 1 ){
            System.exit(0);
        }

        KNSHPMain knsisMain = new KNSHPMain();

        if ( !knsisMain.init(args[0]) ) {
            logger.error("Fail to load Configuration !!!");
            System.exit(0);
        }

        logger.debug("ServerMode:" + configManager.getValue(DefineCode.SERVER_MODE));
        ConfigManager conf = new ConfigManager();

        knsisMain.runServer();
    }

}
