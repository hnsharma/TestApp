package com.textapp.delegate;


import com.textapp.webservice.BaseRequestData;
import com.textapp.webservice.BaseResponseData;

public interface ResponseDelegate {

	public void onSuccess(BaseResponseData response, BaseRequestData tag);
	public void onSuccess(String response, BaseRequestData tag);
	public void onFailure(String errorMsg, BaseRequestData tag);
	public void onNoNetwork(String netwokMessage, BaseRequestData tag);
}
