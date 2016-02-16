package com.textapp.webservice;

import android.content.Context;


import com.textapp.delegate.ResponseDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;


public abstract class DataModel{

	protected Context context;
	protected ResponseDelegate responseDelegate;
	private String url;
	private  int REQUEST_TAG;
	private	 BaseRequestData	baseRequestData;


	private Class<?> responseType;
	private Type type;
	private JSONObject requestedParameter;

    public void setRequestedParameter(JSONObject requestedParameter) {
        this.requestedParameter = requestedParameter;
    }

    private boolean  isShowNetworkTost		=	false;
	

	public boolean isShowNetworkTost() {
		return isShowNetworkTost;
	}

	public void setShowNetworkTost(boolean isShowNetworkTost) {
		this.isShowNetworkTost = isShowNetworkTost;
	}

	public DataModel(Context context,ResponseDelegate delegate) {
		this.context = context;
		this.responseDelegate = delegate;

	}

	public Context getContext() {

		return context;
	}

	protected String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/*public int getTag(){

		return REQUEST_TAG;
	}

	public void setRequestTag(int requestTag){
		this.REQUEST_TAG = requestTag;
	}*/
	public BaseRequestData getBaseRequestData() {
		return baseRequestData;
	}

	public void setBaseRequestData(BaseRequestData baseRequestData) {
		this.baseRequestData = baseRequestData;
	}

	protected ResponseDelegate getResponseDelegate() {

		return responseDelegate;
	}

	public Class<?> getResponseType() {
		return responseType;
	}
	public Type getType() {
		return type;
	}

	public void setResponseType(Class<?> responseType) {
		this.responseType = responseType;
	}
	public void setType(Type listType) {
		this.type = listType;
	}


	public JSONObject getParamBody() 
	{
		return requestedParameter;
	}
	public String getParamBodyArray() 
	{
        if(requestedParameter==null)
            return new JSONObject().toString();
			return requestedParameter.toString();
		
	}
	public void putParamData(String key,Object Value) throws JSONException 
	{
		if(requestedParameter==null)
			requestedParameter	=	new JSONObject();
		requestedParameter.put(key, Value);
	}
	public void putParamData(String key,int Value) throws JSONException 
	{
		if(requestedParameter==null)
			requestedParameter	=	new JSONObject();
		requestedParameter.put(key, Value);
	}
	public void putParamData(String key,double Value) throws JSONException 
	{
		if(requestedParameter==null)
			requestedParameter	=	new JSONObject();
		requestedParameter.put(key, Value);
	}
	public void putParamData(String key,boolean Value) throws JSONException 
	{
		if(requestedParameter==null)
			requestedParameter	=	new JSONObject();
		requestedParameter.put(key, Value);
	}
	public void putParamData(String key,long Value) throws JSONException 
	{
		if(requestedParameter==null)
			requestedParameter	=	new JSONObject();
		requestedParameter.put(key, Value);
	}


	public void setJsonArray(String tagName,JSONArray jArray)throws JSONException{

		requestedParameter.putOpt(tagName, jArray);
	}
}
