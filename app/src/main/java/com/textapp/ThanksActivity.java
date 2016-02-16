package com.textapp;

import android.os.Bundle;
import android.view.View;

/**
 * Created by Asus on 16-02-2016.
 */
public class ThanksActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        setClick(R.id.next);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.next:
                finish();
                break;
        }
    }
}
