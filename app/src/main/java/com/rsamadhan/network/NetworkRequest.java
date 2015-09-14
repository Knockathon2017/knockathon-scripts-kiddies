package com.rsamadhan.network;

import com.rsamadhan.network.callbackrequest.ActivityCommentListCallback;
import com.rsamadhan.network.callbackrequest.CommentListCallback;
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

    public NetworkRequest() {
    }

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

    public RestAdapter getRestAdapter(RequestInterceptor requestInterceptor) {
        return createAdapter(requestInterceptor);
    }

    public interface ServiceIdentifier {
        public static int LOGIN_SERVICE = 100;
        public static int FORGOT_PASS_SERVICE = 101;
    }

    public interface RestAPIInterface {

        @FormUrlEncoded
        @POST("/CreateComplain")
        public void createComplaint(@Field("MobileNumber") String mobileNumber,
                                    @Field("Domain") String domain,
                                    @Field("ComplaintContent") String complaintContent,
                                    @Field("Latitude") String lat,
                                    @Field("Longitude") String lon,
                                    @Field("IsPublicComplaint") Boolean pub,
                                    ComplaintCallback callback);

        @GET("/AllWorkkardActivities/{UserId}/{MobileNumber}/{Domain}")
        public void getListOfComplaints(@Path("UserId") String userId, @Path("MobileNumber") String mobileNumber, @Path("Domain") String domain,
                                        ComplaintListCallback callback);

        @FormUrlEncoded
        @POST("/ActivityAddNote")
        public void postNewComment(@Field("ActivityId") String activityId, @Field("Description") String description, @Field("Domain") String domain, @Field("MobileNumber") String mobile, PostCommentCallback callback);


        @GET("/activityNotes/{id}/{domain}")
        public void getListOfComments(@Path("id") String activityId, @Path("domain") String domain, CommentListCallback callback);

        @GET("/activityNotes/{id}/{domain}")
        public void getActivityListOfComments(@Path("id") String activityId, @Path("domain") String domain, ActivityCommentListCallback callback);

    }
}
