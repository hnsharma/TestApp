package com.textapp.model;

import java.io.Serializable;

/**
 * Created by Asus on 14-02-2016.
 */
public class IdentitiesModel implements Serializable {
    private String user_id;
    private String provider;
    private String connection;
    private boolean isSocial;
    private String access_token;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public boolean getIsSocial() {
        return isSocial;
    }

    public void setIsSocial(boolean isSocial) {
        this.isSocial = isSocial;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
