package com.textapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.converter.ConversionException;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;

/**
 * Created by Asus on 13-02-2016.
 */
public class Utils {

    public static final boolean isOnline(Context context) {

        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected())
            return true;
        return false;
    }

    public  static String TypedToString(TypedInput body)
    {
        String charset = "UTF-8";
        if (body.mimeType() != null) {
            charset = MimeUtil.parseCharset(body.mimeType(), charset);
        }
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(body.in(), charset);
            BufferedReader br=new BufferedReader(isr);
            String read = null;
            StringBuffer sb = new StringBuffer();
            while((read = br.readLine()) != null) {
                sb.append(read);
            }
            String response = sb.toString();
            Log.v("data", "data  " + response);
            return response;
        } catch (IOException e) {
        } catch (JsonParseException e) {
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }

}
