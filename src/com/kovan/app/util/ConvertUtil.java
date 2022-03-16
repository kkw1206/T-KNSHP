package com.kovan.app.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

public class ConvertUtil {
    // Logger
    final private static Logger logger = LoggerFactory.getLogger(ConvertUtil.class);
    private static ObjectMapper mapper;
    private static ObjectMapper upperMapper;
    static {
        mapper = new ObjectMapper();
        // 필드 접근을 위해 최적화 되고, 데이터 바인딩 오버헤드를 최소화하게 해주는 모듈
        mapper.registerModule(new AfterburnerModule());

        // vo에 등록되어있지 않은 것은 무시한다.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);

        // vo 변환시 대소문자 구분 없음
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        // null 혹은 비어있는 항목은 변환하지 않는다.
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        /** uppercase를 위한 mapper **/
        upperMapper = new ObjectMapper();
        // 필드 접근을 위해 최적화 되고, 데이터 바인딩 오버헤드를 최소화하게 해주는 모듈
        upperMapper.registerModule(new AfterburnerModule());

        // vo에 등록되어있지 않은 것은 무시한다.
        upperMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);

        // vo 변환시 대소문자 구분 없음
        upperMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        // null 혹은 비어있는 항목은 변환하지 않는다.
        upperMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // json key를 uppercase로 변경
        upperMapper.setPropertyNamingStrategy(new UpperCaseStrategy());
    }

    public static class UpperCaseStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {
        @Override
        public String translate(String input) {
            return input.toUpperCase();
        }
    }
    /**
     * <pre>
     * VO를 HashMap으로 변환
     * uppercase : 결과로 나오는 HashMap을 True(대문자), False(소문자)
     * </pre>
     *
     * @param obj
     * @param uppercase
     * @return
     */
    public static HashMap<String, String> convertVOToMap(Object obj, boolean uppercase) {
        HashMap<String, String> retMap = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields();

        logger.info("convertVOToMap() > fields : " + fields.length);

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                String value = (String) fields[i].get(obj);
                if (value==null) continue;

                String name = fields[i].getName();
                logger.info("convertVOToMap() > fields name : " + name);
                if (uppercase == true)
                    name.toUpperCase();
                else
                    name.toLowerCase();

                retMap.put(name, String.valueOf(fields[i].get(obj)));
            } catch (Exception e) {
                logger.error(obj.getClass().getSimpleName() + " to Map Converting Error", e);
            }
        }

        return retMap;
    }

    /**
     * <pre>
     * HashMap을 VO로 변환
     * uppercase : HashMap의 key값을 True(대문자), False(소문자)
     * </pre>
     *
     * @param map
     * @param objClass
     * @param uppercase
     * @return
     */
    public static Object convertMapToVO(HashMap map, Object objClass, boolean uppercase) {
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;
        Iterator itr = map.keySet().iterator();

        Object obj = objClass;

        while (itr.hasNext()) {
            keyAttribute = (String) itr.next();

            // map에 있는 값이 null이면 굳이 할필요 없음.
            if (map.get(keyAttribute)==null) continue;

            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase();

            if (uppercase == false)
                methodString += keyAttribute.substring(1).toLowerCase();
            else if (uppercase == true)
                methodString += keyAttribute.substring(1).toUpperCase();

            try {
                Method[] methods = obj.getClass().getDeclaredMethods();
                for (int i = 0; i <= methods.length - 1; i++) {
                    if (methodString.equals(methods[i].getName())) {
                        methods[i].invoke(obj, map.get(keyAttribute));
                    }
                }
            } catch (Exception e) {
                logger.error("Map to " + obj.getClass().getSimpleName() + " Converting Error", e);
            }
        }

        return obj;
    }

    public static String convertVOToXml(Object obj, String encoding) {
        StringWriter sw = null;

        try {
            JAXBContextFactory factory = JAXBContextFactory.getInstance();
            JAXBContext jaxbContext = factory.getJaxBContext(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            // 인코딩
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            // /r/n처리
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // XML 헤더 제거
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            sw = new StringWriter();
            marshaller.marshal(obj, sw);

        } catch (Exception e) {
            logger.error("Convert "+obj.getClass().getName()+" to xml Error", e);
        }

        return sw.toString();
    }

    public static <T> T convertXmlToVO(String strXml, Class<T> clazz, String encoding) {
        T rtnVo = null;
        try {
            if (strXml == null) {
                logger.error("convertXmlToVO strXml is null!");
                return null;
            }
            JAXBContextFactory factory = JAXBContextFactory.getInstance();
            JAXBContext jaxbContext = factory.getJaxBContext(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            rtnVo = (T) unmarshaller.unmarshal(new StringReader(strXml));

        } catch (Exception e) {
            logger.error("Convert xml to "+clazz.getSimpleName()+" Error", e);
        }

        return rtnVo;
    }

    public static <T> T convertJsonToVO(String strJson, Class<T> clazz) {
        if (strJson==null || strJson.isEmpty()) return null;
        T rtnVo = null;
        try {
            rtnVo = mapper.readValue(strJson, clazz);
        } catch(Exception e) {
            logger.error("", e);
            logger.error("Json to VO Exception!!", e);
            rtnVo = null;
        }

        return rtnVo;
    }

    public static String convertVOToJson(Object obj) {
        String rtnStr = "";
        if (obj == null) return rtnStr;

        try {
            rtnStr = mapper.writeValueAsString(obj);
        } catch(Exception e) {
            logger.error("VO to Json Exception!!", e);
            rtnStr = "";
        }

        return rtnStr;
    }

    public static String convertVOToUpperJson(Object obj) {
        String rtnStr = "";
        if (obj == null) return rtnStr;

        try {
            rtnStr = upperMapper.writeValueAsString(obj);
        } catch(Exception e) {
            logger.error("VO to Json Exception!!", e);
            rtnStr = "";
        }

        return rtnStr;
    }
}
