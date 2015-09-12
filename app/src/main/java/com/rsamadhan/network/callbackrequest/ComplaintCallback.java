package com.rsamadhan.network.callbackrequest;

import com.rsamadhan.network.response.RequestResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public abstract  class ComplaintCallback implements Callback<RequestResponse> {
    @Override
    public void success(RequestResponse o, Response response) {
        complaintSuccess(o);
    }
    public abstract void complaintSuccess(RequestResponse o);
    public abstract void complaintFail(RetrofitError error);
    @Override
    public void failure(RetrofitError error) {
            complaintFail(error);
    }
}
