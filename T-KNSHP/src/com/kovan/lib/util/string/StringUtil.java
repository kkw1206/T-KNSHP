package com.kovan.lib.util.string;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class StringUtil {
    public StringUtil() {
    }

    public static String getErrorLogForEMS(String ErrorCd, String ErrorMsg) {
        String result = "ES#|ERROR_CD:" + ErrorCd + "|ERROR_MSG:" + ErrorMsg + "|EE#";
        return result;
    }

    public static String getFixedLengthString(String val, String length, String padding, String align) {
        int len = Integer.parseInt(length);
        if (val != null && val.length() >= len) {
            return val.substring(0, len);
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
                    System.out.println("padding의 크기가 1이 아니므로 남는부분은 space padding처리");
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
            System.out.println("포매터 항목값 인코딩 실패");
            var13.printStackTrace();
            return "";
        }
    }

    public static String nvl(String val) {
        return nvl(val, "");
    }

    public static String nvl(String val, String nval) {
        return val == null ? nval : val;
    }

    public static String getPrintStacTraceString(Exception e) {
        String tempValue = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        e.printStackTrace(printStream);
        tempValue = out.toString();
        int returnValueLen = tempValue.length();
        return tempValue.substring(0, 5000 > returnValueLen ? returnValueLen : 5000);
    }

    public static String toComma(String inValue) {
        if (inValue != null && inValue.length() > 0) {
            int idx = inValue.indexOf(".");
            String str = inValue;
            String rightVal = "";
            if (idx > 0) {
                str = inValue.substring(0, idx);
                rightVal = inValue.substring(idx + 1, inValue.length());
                if (Integer.valueOf(rightVal) > 0) {
                    rightVal = inValue.substring(idx, inValue.length());
                } else {
                    rightVal = "";
                }
            }

            str = str + "";
            str = replace(str, ",", "");
            String statValue = "";
            String strValue = "";
            String modValue = "";
            if (str.substring(0, 1).equals("-")) {
                statValue = str.substring(1, str.length());
            } else {
                statValue = str;
            }

            int tmp1 = statValue.length();
            if (tmp1 > 3) {
                int tmp2 = tmp1 / 3;
                int tmp3 = tmp1 % 3;
                if (tmp3 > 0) {
                    strValue = strValue + statValue.substring(0, tmp3) + ",";
                    modValue = statValue.substring(tmp3);
                } else {
                    modValue = statValue;
                }

                for(int i = 0; i < tmp2; ++i) {
                    if (i == tmp2 - 1) {
                        strValue = strValue + modValue.substring(i * 3, i * 3 + 3);
                    } else {
                        strValue = strValue + modValue.substring(i * 3, i * 3 + 3) + ",";
                    }
                }
            } else {
                strValue = statValue;
            }

            String srtValue = "";
            if (str.substring(0, 1).equals("-")) {
                srtValue = "-" + strValue;
            } else {
                srtValue = strValue;
            }

            return srtValue + rightVal;
        } else {
            return "";
        }
    }

    public static String toComma(double inValue) {
        return toComma(Double.toString(inValue));
    }

    public static String toComma(int inValue) {
        return toComma(Integer.toString(inValue));
    }

    public static String replace(String target, String o, String n) {
        StringBuffer result = new StringBuffer();
        int idx = 0;
        if (target != null && !target.equals("")) {
            while((idx = target.indexOf(o)) != -1) {
                result.append(target.substring(0, idx));
                result.append(n);
                target = target.substring(idx + o.length());
            }

            result.append(target);
            return result.toString();
        } else {
            return "";
        }
    }
}
