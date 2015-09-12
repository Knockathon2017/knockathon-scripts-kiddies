package com.rsamadhan.location;


import android.location.Location;

public interface ILocationListener {
	public void notifyCellLocationChanged(Location location);
}
