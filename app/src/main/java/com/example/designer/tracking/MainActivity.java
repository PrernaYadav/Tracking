package com.example.designer.tracking;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {



    LocationManager locationManager;
    String mprovider;
    String fulladd;
    TextView textview;
    Geocoder geocoder;
    List<Address> addresses;
    Double latitde;
    Double longitude;


    double latitude;
    double longitud;

    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;

    // Google Map
    private GoogleMap googleMaphistory;

    String addresssss;
    Double lat,log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


        try {
            // Loading map
            initilizeMap();
            googleMaphistory.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // Showing / hiding your current location

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
            }
            googleMaphistory.setMyLocationEnabled(true);

            // Enable / Disable zooming controls
            googleMaphistory.getUiSettings().setZoomControlsEnabled(false);

            // Enable / Disable my location button
            googleMaphistory.getUiSettings().setMyLocationButtonEnabled(true);

            // Enable / Disable Compass icon
            googleMaphistory.getUiSettings().setCompassEnabled(true);

            // Enable / Disable Rotate gesture
            googleMaphistory.getUiSettings().setRotateGesturesEnabled(false);

            // Enable / Disable zooming functionality
            googleMaphistory.getUiSettings().setZoomGesturesEnabled(true);


            // latitude and longitude
             latitude = latitde;
             longitud =longitude;


            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitud)).title(addresssss);
            googleMaphistory.addMarker(marker);
            


            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(latitude, longitud)).zoom(15).build();

            googleMaphistory.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initilizeMap() {
        if (googleMaphistory == null) {
            googleMaphistory = ((MapFragment) getFragmentManager().findFragmentById(R.id.maphistory)).getMap();

            // check if map is created successfully or not
            if (googleMaphistory == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();


    }

    @Override
    public void onLocationChanged(Location location) {


        longitude=location.getLongitude();
        latitde=location.getLatitude();


        Toast.makeText(MainActivity.this,""+longitude+""+latitde,Toast.LENGTH_SHORT).show();




        geocoder = new Geocoder(this, Locale.getDefault());


        try {
            addresses = geocoder.getFromLocation(latitde,longitude,1);
            String address= addresses.get(0).getAddressLine(0);
            String area= addresses.get(0).getLocality();
            String city= addresses.get(0).getAdminArea();
            String country= addresses.get(0).getCountryName();

            addresssss= address+","+area+","+city+","+country;
            Toast.makeText(MainActivity.this,addresssss,Toast.LENGTH_LONG).show();





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
}

