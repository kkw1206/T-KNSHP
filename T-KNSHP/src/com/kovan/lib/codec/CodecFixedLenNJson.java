package com.kovan.lib.codec;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

public class CodecFixedLenNJson implements CodecBase {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    private HeaderTemplate headerTemplate;
    private String encoding = "UTF-8";
    private HashMap<String, String> headerMap = new HashMap();
    private String headerStr;
    private int headerLength;
    private HashMap<String, String> bodyMap = new HashMap();
    private String bodyStr;

    public CodecFixedLenNJson() {
    }

    public CodecFixedLenNJson(String encoding) {
        encoding = this.encoding;
    }

    public void setHeaderTemplate(HeaderTemplate ht) {
        logger.debug("setHeaderTemplate");
        this.headerTemplate = ht;
        this.headerLength = ht.getHeaderTemplateLength();
    }

    public void setAttributeHeader(String key, String value) {
        this.headerMap.put(key, value);
    }

    public void setAttributeData(String key, String value) {
        this.bodyMap.put(key, value);
    }

    public String encodeData() {
        String retValues = null;
        JSONObject jsonobj = new JSONObject();
        jsonobj.putAll(this.bodyMap);
        retValues = jsonobj.toJSONString();
        jsonobj.clear();
        this.bodyStr = retValues;
        return retValues;
    }

    public String setHeader() throws UnsupportedEncodingException {
        this.logger.info("================================[ setHeader ]================================");

        try {
            String data = "";
            List<HeaderAttribute> headerTemplateList = this.headerTemplate.getHeaderTemplateAttributeList();

            this.logger.debug("headerTemplateList.size():"+Integer.toString(headerTemplateList.size()));
            for(int index = 0; index < headerTemplateList.size(); ++index) {
                HeaderAttribute entry = (HeaderAttribute)headerTemplateList.get(index);
                String name = entry.getName();
                boolean isLengthField = entry.isLengthField();

                if (this.headerMap.containsKey(name) && !isLengthField) {
                    data = data + this.getFixedByteLengthString((String)this.headerMap.get(name), entry.getLength(), entry.getPadding(), entry.getAlign());
                    this.logger.info("key[" + String.format("%-20s", name) + "]" + " " + "value[" + (String)this.headerMap.get(name) + "]");
                } else if (isLengthField) {
                    data = data + this.getFixedByteLengthString((String)this.headerMap.get(name), entry.getLength(), entry.getPadding(), entry.getAlign());

                    this.logger.debug("index["+Integer.toString(index)+"] isLengthField:"+Boolean.toString(isLengthField)+" data:"+data);
                    this.logger.debug("this.bodyStr.getBytes(this.encoding).length:"+Integer.toString(this.bodyStr.getBytes(this.encoding).length));
                    this.logger.debug(" entry.getLength():["+ Integer.toString(entry.getLength())+"]");
                    this.logger.debug(" entry.getPadding():["+ entry.getPadding() +"]");
                    this.logger.debug(" entry.getAlign():["+ entry.getAlign()+"]");
                    this.logger.debug(" entry.getPadding():["+ entry.getPadding()+"]");

                } else {
                    this.logger.error("Not Found Item[" + name + "]");
                }

            }

            this.logger.info("============================================================================");
            this.headerStr = data;
            return data;
        } catch (UnsupportedEncodingException var7) {
            this.logger.error("[T-LIB/CODEC] 미지원 charset으로 인한 encoding 실패");
            throw var7;
        }

    }
    public String encodeHeader() throws UnsupportedEncodingException {
        this.logger.info("================================[ Encoding ]================================");

        try {
            String data = "";
            List<HeaderAttribute> headerTemplateList = this.headerTemplate.getHeaderTemplateAttributeList();

            this.logger.debug("headerTemplateList.size():"+Integer.toString(headerTemplateList.size()));
            for(int index = 0; index < headerTemplateList.size(); ++index) {
                HeaderAttribute entry = (HeaderAttribute)headerTemplateList.get(index);
                String name = entry.getName();
                boolean isLengthField = entry.isLengthField();

                if (this.headerMap.containsKey(name) && !isLengthField) {
                    data = data + this.getFixedByteLengthString((String)this.headerMap.get(name), entry.getLength(), entry.getPadding(), entry.getAlign());
                    this.logger.info("key[" + String.format("%-20s", name) + "]" + " " + "value[" + (String)this.headerMap.get(name) + "]");
                } else if (isLengthField) {
                    data = data + this.getFixedByteLengthString(Integer.toString(this.bodyStr.getBytes(this.encoding).length), entry.getLength(), entry.getPadding(), entry.getAlign());

//                    this.logger.debug("index["+Integer.toString(index)+"] isLengthField:"+Boolean.toString(isLengthField)+"data:"+data);
//                    this.logger.debug("this.bodyStr.getBytes(this.encoding).length:"+Integer.toString(this.bodyStr.getBytes(this.encoding).length));
//                    this.logger.debug(" entry.getLength():["+ Integer.toString(entry.getLength())+"]");
//                    this.logger.debug(" entry.getPadding():["+ entry.getPadding() +"]");
//                    this.logger.debug(" entry.getAlign():["+ entry.getAlign()+"]");
//                    this.logger.debug(" entry.getPadding():["+ entry.getPadding()+"]");

                } else {
                    this.logger.error("Not Found Item[" + name + "]");
                }

            }

            this.logger.info("============================================================================");
            this.headerStr = data;
            return data;
        } catch (UnsupportedEncodingException var7) {
            this.logger.error("[T-LIB/CODEC] 미지원 charset으로 인한 encoding 실패");
            throw var7;
        }
    }

