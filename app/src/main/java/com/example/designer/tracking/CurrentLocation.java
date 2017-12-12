/*
package com.example.designer.tracking;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import android.location.LocationListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocation extends AppCompatActivity  implements LocationListener {

    LocationManager locationManager;
    String mprovider;
    String lat,longi;
    String fulladd;
    TextView textview;
    Geocoder geocoder;
    List<Address> addresses;
    Double latitde;
    Double longitude;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        textview=(TextView)findViewById(R.id.tv1);




        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // Start your app main activity
                Intent i = new Intent(CurrentLocation.this, MainActivity.class);
                i.putExtra("lg", longitude);
                i.putExtra("lt", latitde);
                i.putExtra("add", fulladd  );
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }



    @Override
    public void onLocationChanged(Location location) {
        TextView longitudee = (TextView) findViewById(R.id.textView);
        TextView latitudee = (TextView) findViewById(R.id.textView1);

        longitudee.setText(""+ location.getLongitude());
        latitudee.setText("" + location.getLatitude());

        longitude=location.getLongitude();
        latitde=location.getLatitude();


Toast.makeText(CurrentLocation.this,""+longitude+""+latitde,Toast.LENGTH_LONG).show();




        geocoder = new Geocoder(this, Locale.getDefault());


        try {
            addresses = geocoder.getFromLocation(latitde,longitude,1);
            String address= addresses.get(0).getAddressLine(0);
            String area= addresses.get(0).getLocality();
            String city= addresses.get(0).getAdminArea();
            String country= addresses.get(0).getCountryName();

             fulladd= address+","+area+","+city+","+country;
            textview.setText(fulladd);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}*/
