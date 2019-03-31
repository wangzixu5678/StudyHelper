package com.wzx.studyhelper.utils;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtils {

    private final static String format1 = "yyyy-MM-dd HH:mm:ss";

    public static String getFormatTime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format1);

        return simpleDateFormat.format(new Date(time));
    }
}
