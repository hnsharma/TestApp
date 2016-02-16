package com.textapp.webservice;

import retrofit.mime.TypedString;

/**
 * Created by Asus on 13-07-2015.
 */
public class TypedJsonString extends TypedString {
    public TypedJsonString(String body) {
        super(body);
    }

    @Override public String mimeType() {
        return "application/json";
    }
}