    public String encode() throws UnsupportedEncodingException {
        if (this.headerStr == null && this.bodyStr == null) {
            this.encodeData();
            this.encodeHeader();
        }

        return this.headerStr + this.bodyStr;
    }

    public void decodeHeader(String strHeader) {
        this.logger.info("================================[ Decoding ]================================");
        int position = 0;
        String value = "";
        List<HeaderAttribute> headerTemplateList = this.headerTemplate.getHeaderTemplateAttributeList();

        for(int index = 0; index < headerTemplateList.size(); ++index) {
            HeaderAttribute entry = (HeaderAttribute)headerTemplateList.get(index);
            String name = entry.getName();
            value = this.setFixedByteLengthString(strHeader, entry.getLength(), entry.getPadding(), position);
            position += entry.getLength();
            this.logger.info("key[" + String.format("%-20s", name) + "]" + " " + "value[" + value + "]");
            this.headerMap.put(name, value);
        }

        this.headerLength = position;
        this.logger.info("============================================================================");
    }

    public void decodeData(String reqvalues) throws ParseException {
        try {
            JSONParser parser = new JSONParser();
            Object parseobj = parser.parse(reqvalues);
            this.bodyMap = (HashMap)parseobj;
        } catch (ParseException var4) {
            this.logger.error("[T-LIB/CODEC]Body가 JSON 형식이 아닙니다. Parse 실패");
            this.bodyMap = null;
            throw var4;
        }
    }

    public void decode(String strPacket) throws ParseException {
        if (this.headerMap.isEmpty() && this.bodyMap.isEmpty()) {
            this.decodeHeader(strPacket);
            this.decodeData(strPacket.substring(this.headerLength));
        }

    }

    public String getAttributeData(String key) {
        return (String)this.bodyMap.get(key);
    }

    public String getAttributeHeader(String key) {
        if (!this.headerMap.containsKey(key)) {
            this.logger.error("KEY[" + key + "] Not Found");
        }

        return (String)this.headerMap.get(key);
    }

    public String getFixedByteLengthString(String val, int len, String padding, String align) {
        try {
            boolean cutFromBack = false;
            if (val != null && val.getBytes(this.encoding).length >= len) {
                return cutFromBack ? new String(val.getBytes(this.encoding), val.getBytes(this.encoding).length - len, len, this.encoding) : new String(val.getBytes(this.encoding), 0, len, this.encoding);
            } else {
                String pad = "";
                String space_pad = "";
                String lpad = "";
                String rpad = "";
                String vals = val == null ? "" : val;
                byte[] byteVal = vals.getBytes(this.encoding);
                if (padding == null || padding.equals("")) {
                    padding = " ";
                }

                int j;
                for(j = 0; j < len - byteVal.length; j += padding.getBytes(this.encoding).length) {
                    pad = pad + padding;
                }

                if (len - byteVal.length - pad.getBytes(this.encoding).length != 0) {
                    this.logger.debug("padding의 크기가 1이 아니므로 남는부분은 space padding처리");
                }

                for(j = 0; j < len - byteVal.length - pad.getBytes(this.encoding).length; ++j) {
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
            this.logger.error("미지원 charset으로 인한 encoding 실패");
            var13.printStackTrace();
            return "";
        }
    }

    public String setFixedByteLengthString(String data, int len, String padding, int position) {
        String value = "";

        try {
            String temp = "";
            temp = new String(data.getBytes(this.encoding), position, len, this.encoding);
            if (padding == null || padding.equals("")) {
                padding = " ";
            }

            if (padding.equals("0")) {
                int ivalue = Integer.parseInt(temp);
                value = Integer.toString(ivalue);
            } else {
                value = temp.replace(padding, "");
            }
        } catch (UnsupportedEncodingException var8) {
            this.logger.error("미지원 charset으로 인한 decoding 실패");
            var8.printStackTrace();
        }

        return value;
    }

    public int getHeaderLength() {
        return this.headerLength;
    }

    public void setBodyStr(String bodyStr) {
        this.bodyStr = bodyStr;
    }

    public void setBodyMap(HashMap<String, String> map) {
        this.bodyMap.putAll(map);
    }

    public void setHeaderMap(HashMap<String, String> map) {
        this.headerMap.putAll(map);
    }
    public HashMap<String, String> getBodyMap() {
        return this.bodyMap;
    }

    public String getBodyStr() {
        return this.bodyStr;
    }

    public String getHeaderStr() {
        return this.headerStr;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
