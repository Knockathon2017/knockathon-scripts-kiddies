package com.rsamadhan.network.callbackrequest;

import com.rsamadhan.network.response.ComplaintListData;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public abstract class ComplaintListCallback  implements Callback<ComplaintListData> {
    @Override
    public void success(ComplaintListData complaintListData, Response response) {
        complaintListSuccess(complaintListData);
    }

    @Override
    public void failure(RetrofitError error) {
        complaintListError(error);
    }
    public abstract void complaintListSuccess(ComplaintListData complaintListData);
    public abstract void complaintListError(RetrofitError error);
}
