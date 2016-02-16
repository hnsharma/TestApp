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
public class GsonConverterObject<T>  implements Converter {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    RequestedServiceDataModel requestedServiceDataModel;

   public GsonConverterObject(RequestedServiceDataModel requestedServiceDataModel) {
        this.requestedServiceDataModel      =   requestedServiceDataModel;
    }

   /* @Override
    public T fromBody(ResponseBody body) throws IOException {
        Reader in = body.charStream();
        try {
            return typeAdapter.fromJson(in);
        } finally {
            try {
                in.close();
            } catch (IOException ignored) {
            }
        }
    }*/

   /* @Override public RequestBody toBody(T value) {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        try {
            typeAdapter.toJson(writer, value);
            writer.flush();
        } catch (IOException e) {
            throw new AssertionError(e); // Writing to Buffer does no I/O.
        }
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }*/

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
            Log.v("data","data  ooo  "+ response);
            try{
                BaseResponseData respomnse = JsonParser.parseJson(response, requestedServiceDataModel.getResponseType(),requestedServiceDataModel.getBaseRequestData().getHashMapGson());
                return respomnse;
            }
            catch (Exception e)
            {
                throw new ConversionException(e);
            }
        //return response;
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

