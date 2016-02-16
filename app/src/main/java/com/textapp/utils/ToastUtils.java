package com.textapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Asus on 14-02-2016.
 */
public class ToastUtils {

    public static void showToast(Context  context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static void showToast(Context  context,int message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
