package com.textapp.model;

import com.textapp.webservice.BaseResponseData;

import java.util.ArrayList;

/**
 * Created by Asus on 16-02-2016.
 */
public class PaymentBaseData extends BaseResponseData{
    private ArrayList<PaymentItem>  list  = new ArrayList<>();
    private int  success;

    public ArrayList<PaymentItem> getList() {
        return list;
    }

    public void setList(ArrayList<PaymentItem> list) {
        this.list = list;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
