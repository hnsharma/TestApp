package com.textapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Asus on 14-02-2016.
 */
public class ValidationUtils
{
    public static boolean isEmpty(String s)
    {
       return s.trim().isEmpty();
    }
    public static boolean isEmptyNotTrim(String s)
    {
        return s.isEmpty();
    }
    public static boolean isValidEmail(String s)
    {
        String emailExp = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,10}$";
        Pattern pattern = Pattern.compile(emailExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s.trim());
        return matcher.matches();
    }
}
