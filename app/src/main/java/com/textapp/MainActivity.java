package com.textapp;

import android.os.Bundle;
import android.view.View;

import com.facebook.CallbackManager;


public class MainActivity extends BaseActivity {


    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setClick(R.id.next);
        setClick(R.id.sign_up);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.next:
                startActivity(LoginActivity.class);
                break;
            case R.id.sign_up:
                startActivity(SignUpActivity.class);
                break;
        }
        super.onClick(view);
    }
}
