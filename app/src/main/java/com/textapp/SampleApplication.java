/*
 * SampleApplication.java
 *
 * Copyright (c) 2015 Auth0 (http://auth0.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.textapp;

import android.app.Application;

import com.auth0.core.Strategies;
import com.auth0.lock.Lock;
import com.auth0.lock.LockBuilder;
import com.auth0.lock.LockProvider;
import com.facebook.FacebookSdk;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.textapp.webservice.BaseResponseData;
import com.textapp.webservice.GsonConverter;
import com.textapp.webservice.GsonConverterObject;
import com.textapp.webservice.MySSLTrust;
import com.textapp.webservice.RequestedServiceDataModel;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class SampleApplication extends Application implements LockProvider {

    private Lock lock;
    private RestAdapter.Builder restAdapter,restAdapterString;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        lock = new Lock.Builder()
                .loadFromApplication(this)
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
        restAdapter  = new  RestAdapter.Builder().setConverter(new GsonConverter<String>()).setClient(new OkClient(MySSLTrust.trustcert(getApplicationContext())));
    }
    public RestAdapter.Builder getRestAdapter(RequestedServiceDataModel requestedServiceDataModel) {
        if(requestedServiceDataModel.getResponseType()==null)
            return restAdapter;
        else
        {
            return   new  RestAdapter.Builder().setConverter(new GsonConverterObject<BaseResponseData>(requestedServiceDataModel)).setClient(new OkClient(MySSLTrust.trustcert(getApplicationContext())));
        }
    }

    public void setRestAdapter(RestAdapter.Builder restAdapter) {
        this.restAdapter = restAdapter;
    }
    @Override
    public Lock getLock() {
        return lock;
    }
}
