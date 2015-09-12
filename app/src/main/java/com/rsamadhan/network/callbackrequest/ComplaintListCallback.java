package com.rsamadhan.network.callbackrequest;

import com.rsamadhan.network.ComplaintListData;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class ComplaintListCallback  implements Callback<ComplaintListData> {
    @Override
    public void success(ComplaintListData complaintListData, Response response) {
        
    }

    @Override
    public void failure(RetrofitError error) {

    }
}
