package com.kovan.lib.util.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    public JsonUtil() {
    }

    public static String JsonToString(Object object) {
        String retValues = null;
        if (object instanceof Map) {
            JSONObject jsonobj = new JSONObject();
            jsonobj.putAll((Map)object);
            retValues = jsonobj.toJSONString();
            jsonobj.clear();
        } else {
            retValues = JSONArray.toJSONString((List)object);
        }

        return retValues;
    }

    public static Map StringToJson(String reqvalues) {
        Map retMap = null;

        try {
            JSONParser parser = new JSONParser();
            Object parseobj = parser.parse(reqvalues);
            retMap = (Map)parseobj;
            return retMap;
        } catch (ParseException var4) {
            return null;
        }
    }

    public static List JsonToList(String reqvalues) {
        List retList = null;

        try {
            JSONParser parser = new JSONParser();
            Object parseobj = parser.parse(reqvalues);
            retList = (List)parseobj;
            return retList;
        } catch (ParseException var4) {
            return null;
        }
    }
}