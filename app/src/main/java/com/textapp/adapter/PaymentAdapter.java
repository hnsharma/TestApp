package com.textapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.textapp.PaymentListActivity;
import com.textapp.R;
import com.textapp.model.PaymentItem;

import java.util.ArrayList;

/**
 * Created by Asus on 16-02-2016.
 */
public class PaymentAdapter extends BaseAdapter{
    Context context;
    ArrayList<PaymentItem> paymentItems;
    LayoutInflater      layoutInflater;
    public PaymentAdapter(Context context, ArrayList<PaymentItem> paymentItems) {
        this.context = context;
        this.paymentItems =paymentItems;
        layoutInflater  =((Activity)context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return paymentItems.size();
    }

    @Override
    public Object getItem(int i) {
        return paymentItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        Holder  holder  =  new Holder();
        if(view == null)
        {
            view = layoutInflater.inflate(R.layout.payment_list_item,null);
            holder.name  = (TextView)view.findViewById(R.id.name);
            holder.email  = (TextView)view.findViewById(R.id.email);
            holder.amount  = (TextView)view.findViewById(R.id.amount);
            holder.card  = (TextView)view.findViewById(R.id.card);
            holder.token  = (TextView)view.findViewById(R.id.token);
            holder.address  = (TextView)view.findViewById(R.id.address);
            view.setTag(holder);
        }
          holder   = (Holder)view.getTag();
        holder.name.setText(paymentItems.get(i).getFname()+" "+paymentItems.get(i).getLname());
        holder.email.setText(paymentItems.get(i).getEmail());
        holder.amount.setText(paymentItems.get(i).getAmount()+" "+paymentItems.get(i).getCurrence());
        holder.card.setText(paymentItems.get(i).getCard());
        holder.token.setText(paymentItems.get(i).getToken());
        holder.address.setText(paymentItems.get(i).getAddress());
        return view;
    }
    class Holder
    {
        TextView        name;
        TextView        email;
        TextView        amount;
        TextView        token;
        TextView        card;
        TextView        address;

    }
}
