package com.kovan.app.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Map;

public class JsonUtil {

    /**
     * <pre>
     * Json Object 를 String으로 변환해주는 메소드
     * </pre>
     * @param object
     * @return 결과String
     */
    public static String JsonToString(Object object){
        String retValues = null;

        if(object instanceof Map){

            JSONObject jsonobj = new JSONObject();
            jsonobj.putAll((Map) object);

            retValues = jsonobj.toJSONString();
            jsonobj.clear();
        }else{

            //JSONArray retArray =JSONArray.toJSONString(arg0) new JSONArray();
            //retArray.add(object);

            //retValues = retArray.toJSONString();
            //retValues = "{"+JSONArray.toJSONString((List) object)+"}";
            retValues = JSONArray.toJSONString((List) object);

        }
        return retValues;
    }

    /**
     * <pre>
     * String을 Json Object로 변환해주는 메소드
     * </pre>
     * @param reqvalues
     * @return Map
     */
    public static Map StringToJson(String reqvalues){
        Map retMap = null;

        try {
            JSONParser parser = new JSONParser();

            Object parseobj = parser.parse(reqvalues);

            retMap = (Map) parseobj;
        } catch (ParseException e) {

            return null;
        }

        return retMap;
    }


    /**
     * <pre>
     * String을 Json Array로 변환해주는 메소드
     * </pre>
     * @param reqvalues
     * @return List
     */
    public static List JsonToList(String reqvalues){
        List retList = null;

        try {
            JSONParser parser = new JSONParser();

            Object parseobj = parser.parse(reqvalues);

            retList = (List) parseobj;
        } catch (ParseException e) {

            return null;
        }

        return retList;
    }
}
