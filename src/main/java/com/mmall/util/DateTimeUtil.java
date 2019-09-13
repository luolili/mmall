package com.mmall.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class DateTimeUtil {
    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
    public static final String STANDARD_FORMAT = "yyyy-MM-dd hh:mm:ss";
    //joda-time
    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = formatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static Date strToDate(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = formatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
}
