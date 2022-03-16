package com.kovan.lib.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public DateUtil() {
    }

    /**
     * 날짜를 파리미터로 넘겨준 형식대로 취득한다。
     * @param pattern (YYMMDD날짜형식)
     * @return String 취득한형식화된 날짜
     */
    public static String getDateTimeByPattern(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREAN);
        String dateString = formatter.format(new Date(System.currentTimeMillis()));
        return dateString;
    }


    public static String getYYYYMMDDHH24MISS() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        Date currentTime = new Date();
        String strDT = formatter.format(currentTime);
        return strDT;
    }

    public static String getYYYYMMDDHH24MISSS() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDT = dayTime.format(new Date(time));
        return strDT;
    }
}