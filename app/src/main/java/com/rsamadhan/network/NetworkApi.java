package com.rsamadhan.network;

import java.util.HashMap;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.http.Field;

/**
 * Created by prathmeshs on 07-09-2015.
 */
public class NetworkApi {

    NetworkRequest mRequest;

    public NetworkApi(){
        mRequest =new NetworkRequest();
    }

    public void registerComplaint(EducationDomainRequest request, ComplaintCallback callback){


        NetworkRequestInterceptor requestInterceptor=new NetworkRequestInterceptor() {
            @Override
            protected void addHeaders(RequestFacade request) {

            }
        };
        RestAdapter adapter=mRequest.getRestAdapter(requestInterceptor);
        NetworkRequest.RestAPIInterface service=adapter.create(NetworkRequest.RestAPIInterface.class);
        service.createComplaint(request.getMobileNumber(),request.getDomain(),request.getComplaintContent(),request.getLatitude(),request.getLongitude(),request.getUserId(),request.getIsPublicComplaint(),callback);
    }

}
