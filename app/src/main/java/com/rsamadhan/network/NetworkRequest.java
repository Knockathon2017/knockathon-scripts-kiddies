package com.rsamadhan.network;

import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.QueryMap;

/**
 * Created by prathmeshs on 03-09-2015.
 */
public class NetworkRequest {

    private RestAdapter mMainRestAdapter;

    public NetworkRequest() {}

    private RestAdapter createAdapter(RequestInterceptor requestInterceptor) {
        return new RestAdapter.Builder()
                .setEndpoint(NetworkConstants.BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();
    }
    public RestAdapter getRestAdapter(RequestInterceptor requestInterceptor){
        return createAdapter(requestInterceptor);
    }
    public interface ServiceIdentifier{
        public static int LOGIN_SERVICE=100;
        public static int FORGOT_PASS_SERVICE=101;
    }
    public interface RestAPIInterface {
     /*   @POST("/offline/login")
        public void getLogin(@Body Object data, Callback callback);
*/
       /* @Multipart
        @PUT("/user/photo")
        Call<User> updateUser(@Part("file") RequestBody photo, @Part("description") RequestBody description);*/

        @FormUrlEncoded
        @POST("/CreateComplain")
        public void createComplaint(@Field("MobileNumber") String mobileNumber ,
                                    @Field("Domain") String domain ,
                                    @Field("ComplaintContent") String complaintContent,
                                    @Field("Latitude") String lat,
                                    @Field("Longitude") String lon ,
                                    @Field("UserId") String userId ,
                                    @Field("IsPublicComplaint") Boolean pub ,
                                    ComplaintCallback callback);
    }
}
