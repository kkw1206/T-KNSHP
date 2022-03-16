package com.kovan.lib.util.formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class FormatterFL extends FormatterBase {
    private static Logger logger = LoggerFactory.getLogger(FormatterFL.class);

    public FormatterFL() {
    }

    public FormatterFL(boolean isStringBasedCropping) {
        this.isStringBasedCropping = isStringBasedCropping;
    }

    public String Encoding(HashMap map) {
        logger.info("================================[ Encoding ]================================");
        String data = "";
        String finalValue = "";
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        Iterator it = treeMap.keySet().iterator();

        while(true) {
            while(it.hasNext()) {
                Integer key = (Integer)it.next();
                if (map.containsKey(((FormatterAttribute)treeMap.get(key)).getName())) {
                    int len;
                    String logginValue;
                    if (((FormatterAttribute)treeMap.get(key)).getLength().equals("X")) {
                        len = ((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).length();
                        if (this.isStringBasedCropping) {
                            finalValue = getFixedLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), Integer.toString(((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).length()), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        } else {
                            try {
                                finalValue = getFixedByteLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), Integer.toString(((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).getBytes("EUC-KR").length), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                            } catch (UnsupportedEncodingException var11) {
                                logger.error("인코딩 과정중 EUC-KR 인코딩 실패");
                                var11.printStackTrace();
                            }
                        }
                    } else {
                        logginValue = ((FormatterAttribute)treeMap.get(key)).getLength();
                        if (logginValue.startsWith("F") || logginValue.startsWith("B")) {
                            logginValue = logginValue.substring(1, logginValue.length());
                        }

                        len = Integer.parseInt(logginValue);
                        if (this.isStringBasedCropping) {
                            finalValue = getFixedLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        } else {
                            finalValue = getFixedByteLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        }
                    }

                    data = data + finalValue;
                    logginValue = finalValue;
                    if (((FormatterAttribute)treeMap.get(key)).getMasking() != null) {
                        logginValue = "";

                        for(int i = 0; i < len; ++i) {
                            logginValue = logginValue + ((FormatterAttribute)treeMap.get(key)).getMasking().substring(0, 1);
                        }
                    }

                    logger.info("key[" + String.format("%-20s", ((FormatterAttribute)treeMap.get(key)).getName()) + "]" + " " + "value[" + logginValue + "]");
                } else {
                    logger.error("Not Found Item[" + ((FormatterAttribute)treeMap.get(key)).getName() + "]");
                }
            }

            logger.info("============================================================================");
            return data;
        }
    }

    public String Encoding(HashMap map, StringBuffer mskData) {
        logger.info("================================[ Encoding ]================================");
        String data = "";
        String finalValue = "";
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        Iterator it = treeMap.keySet().iterator();

        while(true) {
            while(it.hasNext()) {
                Integer key = (Integer)it.next();
                if (map.containsKey(((FormatterAttribute)treeMap.get(key)).getName())) {
                    int len;
                    String loggingValue;
                    if (((FormatterAttribute)treeMap.get(key)).getLength().equals("X")) {
                        len = ((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).length();
                        if (this.isStringBasedCropping) {
                            finalValue = getFixedLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), Integer.toString(((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).length()), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        } else {
                            try {
                                finalValue = getFixedByteLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), Integer.toString(((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).getBytes("EUC-KR").length), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                            } catch (UnsupportedEncodingException var12) {
                                logger.error("인코딩 과정중 EUC-KR 인코딩 실패");
                                var12.printStackTrace();
                            }
                        }
                    } else {
                        loggingValue = ((FormatterAttribute)treeMap.get(key)).getLength();
                        if (loggingValue.startsWith("F") || loggingValue.startsWith("B")) {
                            loggingValue = loggingValue.substring(1, loggingValue.length());
                        }

                        len = Integer.parseInt(loggingValue);
                        if (this.isStringBasedCropping) {
                            finalValue = getFixedLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        } else {
                            finalValue = getFixedByteLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        }
                    }

                    loggingValue = finalValue;
                    if (((FormatterAttribute)treeMap.get(key)).getMasking() != null) {
                        loggingValue = "";

                        for(int i = 0; i < len; ++i) {
                            loggingValue = loggingValue + ((FormatterAttribute)treeMap.get(key)).getMasking().substring(0, 1);
                        }
                    }

                    mskData.append(loggingValue);
                    data = data + finalValue;
                    logger.info("key[" + String.format("%-20s", ((FormatterAttribute)treeMap.get(key)).getName()) + "]" + " " + "value[" + loggingValue + "]");
                } else {
                    logger.error("Not Found Item[" + ((FormatterAttribute)treeMap.get(key)).getName() + "]");
                }
            }

            logger.info("============================================================================");
            return data;
        }
    }

    public String EncodingENC(HashMap map) {
        String data = "";
        String finalValue = "";
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        Iterator it = treeMap.keySet().iterator();

        while(true) {
            while(it.hasNext()) {
                Integer key = (Integer)it.next();
                if (map.containsKey(((FormatterAttribute)treeMap.get(key)).getName())) {
                    int len;
                    if (((FormatterAttribute)treeMap.get(key)).getLength().equals("X")) {
                        len = ((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).length();
                        if (this.isStringBasedCropping) {
                            finalValue = getFixedLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), Integer.toString(((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).length()), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        } else {
                            try {
                                finalValue = getFixedByteLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), Integer.toString(((String)map.get(((FormatterAttribute)treeMap.get(key)).getName())).getBytes("EUC-KR").length), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                            } catch (UnsupportedEncodingException var10) {
                                logger.error("인코딩 과정중 EUC-KR 인코딩 실패");
                                var10.printStackTrace();
                            }
                        }
                    } else {
                        String strLen = ((FormatterAttribute)treeMap.get(key)).getLength();
                        if (strLen.startsWith("F") || strLen.startsWith("B")) {
                            strLen = strLen.substring(1, strLen.length());
                        }

                        len = Integer.parseInt(strLen);
                        if (this.isStringBasedCropping) {
                            finalValue = getFixedLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        } else {
                            finalValue = getFixedByteLengthString((String)map.get(((FormatterAttribute)treeMap.get(key)).getName()), ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), ((FormatterAttribute)treeMap.get(key)).getAlign());
                        }
                    }

                    if (((FormatterAttribute)treeMap.get(key)).getMasking() != null) {
                        finalValue = "";

                        for(int i = 0; i < len; ++i) {
                            finalValue = finalValue + ((FormatterAttribute)treeMap.get(key)).getMasking().substring(0, 1);
                        }
                    }

                    data = data + finalValue;
                } else {
                    logger.error("Not Found Item[" + ((FormatterAttribute)treeMap.get(key)).getName() + "]");
                }
            }

            return data;
        }
    }

    public HashMap Decoding(String data) {
        logger.info("================================[ Decoding ]================================");
        int position = 0;
        String value = "";
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        HashMap<String, String> map = new HashMap();
        Iterator it = treeMap.keySet().iterator();

        while(it.hasNext()) {
            Integer key = (Integer)it.next();
            if (this.isStringBasedCropping) {
                value = this.setFixedLengthString(data, ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), position, ((FormatterAttribute)treeMap.get(key)).getAlign());
            } else {
                value = this.setFixedByteLengthString(data, ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), position, ((FormatterAttribute)treeMap.get(key)).getAlign());
            }

            int len = Integer.parseInt(((FormatterAttribute)treeMap.get(key)).getLength());
            position += len;
            String logginValue = value;
            if (((FormatterAttribute)treeMap.get(key)).getMasking() != null) {
                logginValue = "";

                for(int i = 0; i < len; ++i) {
                    logginValue = logginValue + ((FormatterAttribute)treeMap.get(key)).getMasking().substring(0, 1);
                }
            }

            logger.info("key[" + String.format("%-20s", ((FormatterAttribute)treeMap.get(key)).getName()) + "]" + " " + "value[" + logginValue + "]");
            map.put(((FormatterAttribute)treeMap.get(key)).getName(), value);
        }

        logger.info("============================================================================");
        return map;
    }

    public HashMap Decoding(String data, StringBuffer mskData) {
        logger.info("================================[ Decoding ]================================");
        int position = 0;
        String value = "";
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        HashMap<String, String> map = new HashMap();
        Iterator it = treeMap.keySet().iterator();

        while(it.hasNext()) {
            Integer key = (Integer)it.next();
            if (this.isStringBasedCropping) {
                value = this.setFixedLengthString(data, ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), position, ((FormatterAttribute)treeMap.get(key)).getAlign());
            } else {
                value = this.setFixedByteLengthString(data, ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), position, ((FormatterAttribute)treeMap.get(key)).getAlign());
            }

            int len = Integer.parseInt(((FormatterAttribute)treeMap.get(key)).getLength());
            position += len;
            String loggingValue = value;
            if (((FormatterAttribute)treeMap.get(key)).getMasking() != null) {
                loggingValue = "";

                for(int i = 0; i < len; ++i) {
                    loggingValue = loggingValue + ((FormatterAttribute)treeMap.get(key)).getMasking().substring(0, 1);
                }
            }

            mskData.append(loggingValue);
            logger.info("key[" + String.format("%-20s", ((FormatterAttribute)treeMap.get(key)).getName()) + "]" + " " + "value[" + loggingValue + "]");
            map.put(((FormatterAttribute)treeMap.get(key)).getName(), value);
        }

        logger.info("============================================================================");
        return map;
    }

    public String DecodingENC(String data) {
        int position = 0;
        HashMap hmap = this.getFormatterMap();
        TreeMap<Integer, FormatterAttribute> treeMap = new TreeMap(hmap);
        String encData = "";

        int len;
        for(Iterator it = treeMap.keySet().iterator(); it.hasNext(); position += len) {
            Integer key = (Integer)it.next();
            String value = "";
            len = Integer.parseInt(((FormatterAttribute)treeMap.get(key)).getLength());
            if (((FormatterAttribute)treeMap.get(key)).getMasking() != null) {
                for(int i = 0; i < len; ++i) {
                    value = value + ((FormatterAttribute)treeMap.get(key)).getMasking().substring(0, 1);
                }
            } else if (this.isStringBasedCropping) {
                value = this.setFixedLengthString(data, ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), position, ((FormatterAttribute)treeMap.get(key)).getAlign());
            } else {
                value = this.setFixedByteLengthString(data, ((FormatterAttribute)treeMap.get(key)).getLength(), ((FormatterAttribute)treeMap.get(key)).getPadding(), position, ((FormatterAttribute)treeMap.get(key)).getAlign());
            }

            encData = encData + value;
        }

        return encData;
    }

    public static String getFixedLengthString(String val, String length, String padding, String align) {
        boolean cutFromBack = false;
        if (length.startsWith("F")) {
            cutFromBack = false;
            length = length.replace("F", "");
        } else if (length.startsWith("B")) {
            cutFromBack = true;
            length = length.replace("B", "");
        }

        int len = Integer.parseInt(length);
        if (val != null && val.length() >= len) {
            return cutFromBack ? val.substring(val.length() - len, val.length()) : val.substring(0, len);
        } else {
            String pad = "";
            String lpad = "";
            String rpad = "";
            String vals = nvl(val);
            if (padding == null || padding.equals("")) {
                padding = " ";
            }

            for(int i = 0; i < len - vals.length(); ++i) {
                pad = pad + padding;
            }

            if (align.endsWith("left")) {
                rpad = pad;
            } else {
                lpad = pad;
            }

            return lpad + vals + rpad;
        }
    }

    public String setFixedLengthString(String data, String length, String padding, int position, String align) {
        String value = "";
        String temp = "";
        int len = Integer.parseInt(length);
        temp = data.substring(position, position + len);
        if (padding == null || padding.equals("")) {
            padding = " ";
        }

        if (padding.equals("0")) {
            int minusType = temp.indexOf("-");
            if (minusType > -1) {
                temp = temp.substring(minusType);
            }

            if (len < 10) {
                int ivalue = Integer.parseInt(temp);
                value = Integer.toString(ivalue);
            } else {
                long ivalue = Long.parseLong(temp);
                value = Long.toString(ivalue);
            }
        } else if (padding.equals(" ")) {
            if (align.equals("left")) {
                value = temp.replaceAll("\\s+$", "");
            } else {
                value = temp.replaceAll("^\\s+", "");
            }
        } else {
            padding = Pattern.quote(padding);
            if (align.equals("left")) {
                value = temp.replaceAll(padding + "+$", "");
            } else {
                value = temp.replaceAll("^" + padding + "+", "");
            }
        }

        return value;
    }

    public static String getFixedByteLengthString(String val, String length, String padding, String align) {
        try {
            boolean cutFromBack = false;
            if (length.startsWith("F")) {
                cutFromBack = false;
                length = length.replace("F", "");
            } else if (length.startsWith("B")) {
                cutFromBack = true;
                length = length.replace("B", "");
            }

            int len = Integer.parseInt(length);
            if (val != null && val.getBytes("EUC-KR").length >= len) {
                return cutFromBack ? new String(val.getBytes("EUC-KR"), val.getBytes("EUC-KR").length - len, len, "EUC-KR") : new String(val.getBytes("EUC-KR"), 0, len, "EUC-KR");
            } else {
                String pad = "";
                String space_pad = "";
                String lpad = "";
                String rpad = "";
                String vals = nvl(val);
                byte[] byteVal = vals.getBytes("EUC-KR");
                if (padding == null || padding.equals("")) {
                    padding = " ";
                }

                int j;
                for(j = 0; j < len - byteVal.length; j += padding.getBytes("EUC-KR").length) {
                    pad = pad + padding;
                }

                if (len - byteVal.length - pad.getBytes("EUC-KR").length != 0) {
                    logger.debug("padding의 크기가 1이 아니므로 남는부분은 space padding처리");
                }

                for(j = 0; j < len - byteVal.length - pad.getBytes("EUC-KR").length; ++j) {
                    space_pad = space_pad + " ";
                }

                if (align.endsWith("left")) {
                    rpad = pad + space_pad;
                } else {
                    lpad = space_pad + pad;
                }

                return lpad + vals + rpad;
            }
        } catch (UnsupportedEncodingException var13) {
            logger.error("포매터 항목값 인코딩 실패");
            var13.printStackTrace();
            return "";
        }
    }

    public String setFixedByteLengthString(String data, String length, String padding, int position, String align) {
        String value = "";

        try {
            String temp = "";
            int len = Integer.parseInt(length);
            temp = new String(data.getBytes("EUC-KR"), position, len, "EUC-KR");
            if (padding == null || padding.equals("")) {
                padding = " ";
            }

            if (padding.equals("0")) {
                int minusType = temp.indexOf("-");
                if (minusType > -1) {
                    temp = temp.substring(minusType);
                }

                int ivalue = Integer.parseInt(temp);
                value = Integer.toString(ivalue);
            } else if (padding.equals(" ")) {
                if (align.equals("left")) {
                    value = temp.replaceAll("\\s+$", "");
                } else {
                    value = temp.replaceAll("^\\s+", "");
                }
            } else {
                padding = Pattern.quote(padding);
                if (align.equals("left")) {
                    value = temp.replaceAll(padding + "+$", "");
                } else {
                    value = temp.replaceAll("^" + padding + "+", "");
                }
            }
        } catch (UnsupportedEncodingException var11) {
            logger.error("포매터 항목값 디코딩 실패");
            var11.printStackTrace();
        }

        return value;
    }

    public static String nvl(String val) {
        return nvl(val, "");
    }

    public static String nvl(String val, String nval) {
        return val == null ? nval : val;
    }
}
