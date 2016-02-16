package com.textapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.textapp.delegate.ResponseType;
import com.textapp.model.SignUpInModel;
import com.textapp.utils.PreferenceConnector;
import com.textapp.utils.ToastUtils;
import com.textapp.utils.Utils;
import com.textapp.utils.ValidationUtils;
import com.textapp.webservice.BaseRequestData;
import com.textapp.webservice.BaseResponseData;
import com.textapp.webservice.RequestedServiceDataModel;
import com.textapp.widget.TransparentProgressDialog;

import java.util.Calendar;

/**
 * Created by Asus on 14-02-2016.
 */
public class PaymentActivity extends BaseActivity {

    Card card;
    EditText fName,lName,cardNumber,cvv,address,amount;
    Spinner   month,year;
    public static final String PUBLISHABLE_KEY = "pk_test_omcBaw3KTDQ8rrnSHoSxYPYD";
    Dialog startProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        UI();


    }

    private void UI() {
        startProgress  =new TransparentProgressDialog(this);
        setClick(R.id.next);
        setClick(R.id.back);
        month       =    (Spinner)findViewById(R.id.expMonth);
        year        =    (Spinner)findViewById(R.id.expYear);
        fName       =       (EditText)findViewById(R.id.fname);
        amount      =        (EditText)findViewById(R.id.amount);
        lName       =       (EditText)findViewById(R.id.lname);
        cardNumber  =       (EditText)findViewById(R.id.card_number);
        cvv         =       (EditText)findViewById(R.id.cvv);
        address     =       (EditText)findViewById(R.id.address);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.next:
                validate();
                break;
        }
    }

    private boolean validate() {

        Card card = new Card(
               cardNumber.getText().toString(),
                getInteger(month),
                getInteger(year),
                cvv.getText().toString());
        boolean validation = card.validateCard();
        if(ValidationUtils.isEmpty(fName.getText().toString()))
        {
            ToastUtils.showToast(this, R.string.enter_f_name);
            return false;
        }
        else if(ValidationUtils.isEmpty(lName.getText().toString()))
        {
            ToastUtils.showToast(this, R.string.enter_l_name);
            return false;
        }
        else if(ValidationUtils.isEmpty(amount.getText().toString()))
        {
            ToastUtils.showToast(this, "Please enter amount.");
            return false;
        }
       else if (validation) {
            startProgress.show();
            new Stripe().createToken(
                    card,
                    PUBLISHABLE_KEY,
                    new TokenCallback() {
                        public void onSuccess(Token token) {
                            startProgress.dismiss();

                            payment(token.getId());
                        }

                        public void onError(Exception error) {
                            ToastUtils.showToast(PaymentActivity.this, error.getLocalizedMessage());
                            startProgress.dismiss();
                        }
                    });
        } else if (!card.validateNumber()) {
            ToastUtils.showToast(this,"The card number that you entered is invalid");
        } else if (!card.validateExpiryDate()) {
            ToastUtils.showToast(this,"The expiration date that you entered is invalid");
        } else if (!card.validateCVC()) {
            ToastUtils.showToast(this,"The CVC code that you entered is invalid");
        } else {
            ToastUtils.showToast(this,"The card details that you entered are invalid");
        }

        return false;
    }
    private Integer getInteger(Spinner spinner) {
        try {
            return Integer.parseInt(spinner.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public void payment(String token) {
        try {
            requestDataModel = new RequestedServiceDataModel(this, this);
            BaseRequestData baseRequestData	= new BaseRequestData();
            baseRequestData.setPath1("htest");
            baseRequestData.setPath2("lole.php");
            requestDataModel.setUrl(ResponseType.PAYMENT_URL);
            baseRequestData.setTag(ResponseType.TAG_KEY_PAYMENT);
            requestDataModel.setBaseRequestData(baseRequestData);
            requestDataModel.setShowNetworkTost(true);
            requestDataModel.putParamData("user_id", PreferenceConnector.getProfile(this).getIdentities().get(0).getUser_id());
            requestDataModel.putParamData("fname",fName.getText().toString());
            requestDataModel.putParamData("lname", lName.getText().toString());
            requestDataModel.putParamData("address", address.getText().toString());
            requestDataModel.putParamData("amount", amount.getText().toString());
            requestDataModel.putParamData("card", cardNumber.getText().toString());
            requestDataModel.putParamData("token", token);
            requestDataModel.putParamData("amount", amount.getText().toString());
            requestDataModel.putParamData("email", PreferenceConnector.getProfile(this).getEmail());
            requestDataModel.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSuccess(String response, BaseRequestData tag) {
        super.onSuccess(response, tag);
        if(response.trim().equalsIgnoreCase("Your payment was successful."))
        {
            startActivity(ThanksActivity.class);
            finish();
        }
        else
        {
            ToastUtils.showToast(this,response);
        }
    }
}
