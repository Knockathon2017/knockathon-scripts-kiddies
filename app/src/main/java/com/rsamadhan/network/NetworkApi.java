package com.rsamadhan.network;

import com.rsamadhan.network.callbackrequest.ComplaintCallback;
import com.rsamadhan.network.callbackrequest.ComplaintListCallback;
import com.rsamadhan.network.requests.EducationDomainRequest;

import retrofit.RestAdapter;

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
        service.createComplaint(request.getMobileNumber(),request.getDomain(),request.getComplaintContent(),request.getLatitude(),request.getLongitude(),request.getIsPublicComplaint(),callback);
    }

    public void getComplaintList(ComplaintListCallback callback,String ...requestparams){
        NetworkRequestInterceptor requestInterceptor=new NetworkRequestInterceptor() {
            @Override
            protected void addHeaders(RequestFacade request) {

            }
        };
        RestAdapter adapter=mRequest.getRestAdapter(requestInterceptor);
        NetworkRequest.RestAPIInterface service=adapter.create(NetworkRequest.RestAPIInterface.class);
        service.getListOfComplaints(requestparams[0],requestparams[1],requestparams[2],callback);
    }

}
