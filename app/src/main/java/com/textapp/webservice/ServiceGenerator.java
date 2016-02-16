package com.textapp.webservice;

import android.content.Context;
import android.util.Base64;


import com.textapp.SampleApplication;
import com.textapp.utils.Utils;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Asus on 13-07-2015.
 */
public class ServiceGenerator {
   // private static RestAdapter.Builder builder = new RestAdapter.Builder().setClient(new OkClient());

    // No need to instantiate this class.
    private ServiceGenerator() {
    }


    public static <S> S createService(Class<S> serviceClass, String baseUrl,  final Context context, RequestedServiceDataModel requestedServiceDataModel) {
        // set endpoint url
        RestAdapter.Builder builder   =((SampleApplication)context.getApplicationContext()).getRestAdapter(requestedServiceDataModel);
        builder.setEndpoint(baseUrl);
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json;versions=1");
                if (Utils.isOnline(context)) {
                    int maxAge = 60; // read from cache for 1 minute
                    request.addHeader("Cache-Control", "public, max-age=" + maxAge);
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    request.addHeader("Cache-Control",
                            "public, only-if-cached, max-stale=" + maxStale);
                }
            }
        });



            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    // create Base64 encodet string
                    request.addHeader("Accept", "application/json");
                }
            });

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }



}
