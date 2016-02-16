
package com.textapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.auth0.lock.Lock;
import com.facebook.login.LoginManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.textapp.delegate.ResponseType;
import com.textapp.model.PaymentBaseData;
import com.textapp.model.ProfileModel;
import com.textapp.utils.PreferenceConnector;
import com.textapp.webservice.BaseRequestData;
import com.textapp.webservice.BaseResponseData;
import com.textapp.webservice.RequestedServiceDataModel;


public class ProfileActivity extends BaseActivity {

    PaymentBaseData   paymentBaseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ProfileModel profile = (ProfileModel) getIntent().getSerializableExtra("data");
        TextView greetingTextView = (TextView) findViewById(R.id.welcome_message);
        greetingTextView.setText("Welcome " + profile.getName());
        ImageView profileImageView = (ImageView) findViewById(R.id.profile_image);
        if (profile.getPicture() != null) {
            ImageLoader.getInstance().displayImage(profile.getPicture(), profileImageView);
        }
        setClick(R.id.pay);
        setClick(R.id.pay_history);
        setClick(R.id.logout);


    }

    @Override
    protected void onResume() {
        super.onResume();
        paymentHistory();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.pay:
                startActivity(PaymentActivity.class);
                break;
            case R.id.logout:
                if(PreferenceConnector.getProfile(this).getIdentities().get(0).getIsSocial())
                {
                    LoginManager.getInstance().logOut();
                }
                PreferenceConnector.clear(this);
                startActivity(MainActivity.class);
                break;
            case R.id.pay_history:

                Bundle      bundle  = new Bundle();
                bundle.putSerializable("data",paymentBaseData.getList());
                Intent intent  = new Intent(this,PaymentListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onSuccess(BaseResponseData response, BaseRequestData tag) {
        super.onSuccess(response, tag);
        switch (tag.getTag())
        {
            case ResponseType.TAG_KEY_PAYMENT_HISTORY:
                paymentBaseData     =(PaymentBaseData)response;
                if(paymentBaseData.getSuccess()==1)
                {
                    findViewById(R.id.pay_history).setVisibility(View.VISIBLE);
                }
                else
                {
                    findViewById(R.id.pay_history).setVisibility(View.GONE);
                }
                break;
        }
    }

    public void paymentHistory() {
        try {
            requestDataModel = new RequestedServiceDataModel(this, this);
            BaseRequestData baseRequestData = new BaseRequestData();
            baseRequestData.setPath1("htest");
            baseRequestData.setPath2("getlist.php");
            requestDataModel.setUrl(ResponseType.PAYMENT_URL);
            requestDataModel.setResponseType(PaymentBaseData.class);
            baseRequestData.setTag(ResponseType.TAG_KEY_PAYMENT_HISTORY);
            requestDataModel.setBaseRequestData(baseRequestData);
            requestDataModel.setShowNetworkTost(true);
            requestDataModel.putParamData("user_id", PreferenceConnector.getProfile(this).getIdentities().get(0).getUser_id());
            requestDataModel.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
