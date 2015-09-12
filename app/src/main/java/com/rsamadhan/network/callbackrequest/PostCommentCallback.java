package com.rsamadhan.network.callbackrequest;

import com.rsamadhan.network.response.CommentResponse;
import com.rsamadhan.network.response.RequestResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public abstract class PostCommentCallback implements Callback<CommentResponse>{
    @Override
    public void success(CommentResponse commentResponse, Response response) {
        commentSuccess(commentResponse);
    }
    public abstract void commentSuccess(CommentResponse o);
    public abstract void commentFail(RetrofitError error);
    @Override
    public void failure(RetrofitError error) {
        commentFail(error);
    }
}
