package com.textapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.textapp.delegate.ResponseDelegate;
import com.textapp.delegate.ResponseType;
import com.textapp.model.ProfileModel;
import com.textapp.model.SignUpInModel;
import com.textapp.utils.PreferenceConnector;
import com.textapp.utils.Utils;
import com.textapp.webservice.BaseRequestData;
import com.textapp.webservice.BaseResponseData;
import com.textapp.webservice.RequestedServiceDataModel;

import java.util.Arrays;

/**
 * Created by Asus on 13-02-2016.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener ,ResponseDelegate{
    CallbackManager callbackManager;
    RequestedServiceDataModel requestDataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void startActivity(Class<?> aClass)
    {
        startActivity(new Intent(this,aClass));
    }
    public  void setClick(int id)
    {
        findViewById(id).setOnClickListener(this);
    }
    public  void setClick(View view)
    {
        view.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back:
                finish();
                break;
            case R.id.fb_login:
                LoginManager.getInstance().logInWithReadPermissions(BaseActivity.this, Arrays.asList("public_profile"));
                break;
        }

    }


    @Override
    public void onSuccess(BaseResponseData response, BaseRequestData tag) {
        switch (tag.getTag())
        {
            case ResponseType.TAG_KEY_ACCESS_TOKEN:
                getProfile(((SignUpInModel)response).getId_token());
                break;
            case ResponseType.TAG_KEY_PROFILE:
                PreferenceConnector.saveProfile(this,(ProfileModel)response);
                Intent intent  = new Intent(this,ProfileActivity.class);
                Bundle  bundle =new Bundle();
                bundle.putSerializable("data",(ProfileModel)response);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onSuccess(String response, BaseRequestData tag) {

    }

    @Override
    public void onFailure(String errorMsg, BaseRequestData tag) {

    }

    @Override
    public void onNoNetwork(String netwokMessage, BaseRequestData tag) {

    }
    public void getProfile(String token) {
        try {
            PreferenceConnector.writeString(this,PreferenceConnector.TOKEN,token);
            requestDataModel = new RequestedServiceDataModel(this, this);
            BaseRequestData baseRequestData	= new BaseRequestData();
            baseRequestData.setPath1("tokeninfo");
            baseRequestData.setPath2("");
            requestDataModel.setShowNetworkTost(true);
            requestDataModel.setResponseType(ProfileModel.class);
            baseRequestData.setTag(ResponseType.TAG_KEY_PROFILE);
            requestDataModel.setBaseRequestData(baseRequestData);
            requestDataModel.putParamData("id_token",token);
            requestDataModel.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAccessToken(String token) {
        try {
            requestDataModel = new RequestedServiceDataModel(this, this);
            BaseRequestData baseRequestData	= new BaseRequestData();
            baseRequestData.setPath1("oauth");
            baseRequestData.setPath2("access_token");;
            requestDataModel.setShowNetworkTost(true);
            baseRequestData.setTag(ResponseType.TAG_KEY_ACCESS_TOKEN);
            requestDataModel.setBaseRequestData(baseRequestData);
            requestDataModel.setResponseType(SignUpInModel.class);
            requestDataModel.putParamData("client_id",getString(R.string.auth0_client_id));
            requestDataModel.putParamData("connection","facebook");
            requestDataModel.putParamData("access_token",token);
            requestDataModel.putParamData("scope", "openid");
            requestDataModel.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
