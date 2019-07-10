package com.example.getlocationactivity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Narerit on 7/10/2019.
 */
public class MyLocationListener implements LocationListener {
    final String TAG = "Monitor Location";

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
