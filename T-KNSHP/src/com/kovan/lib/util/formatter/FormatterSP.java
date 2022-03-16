package com.kovan.lib.util.formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class FormatterSP extends FormatterBase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String seperator;

    FormatterSP(String sp) {
        this.seperator = sp;
    }

    FormatterSP(String sp, boolean isStringBasedCropping) {
        this.seperator = sp;
        this.isStringBasedCropping = isStringBasedCropping;
    }

    public String Encoding(HashMap map) {
        this.logger.info("================================[ Encoding ]================================");
        String data = "";
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        Iterator it = treeMap.keySet().iterator();

        while(it.hasNext()) {
            Integer key = (Integer)it.next();
            if (map.containsKey(((FormatterAttribute)treeMap.get(key)).getName())) {
                String length = ((FormatterAttribute)treeMap.get(key)).getLength();
                if (!length.equals("X")) {
                    boolean cutFromBack = false;
                    if (length.startsWith("F")) {
                        cutFromBack = false;
                        length = length.replace("F", "");
                    } else if (length.startsWith("B")) {
                        cutFromBack = true;
                        length = length.replace("B", "");
                    }

                    int intLen = Integer.parseInt(length);
                    String val = (String)map.get(((FormatterAttribute)treeMap.get(key)).getName());
                    if (val == null) {
                        val = "";
                    }

                    if (intLen < val.length()) {
                        this.logger.debug("key[" + String.format("%-20s", ((FormatterAttribute)treeMap.get(key)).getName()) + "]" + "is cropped, since it exceeds formatter length limit. ");
                        if (this.isStringBasedCropping) {
                            if (cutFromBack) {
                                val = val.substring(val.length() - intLen, val.length());
                            } else {
                                val = val.substring(0, intLen);
                            }
                        } else {
                            try {
                                if (cutFromBack) {
                                    val = new String(val.getBytes("EUC-KR"), val.getBytes("EUC-KR").length - intLen, intLen, "EUC-KR");
                                } else {
                                    val = new String(val.getBytes("EUC-KR"), 0, intLen, "EUC-KR");
                                }
                            } catch (UnsupportedEncodingException var12) {
                                this.logger.error("바이트 단위로 잘라 인코딩하는 과정 실패");
                                var12.printStackTrace();
                            }
                        }
                    }

                    data = data + val;
                    data = data + this.seperator;
                } else {
                    data = data + (String)map.get(((FormatterAttribute)treeMap.get(key)).getName());
                    if (it.hasNext()) {
                        data = data + this.seperator;
                    }
                }

                this.logger.info("key[" + String.format("%-20s", ((FormatterAttribute)treeMap.get(key)).getName()) + "]" + " " + "value[" + (String)map.get(((FormatterAttribute)treeMap.get(key)).getName()) + "]");
            } else {
                this.logger.error("Not Found Item[" + ((FormatterAttribute)treeMap.get(key)).getName() + "]");
            }
        }

        this.logger.info("============================================================================");
        return data;
    }

    public HashMap Decoding(String data) {
        this.logger.info("================================[ Decoding ]================================");
        int position = 0;
        String value = "";
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        HashMap<String, String> map = new HashMap();
        Iterator<Integer> it = treeMap.keySet().iterator();
        String[] strArray = data.split(Pattern.quote(this.seperator), -1);
        int tokenCount = strArray.length;
        int formatEntryCount = treeMap.keySet().size();
        int i = 0;

        while(it.hasNext()) {
            Integer key = (Integer)it.next();
            if (i >= tokenCount) {
                break;
            }

            value = strArray[i];
            ++i;
            this.logger.info("key[" + String.format("%-20s", ((FormatterAttribute)treeMap.get(key)).getName()) + "]" + " " + "value[" + value + "]");
            map.put(((FormatterAttribute)treeMap.get(key)).getName(), value);
        }

        this.logger.info("============================================================================");
        return map;
    }

    public String Encoding(HashMap map, StringBuffer mskData) {
        return null;
    }

    public String EncodingENC(HashMap map) {
        return null;
    }

    public HashMap Decoding(String data, StringBuffer mskData) {
        return null;
    }

    public String DecodingENC(String data) {
        return null;
    }
}
