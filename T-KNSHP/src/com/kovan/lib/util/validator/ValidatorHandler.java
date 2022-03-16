package com.kovan.lib.util.validator;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class ValidatorHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String encoding = "UTF-8";
    private Unmarshaller unmarshaller;
    private static DataListTag dataList = new DataListTag();
    private static DataTag data = new DataTag();
    private static InvalidDataTag invalidData = new InvalidDataTag();

    public ValidatorHandler() {
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void setEncoding(String encoding) {
        if (encoding != null && !encoding.isEmpty()) {
            this.encoding = encoding;
        }

    }

    public static InvalidDataTag getInvalidData() {
        return invalidData;
    }

    public void loadXml(String path) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(new Class[]{DataListTag.class});
        StreamSource source = new StreamSource(path);
        this.unmarshaller = jc.createUnmarshaller();
        JAXBElement<DataListTag> root = this.unmarshaller.unmarshal(source, DataListTag.class);
        dataList = (DataListTag)root.getValue();
        this.setEncoding(dataList.getEncoding());
    }

    public boolean validator(HashMap<String, String> dataMap) {
        Iterator var2 = dataList.getDataList().iterator();

        DataTag data;
        do {
            if (!var2.hasNext()) {
                return true;
            }

            data = (DataTag)var2.next();
        } while(this.checkValidator(data, data.getName(), dataMap));

        return false;
    }

    public boolean validatorFl(String dataStr, LinkedHashMap<String, String> nameLength) {
        byte[] dataByte;
        try {
            dataByte = dataStr.getBytes(this.encoding);
        } catch (UnsupportedEncodingException var13) {
            this.logger.error("UnsupportedEncodingException");
            dataByte = dataStr.getBytes();
        }

        HashMap<String, String> dataMap = new HashMap();
        Set<String> set = nameLength.keySet();
        Iterator<String> iter = set.iterator();

        int length;
        for(int sumLength = 0; iter.hasNext(); sumLength += length) {
            String key = ((String)iter.next()).toString();
            length = Integer.parseInt((String)nameLength.get(key));
            String value = "";

            try {
                value = new String(dataByte, sumLength, length, this.encoding);
            } catch (UnsupportedEncodingException var12) {
                this.logger.error("UnsupportedEncodingException");
                value = new String(dataByte, sumLength, length);
            }

            dataMap.put(key, value);
            this.logger.debug(key + " : " + value);
        }

        return this.validator(dataMap);
    }

    public boolean validatorJson(JSONObject reqJson) {
        HashMap<String, String> reqMap = new HashMap();
        Set<String> set = reqJson.keySet();
        Iterator iter = set.iterator();

        while(iter.hasNext()) {
            String key = ((String)iter.next()).toString();
            String value = reqJson.get(key).toString();
            reqMap.put(key, value);
        }

        return this.validator(reqMap);
    }

    private boolean checkValidator(DataTag pData, String name, HashMap reqMap) {
        this.logger.info("===================[ " + name + " ]===================");
        boolean rtnBool = true;
        rtnBool = this.checkAttr(pData, name, reqMap);
        this.logger.info("===================[ " + name + " ]===================");
        return rtnBool;
    }

    private boolean checkAttr(DataTag data, String name, HashMap requestMap) {
        Validate vd = new Validate(this.encoding);
        boolean val = false;
        if (data.getRequired() != null && !"".equals(data.getRequired())) {
            val = vd.validateRequired(data, name, requestMap);
            this.logger.info("Required : " + val);
            if (!val) {
                this.setInvalidData(data, "required");
                return val;
            }
        }

        if (data.getType() != null && !"".equals(data.getType())) {
            val = vd.validateType(data, name, requestMap);
            this.logger.info("Type : " + val);
            if (!val) {
                this.setInvalidData(data, "type");
                return val;
            }
        }

        if (data.getFixLength() != null && !"".equals(data.getFixLength())) {
            val = vd.validateFixLength(data, name, requestMap);
            this.logger.info("FixLength : " + val);
            if (!val) {
                this.setInvalidData(data, "fixLength");
                return val;
            }
        }

        if (data.getMaxLength() != null && !"".equals(data.getMaxLength())) {
            val = vd.validateMaxLength(data, name, requestMap);
            this.logger.info("MaxLength : " + val);
            if (!val) {
                this.setInvalidData(data, "maxLength");
                return val;
            }
        }

        if (data.getMinLength() != null && !"".equals(data.getMinLength())) {
            val = vd.validateMinLength(data, name, requestMap);
            this.logger.info("MinLength : " + val);
            if (!val) {
                this.setInvalidData(data, "minLength");
                return val;
            }
        }

        if (data.getPattern() != null && !"".equals(data.getPattern())) {
            val = vd.validatePattern(data, name, requestMap);
            this.logger.info("Pattern : " + val);
            if (!val) {
                this.setInvalidData(data, "pattern");
                return val;
            }
        }

        if (data.getValue() != null && !"".equals(data.getValue())) {
            val = vd.validateValue(data, name, requestMap);
            this.logger.info("Value : " + val);
            if (!val) {
                this.setInvalidData(data, "value");
                return val;
            }
        }

        return val;
    }

    private void setInvalidData(DataTag data, String attr) {
        Iterator var3 = data.getInvalidDataList().iterator();

        while(var3.hasNext()) {
            InvalidDataTag iData = (InvalidDataTag)var3.next();
            this.logger.debug("name : " + data.getName() + "; attr : " + attr + "; iData.getAttr : " + iData.getAttr());
            invalidData.setName(data.getName());
            if (attr.equals(iData.getAttr())) {
                invalidData.setAttr(iData.getAttr());
                invalidData.setMessage(iData.getMessage());
                break;
            }

            invalidData.setMessage(iData.getMessage());
        }

    }
}
