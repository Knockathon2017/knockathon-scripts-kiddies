package com.rsamadhan.network.callbackrequest;

import com.rsamadhan.network.response.Results;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;




public abstract  class PublicComplaintListCallback implements Callback<List<Results>> {
    @Override
    public void success(List<Results> complaintListData, Response response) {
        publicCmplaintListSuccess(complaintListData);
    }

    @Override
    public void failure(RetrofitError error) {
        complaintListError(error);
    }
    public abstract void publicCmplaintListSuccess(List<Results> complaintListData);
    public abstract void complaintListError(RetrofitError error);
}
