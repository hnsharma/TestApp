package com.textapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 14-02-2016.
 */
public class DateUtils {

   public static final String DATE_FORMATE="dd-MMM yyyy";
    public static String DateToString(long l)
    {
        String dateString =null;
        Date date = new Date();
        date.setTime(l);
        SimpleDateFormat  simpleDateFormat =new SimpleDateFormat(DATE_FORMATE);
        dateString= simpleDateFormat.format(date);
        return dateString;

    }
}
