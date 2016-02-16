package com.textapp.webservice;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.squareup.okhttp.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;


/**
 * Created by Asus on 14-07-2015.
 */
public class GsonConverter<T>  implements Converter {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");


   public GsonConverter() {

    }



    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
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
            Log.v("data","data  "+ response);
        return response;
        } catch (IOException e) {
            throw new ConversionException(e);
        } catch (JsonParseException e) {
            throw new ConversionException(e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override
    public TypedOutput toBody(Object object) {
        return null;
    }
}

