package com.rsamadhan.network;

/**
 * Created by prathmeshs on 07-09-2015.
 */
public class NetworkApi {

    NetworkRequest mRequest;

    public NetworkApi(){
        mRequest =new NetworkRequest();
    }
   /* public void fetchLogin(Object data, Callback callback){
            NetworkRequestInterceptor requestInterceptor=new NetworkRequestInterceptor() {
                @Override
                protected void addHeaders(RequestFacade request) {
                    //Add Additional headers here
                    request.addHeader(NetworkConstants.DEVICE_ID,"empty");
                }
            };
            RestAdapter adapter= mRequest.getRestAdapter(requestInterceptor);
            NetworkRequest.RestAPIInterface service=adapter.create(NetworkRequest.RestAPIInterface.class);
            service.getLogin(data,callback);
    }*/
    public void uploadUserVoice(){

    }

}
