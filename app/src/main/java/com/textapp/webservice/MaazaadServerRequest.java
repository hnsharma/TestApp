package com.textapp.webservice;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;


import com.textapp.model.ErrorModel;
import com.textapp.utils.ToastUtils;
import com.textapp.utils.Utils;
import com.textapp.widget.TransparentProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class MaazaadServerRequest {

	private Context context;
	private RequestedServiceDataModel requestedServiceDataModel;
	private Class<?> responseClass = null;
	private Dialog progressdialog;


	public MaazaadServerRequest(Context context, RequestedServiceDataModel serviceRequests){

		this.context = context;
		this.requestedServiceDataModel = serviceRequests;


    }

	public void executeRequest()  {
        showProgressBar();

        RetroDataRequest userService;
        String Url = null;
        if(requestedServiceDataModel.getUrl()==null)
             Url  =   "https://haritest.auth0.com/";
        else
            Url= requestedServiceDataModel.getUrl();
        Log.v("requst","data  "+requestedServiceDataModel.getParamBodyArray());
        userService = ServiceGenerator.createService(RetroDataRequest.class,Url, context,requestedServiceDataModel);
       if( requestedServiceDataModel.getResponseType()==null) {

            userService.dataRequest(requestedServiceDataModel.getBaseRequestData().getPath1(), requestedServiceDataModel.getBaseRequestData().getPath2(), new TypedJsonString(requestedServiceDataModel.getParamBodyArray()), stringCallback);
       }
           else {
           userService.dataRequestObject(requestedServiceDataModel.getBaseRequestData().getPath1(), requestedServiceDataModel.getBaseRequestData().getPath2(), new TypedJsonString(requestedServiceDataModel.getParamBodyArray()), baseResponseDataCallback);
       }
    }
	
	public void executeRequestWithoutProgressbar()  {

        String Url = null;
        if(requestedServiceDataModel.getUrl()==null)
            Url  =   "https://haritest.auth0.com/";
        else
            Url= requestedServiceDataModel.getUrl();
        RetroDataRequest userService;
        Log.v("requst","data  "+requestedServiceDataModel.getParamBodyArray());
        userService = ServiceGenerator.createService(RetroDataRequest.class,Url,context,requestedServiceDataModel);
        if( requestedServiceDataModel.getResponseType()==null) {
            userService.dataRequest(requestedServiceDataModel.getBaseRequestData().getPath1(), requestedServiceDataModel.getBaseRequestData().getPath2(), new TypedJsonString(requestedServiceDataModel.getParamBodyArray()), stringCallback);
        }
        else
            userService.dataRequestObject(requestedServiceDataModel.getBaseRequestData().getPath1(),requestedServiceDataModel.getBaseRequestData().getPath2(), new TypedJsonString(requestedServiceDataModel.getParamBodyArray()), baseResponseDataCallback);

    }
	private void showProgressBar(){
		progressdialog = new TransparentProgressDialog(context);
		progressdialog.show();
	}



    Callback<String>   stringCallback   = new Callback<String>() {
        private String s;
        private Response response;

        @Override
        public void success(String s, Response response) {
            if(context instanceof Activity &&(context==null||((Activity)context).isFinishing()))
            {
                return;
            }
            if (progressdialog != null && progressdialog.isShowing()) {
                try {
                    progressdialog.dismiss();
                }
                catch (Exception e)
                {

                }
            }
            try {
                this.s = s;
                this.response = response;
                responseClass = requestedServiceDataModel.getResponseType();
                if (requestedServiceDataModel.getResponseDelegate() != null) {
                    requestedServiceDataModel.getResponseDelegate().onSuccess(s, requestedServiceDataModel.getBaseRequestData());
                }

            } catch (Exception ex) {

                ex.printStackTrace();
                if (requestedServiceDataModel.getResponseDelegate() != null) {
                    requestedServiceDataModel.getResponseDelegate().onFailure("Server error", requestedServiceDataModel.getBaseRequestData());
                }
            }

        }

        @Override
        public void failure(RetrofitError error) {
            Log.v("error", "error" + error.getMessage());
            if(error.getResponse()!=null&&error.getResponse().getBody()!=null)
            {
                String s = Utils.TypedToString(error.getResponse().getBody());
                if(s.startsWith("{")) {
                    ErrorModel errorModel = (ErrorModel) JsonParser.parseJson(s, ErrorModel.class);
                    ToastUtils.showToast(context, errorModel.getDes() == null ? errorModel.getError() : errorModel.getDes());
                    Log.v("error", "error" + Utils.TypedToString(error.getResponse().getBody()));
                }
                else
                {
                    ToastUtils.showToast(context,s);
                }
            }
            if(context instanceof Activity &&(context==null||((Activity)context).isFinishing()))
            {
                return;
            }
            if (progressdialog != null && progressdialog.isShowing()) {
                try {
                    progressdialog.dismiss();
                }
                catch (Exception e)
                {

                }
            }
            if (requestedServiceDataModel.getResponseDelegate() != null) {
                requestedServiceDataModel.getResponseDelegate().onFailure(null, requestedServiceDataModel.getBaseRequestData());
            }
            try {
                error.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    Callback<BaseResponseData>   baseResponseDataCallback  =  new Callback<BaseResponseData>() {
        @Override
        public void success(BaseResponseData baseRequestData, Response response)
        {

            if(context instanceof Activity &&(context==null||((Activity)context).isFinishing()))
            {
                return;
            }
            if (progressdialog != null && progressdialog.isShowing()) {
                try {
                    progressdialog.dismiss();
                }
                catch (Exception e)
                {

                }

            }
            try {
                if(requestedServiceDataModel.getResponseDelegate()==null)
                {
                    return;
                }
                if (baseRequestData.getError()==null) {
                    requestedServiceDataModel.getResponseDelegate().onSuccess(baseRequestData, requestedServiceDataModel.getBaseRequestData());
                } else {


                }
            } catch (Exception ex) {

                ex.printStackTrace();
                if (requestedServiceDataModel.getResponseDelegate() != null) {
                    ToastUtils.showToast(context,"Server error");
                    requestedServiceDataModel.getResponseDelegate().onFailure("Server error", requestedServiceDataModel.getBaseRequestData());
                }
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.v("error", "error" + error.getMessage());
            if(error.getResponse()!=null&&error.getResponse().getBody()!=null) {
                String s = Utils.TypedToString(error.getResponse().getBody());
                if(s.startsWith("{")) {
                    ErrorModel errorModel = (ErrorModel) JsonParser.parseJson(s, ErrorModel.class);
                    ToastUtils.showToast(context, errorModel.getDes() == null ? errorModel.getError() : errorModel.getDes());
                    Log.v("error", "error" + Utils.TypedToString(error.getResponse().getBody()));
                }
                else
                {
                    ToastUtils.showToast(context,s);
                }
            }
            if(context instanceof Activity &&(context==null||((Activity)context).isFinishing()))
            {
                return;
            }
            if (progressdialog != null && progressdialog.isShowing()) {
                try {
                    progressdialog.dismiss();
                }
                catch (Exception e)
                {

                }
            }
            if (requestedServiceDataModel.getResponseDelegate() != null) {

                requestedServiceDataModel.getResponseDelegate().onFailure(null, requestedServiceDataModel.getBaseRequestData());
            }

            try {
                error.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
