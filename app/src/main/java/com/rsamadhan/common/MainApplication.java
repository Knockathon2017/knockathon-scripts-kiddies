package com.rsamadhan.common;

import android.app.Application;

import com.rsamadhan.location.LocationHandler;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class MainApplication extends Application {

    public static  LocationHandler sLocationFetcher;
    @Override
    public void onCreate() {
        super.onCreate();
        sLocationFetcher =LocationHandler.getInstance(getBaseContext());
        sLocationFetcher.registerLocation();
    }

}
