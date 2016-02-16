package com.textapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.textapp.model.ProfileModel;
import com.textapp.utils.PreferenceConnector;

/**
 * Created by Asus on 16-02-2016.
 */
public class SplashActivity extends BaseActivity {

    Handler handler  = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(PreferenceConnector.getProfile(SplashActivity.this)==null||PreferenceConnector.getProfile(SplashActivity.this).getUser_id()==null)
                {
                    startActivity(MainActivity.class);
                }
                else
                {
                    Intent intent  = new Intent(SplashActivity.this,ProfileActivity.class);
                    Bundle  bundle =new Bundle();
                    bundle.putSerializable("data",PreferenceConnector.getProfile(SplashActivity.this));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        },3000);
    }
}
