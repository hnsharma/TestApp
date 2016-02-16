package com.textapp.model;

import com.textapp.webservice.BaseResponseData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Asus on 14-02-2016.
 */
public class ProfileModel extends BaseResponseData implements Serializable {

    private String   email;
    private String   email_verified;
    private String   clientID;
    private String   updated_at;
    private String   picture;
    private String   user_id;
    private String   name;
    private String   nickname;
    private String   created_at;
    private String   global_client_id;
    private ArrayList<IdentitiesModel>  identities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getGlobal_client_id() {
        return global_client_id;
    }

    public void setGlobal_client_id(String global_client_id) {
        this.global_client_id = global_client_id;
    }

    public ArrayList<IdentitiesModel> getIdentities() {
        return identities;
    }

    public void setIdentities(ArrayList<IdentitiesModel> identities) {
        this.identities = identities;
    }
}
