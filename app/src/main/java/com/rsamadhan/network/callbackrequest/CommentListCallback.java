package com.rsamadhan.network.callbackrequest;

import com.rsamadhan.network.response.ComplaintListData;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public abstract class CommentListCallback  implements Callback<ComplaintListData> {
    @Override
    public void success(ComplaintListData complaintListData, Response response) {
        commentListSuccess(complaintListData);
    }

    @Override
    public void failure(RetrofitError error) {
        commentListError(error);
    }
    public abstract void commentListSuccess(ComplaintListData complaintListData);
    public abstract void commentListError(RetrofitError error);
}