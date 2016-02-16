package com.textapp.webservice;


import android.content.Context;
import android.widget.Toast;

import com.textapp.R;
import com.textapp.delegate.ResponseDelegate;
import com.textapp.utils.Utils;

import java.io.UnsupportedEncodingException;


public class RequestedServiceDataModel extends DataModel {

	private MaazaadServerRequest dostoomServerRequest;

	public RequestedServiceDataModel(Context context, ResponseDelegate delegate) {
		super(context, delegate);
	}

	public void execute() {

		if(Utils.isOnline(context)) {
			dostoomServerRequest = new MaazaadServerRequest(context, this);
				dostoomServerRequest.executeRequest();


		}
		else 
		{
			if (isShowNetworkTost()) {
				Toast.makeText(context, R.string.network_meaage, Toast.LENGTH_LONG).show();
			}
			else
			{
				if(responseDelegate!=null)
				{
					responseDelegate.onNoNetwork(context.getResources().getString(R.string.network_meaage),getBaseRequestData());
				}
			}
			
		}

	}
	
	public void executeWithoutProgressbar() {

		if(Utils.isOnline(context)) {
			dostoomServerRequest = new MaazaadServerRequest(context, this);
				dostoomServerRequest.executeRequestWithoutProgressbar();

		} else 
		{
			if (isShowNetworkTost()) 
			{
				Toast.makeText(context, R.string.network_meaage, Toast.LENGTH_LONG).show();
			}
			else
			{
				if(responseDelegate!=null)
				{
					responseDelegate.onNoNetwork(context.getResources().getString(R.string.network_meaage),getBaseRequestData());
				}
			}
		}

	}

}
