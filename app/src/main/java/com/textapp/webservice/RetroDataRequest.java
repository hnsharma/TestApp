package com.textapp.webservice;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedInput;

/**
 * Created by Asus on 13-07-2015.
 */
public interface RetroDataRequest {


    @POST("/{path1}/{path2}")
    public void dataRequest(@Path("path1") String path1, @Path("path2") String path2, @Body TypedJsonString bytes,  Callback<String> cb);
    @POST("/{path1}/{path2}")
    public void dataRequestObject(@Path("path1") String path1, @Path("path2") String path2, @Body TypedJsonString bytes, Callback<BaseResponseData> cb);
}
