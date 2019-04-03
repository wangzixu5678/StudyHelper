package com.wzx.studyhelper.utils;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtils {

    private final static String format1 = "yyyy-MM-dd HH:mm:ss";
    private final static String format2 = "yyyy-MM-dd HH:mm";
    public static String getFormatTime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format1);

        return simpleDateFormat.format(new Date(time));
    }

    public static String getFormatTime2(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format2);

        return simpleDateFormat.format(new Date(time));
    }
}
