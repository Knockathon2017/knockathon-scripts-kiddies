package com.rsamadhan.network;

import com.rsamadhan.network.callbackrequest.ComplaintCallback;
import com.rsamadhan.network.callbackrequest.ComplaintListCallback;
import com.rsamadhan.network.callbackrequest.PostCommentCallback;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by prathmeshs on 03-09-2015.
 */
public class NetworkRequest {

    private RestAdapter mMainRestAdapter;

    public NetworkRequest() {}

    private RestAdapter createAdapter(RequestInterceptor requestInterceptor) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);


        return new RestAdapter.Builder()
                .setEndpoint(NetworkConstants.BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(okHttpClient))
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
                                    @Field("IsPublicComplaint") Boolean pub ,
                                    ComplaintCallback callback);

        @GET("/AllWorkkardActivities/{UserId}/{MobileNumber}/{Domain}")
        public void getListOfComplaints(@Path("UserId") String userId,@Path("MobileNumber") String mobileNumber,@Path("Domain") String domain,
                                        ComplaintListCallback callback);

        @FormUrlEncoded
        @POST("/ActivityAddNote")
        public void postNewComment(@Field("ActivityId") String activityId, @Field("Description") String description, @Field("Domain") String domain,PostCommentCallback callback);


    }
}
