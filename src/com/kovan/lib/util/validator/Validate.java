package com.kovan.lib.util.validator;

import com.kovan.lib.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validate {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String encoding = "UTF-8";

    public Validate() {
    }

    public Validate(String encoding) {
        this.encoding = encoding;
    }

    private boolean nullCheck(Object obj) {
        return this.nullCheck(obj.toString());
    }

    private boolean nullCheck(String str) {
        return str != null && !str.isEmpty();
    }

    protected boolean validateRequired(DataTag data, String name, HashMap dataMap) {
        if (this.nullCheck(data.getRequired()) && "Y".equals(data.getRequired().toUpperCase())) {
            if (dataMap.get(name) != null && !dataMap.get(name).toString().isEmpty()) {
                this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", required : " + data.getRequired());
                return true;
            }

            this.logger.info("The request data is not valid. required : " + data.getRequired());
            this.logger.debug("id : " + name + ", value : null");
        }

        return false;
    }

    protected boolean validateType(DataTag data, String name, HashMap dataMap) {
        if (this.nullCheck(dataMap.get(name)) && !data.getType().isEmpty()) {
            String regex = "";

            for(int i = 0; i < data.getType().length(); ++i) {
                if (data.getType().charAt(i) == 'N') {
                    regex = regex + "0-9";
                } else if (data.getType().charAt(i) == 'A') {
                    regex = regex + "a-zA-Z";
                } else if (data.getType().charAt(i) == 'C') {
                    regex = regex + "~!@#%^()_=+/:?.,";
                } else {
                    if (data.getType().charAt(i) != 'H') {
                        this.logger.info("Type is not valid. type : " + data.getType());
                        return false;
                    }

                    regex = regex + "ㄱ-ㅎ가-힣";
                }
            }

            regex = "^[" + regex + "\\s]*$";
            if (Pattern.matches(regex, dataMap.get(name).toString())) {
                this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", type : " + data.getType());
                return true;
            }

            this.logger.info("The request data is not valid. type : " + data.getType());
            this.logger.debug("id : " + name + ", value : " + dataMap.get(name));
        }

        return false;
    }

    protected boolean validateFixLength(DataTag data, String name, HashMap dataMap) {
        if (this.nullCheck(dataMap.get(name))) {
            try {
                int length = dataMap.get(name).toString().getBytes(this.encoding).length;
                if (Integer.parseInt(data.getFixLength()) == length) {
                    this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", fixLength : " + data.getFixLength());
                    return true;
                }

                this.logger.info("The request data is not valid. fixlength : " + data.getFixLength());
                this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", length : " + length);
            } catch (UnsupportedEncodingException var5) {
                this.logger.error("UnsupportedEncodingException.");
                this.logger.error("id : " + name + ", value : " + dataMap.get(name));
                return false;
            } catch (NumberFormatException var6) {
                this.logger.error("The fixlength is not number. fixlength : " + data.getFixLength());
                return false;
            }
        }

        return false;
    }

    protected boolean validateMaxLength(DataTag data, String name, HashMap dataMap) {
        if (this.nullCheck(dataMap.get(name)) && !data.getMaxLength().isEmpty()) {
            try {
                int length = dataMap.get(name).toString().getBytes(this.encoding).length;
                if (Integer.parseInt(data.getMaxLength()) >= length) {
                    this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", maxLength : " + data.getMaxLength());
                    return true;
                }

                this.logger.info("The request data is not valid. maxlength : " + data.getMaxLength());
                this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", length : " + length);
            } catch (UnsupportedEncodingException var5) {
                this.logger.error("UnsupportedEncodingException.");
                this.logger.error("id : " + name + ", value : " + dataMap.get(name));
                return false;
            } catch (NumberFormatException var6) {
                this.logger.error("The maxlength is not number. maxlength : " + data.getMaxLength());
                return false;
            }
        }

        return false;
    }

    protected boolean validateMinLength(DataTag data, String name, HashMap dataMap) {
        if (this.nullCheck(dataMap.get(name)) && !data.getMinLength().isEmpty()) {
            try {
                int length = dataMap.get(name).toString().getBytes(this.encoding).length;
                if (Integer.parseInt(data.getMinLength()) >= length) {
                    this.logger.info("The request data is not valid. minlength : " + data.getMinLength());
                    this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", length : " + length);
                    return true;
                }

                this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", minLength : " + data.getMinLength());
            } catch (UnsupportedEncodingException var5) {
                this.logger.error("UnsupportedEncodingException.");
                this.logger.error("id : " + name + ", value : " + dataMap.get(name));
                return false;
            } catch (NumberFormatException var6) {
                this.logger.error("The minlength is not number. minlength : " + data.getMinLength());
                return false;
            }
        }

        return false;
    }

    protected boolean validatePattern(DataTag data, String name, HashMap dataMap) {
        if (this.nullCheck(dataMap.get(name)) && !data.getPattern().isEmpty()) {
            try {
                if (Pattern.matches(data.getPattern(), dataMap.get(name).toString())) {
                    this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", pattern : " + data.getPattern());
                    return true;
                }

                this.logger.info("The request data is not valid. Pattern : " + data.getPattern());
            } catch (PatternSyntaxException var5) {
                this.logger.error("Unchecked exception thrown to indicate a syntax error in aregular-expression pattern. : " + data.getPattern());
                return false;
            }
        }

        return false;
    }

    protected boolean validateValue(DataTag data, String name, HashMap dataMap) {
        if (this.nullCheck(dataMap.get(name)) && !data.getValue().isEmpty()) {
            String pVal = data.getValue();
            String jVal = dataMap.get(name).toString();
            this.logger.debug("id : " + name + ", value : " + dataMap.get(name) + ", value : " + pVal + " , split : " + StringUtil.nvl(data.getSplit()));
            if (!data.getSplit().isEmpty()) {
                String[] pp = pVal.split(data.getSplit());

                for(int i = 0; i < pp.length; ++i) {
                    if (pp[i].equals(jVal)) {
                        return true;
                    }
                }

                this.logger.info("The request data is not valid. value : " + data.getValue());
            } else {
                if (pVal.equals(jVal)) {
                    return true;
                }

                this.logger.info("The request data is not valid. value : " + data.getValue());
            }
        }

        return false;
    }
}