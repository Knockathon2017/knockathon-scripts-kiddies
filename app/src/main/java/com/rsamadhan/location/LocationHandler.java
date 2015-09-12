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

    public Location getUpdatedLocation() {
        return mUpdatedLocation;
    }

    @SuppressWarnings("ResourceType")
    public void registerLocation() {
        LocationManager manager = (LocationManager) sContext.getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 100f, new RLocationListener(mLocationListener, sContext));
    }





}
