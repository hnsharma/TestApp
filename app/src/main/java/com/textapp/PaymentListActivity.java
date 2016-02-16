package com.textapp;

import android.os.Bundle;
import android.widget.ListView;

import com.textapp.adapter.PaymentAdapter;
import com.textapp.model.PaymentItem;

import java.util.ArrayList;

/**
 * Created by Asus on 15-02-2016.
 */
public class PaymentListActivity extends BaseActivity{

    PaymentAdapter  paymentAdapter;
    ArrayList<PaymentItem>  paymentItems;
    ListView        listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentlist);
        paymentItems  = (ArrayList<PaymentItem>)getIntent().getSerializableExtra("data");
        UI();
    }

    private void UI() {
        setClick(R.id.back);
        listView        =    (ListView)findViewById(R.id.list);
        paymentAdapter  = new PaymentAdapter(this,paymentItems);
        listView.setAdapter(paymentAdapter);
    }
}
