package com.rsamadhan.network;

import retrofit.RequestInterceptor;

/**
 * Created by prathmeshs on 07-09-2015.
 */
public  abstract  class NetworkRequestInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        //commom headers add here
        request.addHeader("Content-type","application/json");
        addHeaders(request);
    }
    protected abstract void addHeaders(RequestFacade request);
}
