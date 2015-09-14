package com.rsamadhan.network.callbackrequest;

import com.rsamadhan.network.response.ActivityCommentsListData;
import com.rsamadhan.network.response.ComplaintListData;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public abstract class ActivityCommentListCallback implements Callback<ActivityCommentsListData> {
    @Override
    public void success(ActivityCommentsListData activityCommentsListData, Response response) {
        commentListSuccess(activityCommentsListData);
    }

    @Override
    public void failure(RetrofitError error) {
        commentListError(error);
    }
    public abstract void commentListSuccess(ActivityCommentsListData activityCommentsListData);
    public abstract void commentListError(RetrofitError error);
}