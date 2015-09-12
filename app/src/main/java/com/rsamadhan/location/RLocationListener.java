package com.rsamadhan.location;

/**
 * Custom class for registering location listener
 */

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class RLocationListener implements LocationListener {

	private ILocationListener mILocationListener;


	private static final int ACCURACY_THRESHOLD = 200;
	
	private Double mLongitude=(double) 000;
	private Double mLatitude=(double) 000;
	private Location mCurrentLocation;
	private Context mContext;
	
	public RLocationListener(ILocationListener icellLocationListener, Context context) {
		mILocationListener = icellLocationListener;
		mContext = context;
	}
	@Override
	public void onLocationChanged(Location location) {
		setLatitude(location.getLatitude());
		setLongitude(location.getLongitude());
		if (isValidLocation(location)) {
			mILocationListener.notifyCellLocationChanged(location);
		}
	}


	/**
	 * sets the longitude triggered in onLocationChanged
	 * @param longitude - longitude of the user current location
	 */
	private void setLongitude(double longitude) {
		mLongitude = longitude;
	}
	
	/**
	 * 
	 * @return longitude
	 */
	public double getLongitude() {
		return mLongitude;
	}

	/**
	 * sets the latitude triggerd in onLocationChanged
	 * @param latitude - latitude of the user current location
	 */
	private void setLatitude(double latitude) {
		mLatitude = latitude;
	}
	
	/**
	 * 
	 * @return lattitude
	 */
	public double getLatitude() {
		return mLatitude;
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	private boolean isValidLocation(Location newLocation) {
		if (newLocation == null) {
			return false;
		} else if (mCurrentLocation == null) {
			mCurrentLocation = newLocation;
			return true;
		}

		mCurrentLocation=newLocation;

		boolean isValid =  mCurrentLocation.getAccuracy()<=ACCURACY_THRESHOLD;

		return isValid;
	}
}
