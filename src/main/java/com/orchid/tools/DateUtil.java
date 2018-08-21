package com.orchid.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ljg on 2018/4/1.
 */
public class DateUtil {
    private static final String date_format = "yyyy-MM-dd";
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(date_format);
        }
    };

    public static SimpleDateFormat getSimpleDateFormat() {
        return threadLocal.get();
    }

    public static String format(Date date) {
        return getSimpleDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getSimpleDateFormat().parse(strDate);
    }

    public static void main(String[] args) throws InterruptedException {
        String strDate1 = "2018-04-01";
        final String strDate2 = "2018-05-01";
        try {
            System.out.println(parse(strDate1));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(){
            public void run(){
                try {
                    System.out.println(parse(strDate2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread.join();
    }
}