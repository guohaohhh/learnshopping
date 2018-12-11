package com.neuedu.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {
    private static final String STANDARD_FORMAT="yyy-MM-dd HH:mm:ss";
    //将时间转成字符串
    public static String dateToStr(Date date,String formate){
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(formate);
    }
    public static String dateToStr(Date date){
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
    /**
     * 将字符串转化为时间
     * */
    public static Date strToDate(String str){
        DateTimeFormatter dateTimeFormat= DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime=dateTimeFormat.parseDateTime(str);
        return dateTime.toDate();
    }
    public static Date strToDate(String str,String format){
        DateTimeFormatter dateTimeFormat= DateTimeFormat.forPattern(format);
        DateTime dateTime=dateTimeFormat.parseDateTime(str);
        return dateTime.toDate();
    }

/**
 * 测试时间
 * */
//    public static void main(String[] args) {
//        System.out.println(dateToStr(new Date()));
//        System.out.println(strToDate("2018-12-10 11:11:19"));
//    }





}
