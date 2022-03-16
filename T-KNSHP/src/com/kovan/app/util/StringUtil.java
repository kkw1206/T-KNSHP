package com.kovan.app.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StringUtil {

    /**
     * <pre>
     * 고정길이 String 생성 메소드
     * </pre>
     * @param val String 값
     * @param length 고정길이
     * @param padding 패딩값
     * @param align 정렬
     * @return 고정길이스트링
     */
    public static String getFixedLengthString(String val, String length, String padding, String align) {
        int len = Integer.parseInt(length);
        if ( val != null && val.length() >= len ) {
            return val.substring(0, len);
        } else {
            String pad = "";
            String lpad = "";
            String rpad = "";
            String vals = nvl(val);

            if ( padding == null || padding.equals("") ) {
                padding = " ";
            }

            for ( int i = 0 ; i < len - vals.length() ; ++i ) {
                pad += padding;
            }

            if ( align.endsWith("left") ) {
                rpad = pad;
            } else {
                lpad = pad;
            }

            return lpad + vals + rpad;
        }
    }



    public static String nvl(String val) {
        return nvl(val, "");
    }

    public static String nvl(String val, String nval) {
        return val == null ? nval : val;
    }

    /**
     * <pre>
     * StackTrace String 변환 메소드
     * </pre>
     * @param e Exception
     * @return 에러스트링
     */
    public static String getPrintStacTraceString(Exception e) {
        String tempValue   =   "";

        ByteArrayOutputStream out =   new ByteArrayOutputStream();
        PrintStream printStream  =   new PrintStream(out);
        e.printStackTrace(printStream);
        tempValue     =   out.toString();
        String returnValue = tempValue.replaceAll("\\s", "");
        int returnValueLen = returnValue.length();

        return returnValue.substring(0, ( 120 > returnValueLen)?returnValueLen:120);

    }
}
