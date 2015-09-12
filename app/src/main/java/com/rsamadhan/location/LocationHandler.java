package com.rsamadhan.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class LocationHandler {
    private Location mUpdatedLocation;

    private static LocationHandler sInstance;
    private LocationManager mLocationManager;

    private static Context sContext;

    private ILocationListener mLocationListener = new ILocationListener() {
        @Override
        public void notifyCellLocationChanged(Location location) {
            mUpdatedLocation = location;
        }
    };

    public static LocationHandler getInstance(Context context) {
        sContext = context;
        if (sInstance == null) {
            sInstance = new LocationHandler();
        }

        return sInstance;
    }

    @SuppressWarnings("ResourceType")
    public Location getUpdatedLocation() {
        if(mUpdatedLocation==null){
            mUpdatedLocation=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        return mUpdatedLocation;
    }

    @SuppressWarnings("ResourceType")
    public void registerLocation() {
        mLocationManager = (LocationManager) sContext.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 100f, new RLocationListener(mLocationListener, sContext));

    }





}
