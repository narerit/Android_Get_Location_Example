package com.example.getlocationactivity;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Monitor Location";
    //TODO 4 Initialize our location manager and location listener class here as fields
    private LocationListener mNetworkListener;
    private LocationManager mLm;
    private static final int REQUEST_LOCATION = 1;
    //Just in case to show data but it's not prefer to use static with TextView
    private static TextView text_time;
    private static TextView text_latlng;
    private static TextView text_accuracy;
    private static TextView text_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = findViewById(R.id.btn_start);
        Button btn_stop = findViewById(R.id.btn_stop);
        text_name = findViewById(R.id.text_name);
        text_time = findViewById(R.id.text_time);
        text_latlng = findViewById(R.id.text_latlng);
        text_accuracy = findViewById(R.id.text_accuracy);

        //TODO 1 : At first Require user's location access permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        //TODO 7 : Set start and stop listening method into button onclick.
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartListening();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStopListening();
            }
        });

    }

    //TODO 2 : Create static method for call on MyLocationListener class to update text view when location change

    public static void setLocationInfo(Location location) {
        //Extract information from location that we pass from location listener
        String provider = location.getProvider();
        String lat = String.valueOf(location.getLatitude());
        String lng = String.valueOf(location.getLongitude());
        String accuracy = String.valueOf(location.getAccuracy());
        String time = timeFormat(location.getTime());
        //Set our info into text views
        text_name.setText(provider);
        text_time.setText(time);
        text_latlng.setText(lat + "," + lng);
        text_accuracy.setText(accuracy);

    }
    //TODO 3 : Create method to convert ticks into time format
    private static String timeFormat(Long ticks) {
        String date = DateFormat.format("dd-MM-yyyy HH:mm:ss", ticks).toString();
        return date;
    }

    //TODO 5 : Create method for binding our location listener with location manager
    @SuppressLint("MissingPermission")
    private void onStartListening() {

        Log.d(TAG, "onStartListening: started");
        mLm = (LocationManager) getSystemService(LOCATION_SERVICE);
        mNetworkListener = new MyLocationListener();
        mLm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mNetworkListener);

    }

    //TODO 6 : Don't forget to stop location listening because it will continue running if you doesn't fully close your app
    void  doStopListening(){
        if (mNetworkListener != null){
            mLm.removeUpdates(mNetworkListener);
            Toast.makeText(this, "Successfully Stop Listening.", Toast.LENGTH_SHORT).show();
        }
    }
}
