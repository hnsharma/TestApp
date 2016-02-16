package com.textapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.textapp.delegate.ResponseType;
import com.textapp.model.SignUpInModel;
import com.textapp.utils.ToastUtils;
import com.textapp.utils.ValidationUtils;
import com.textapp.webservice.BaseRequestData;
import com.textapp.webservice.BaseResponseData;
import com.textapp.webservice.RequestedServiceDataModel;

/**
 * Created by Asus on 13-02-2016.
 */
public class SignUpActivity extends BaseActivity {

    EditText email,password,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        UI();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                getAccessToken( AccessToken.getCurrentAccessToken().getToken());
                Log.e("Taken", "token  " + AccessToken.getCurrentAccessToken().getToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    private void UI() {
        setClick(R.id.back);
        setClick(R.id.next);
        setClick(R.id.fb_login);
        name   =    (EditText)findViewById(R.id.name);
        email  = (EditText)findViewById(R.id.email);
        password  = (EditText)findViewById(R.id.password);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.next:
                if(validation())
                {
                    singUp();
                }
                break;
        }
    }
    public boolean validation()
    {
        if(ValidationUtils.isEmpty(name.getText().toString())) {
            ToastUtils.showToast(this, R.string.please_enter_name);
            return false;

        }
        else if(ValidationUtils.isEmpty(email.getText().toString())) {
            ToastUtils.showToast(this, R.string.please_enter_email);
            return false;

        }
        else if(!ValidationUtils.isValidEmail(email.getText().toString()))
        {
            ToastUtils.showToast(this,R.string.please_enter_valid_email);
            return false;

        }
        else if(ValidationUtils.isEmptyNotTrim(password.getText().toString()))
        {
            ToastUtils.showToast(this,R.string.please_enter_password);
            return false;

        }
        return true;
    }
    public void singUp() {
        try {
            requestDataModel = new RequestedServiceDataModel(this, this);
            BaseRequestData baseRequestData	= new BaseRequestData();
            baseRequestData.setPath1("dbconnections");
            baseRequestData.setPath2("signup");;
            baseRequestData.setTag(ResponseType.TAG_KEY_SIGN_UP);
            requestDataModel.setShowNetworkTost(true);
            requestDataModel.setBaseRequestData(baseRequestData);
            requestDataModel.setResponseType(SignUpInModel.class);
            requestDataModel.putParamData("client_id",getString(R.string.auth0_client_id));
            requestDataModel.putParamData("connection","Username-Password-Authentication");
            requestDataModel.putParamData("email",email.getText().toString().trim());
            requestDataModel.putParamData("password", password.getText().toString().trim());
            requestDataModel.putParamData("username",name.getText().toString().trim());
            requestDataModel.putParamData("grant_type", "password");
            requestDataModel.putParamData("scope", "openid");
            requestDataModel.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(BaseResponseData response, BaseRequestData tag) {
        super.onSuccess(response, tag);
        switch (tag.getTag())
        {
            case ResponseType.TAG_KEY_SIGN_UP:
                getProfile(((SignUpInModel)response).getId_token());
                break;
        }
    }
}

