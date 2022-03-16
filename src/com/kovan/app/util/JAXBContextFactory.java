package com.kovan.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JAXBContextFactory {
    private static Logger logger = LoggerFactory.getLogger(JAXBContextFactory.class);
    private static JAXBContextFactory instance = null;
    private static Map<String, JAXBContext> instances = new ConcurrentHashMap<String, JAXBContext>();

    public static JAXBContextFactory getInstance() {
        if (instance == null) {
            instance = new JAXBContextFactory();
        }
        return instance;
    }

    public JAXBContext getJaxBContext(final String contextPath) {
        JAXBContext context = instances.get(contextPath);
        if (context == null) {
            try {
                context = JAXBContext.newInstance(contextPath);
            } catch (JAXBException e) {
                logger.error("JAXBContext.newInstance Exception", e);
            }
            instances.put(contextPath, context);
        }
        return context;
    }

    public <T> JAXBContext getJaxBContext(final Class<T> contextPath) {
        JAXBContext context = instances.get(contextPath.getName());
        if (context == null) {
            try {
                context = JAXBContext.newInstance(contextPath);
            } catch (JAXBException e) {
                logger.error("JAXBContext.newInstance Exception", e);
            }
            instances.put(contextPath.getName(), context);
        }
        return context;
    }
}
