package com.kovan.lib.util.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ValidatorManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ValidatorManager validateManager = null;
    private HashMap<String, ValidatorHandler> mapValidateHandler = new HashMap();

    public ValidatorManager() {
    }

    public static ValidatorManager getValidateManager() {
        if (validateManager == null) {
            validateManager = new ValidatorManager();
        }

        return validateManager;
    }

    public ValidatorHandler getValidatorHandler(String name) {
        return (ValidatorHandler)this.mapValidateHandler.get(name);
    }

    public void load(String path) throws Exception {
        ValidatorHandler vh = new ValidatorHandler();

        try {
            vh.loadXml(path);
        } catch (Exception var4) {
            this.logger.error("", var4);
        }

        this.mapValidateHandler.put(path, vh);
    }

    public static void main(String[] args) {
    }
}