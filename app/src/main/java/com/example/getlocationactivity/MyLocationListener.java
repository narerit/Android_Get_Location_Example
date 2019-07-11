package com.example.getlocationactivity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Narerit on 7/10/2019.
 */
//TODO 1 : Implementing class form LocationListener Class and get require method for override.
public class MyLocationListener implements LocationListener {
    final String TAG = "Monitor Location";

    //TODO 2 : Call setLocationInfo method on MainActivity Class when location changed
    @Override
    public void onLocationChanged(Location location) {
        MainActivity.setLocationInfo(location);
        Log.d(TAG, "onLocationChanged!");
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "Monitor Location - Provider Enabled" + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "Monitor Location - Provider Disabled" + s);
    }
}
