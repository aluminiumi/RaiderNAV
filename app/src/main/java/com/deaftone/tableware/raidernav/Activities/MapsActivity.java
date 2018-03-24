package com.deaftone.tableware.raidernav.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.deaftone.tableware.raidernav.AddressMap;
import com.deaftone.tableware.raidernav.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle extras = getIntent().getExtras();
        final String destinationName = extras.getString("destinationName");
        if(destinationName != null) {
            String destXYstring = AddressMap.fetch(destinationName); //looks like "33.593170, -101.897627"
            System.out.println(destXYstring);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints = new ArrayList<>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true); //Yong 03082018
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);   //Yong 03082018
        mMap.setOnMapLongClickListener( new GoogleMap.OnMapLongClickListener()
        {
            @Override
            public void onMapLongClick(LatLng latLng) {
                {
                    if (listPoints.size() == 2)
                    {
                        listPoints.clear();
                        mMap.clear();
                    }
                    listPoints.add(latLng);
                    MarkerOptions markerOptions= new MarkerOptions();
                    markerOptions.position(latLng);

                    if  (listPoints.size()==1)
                    {
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }
                    else
                    {
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }
                    mMap.addMarker(markerOptions);
                    //TODO get direction url

                }
            }

        }


       );
     }

    @SuppressLint({"MissingPermissions", "MissingPermission"})
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
        //super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
        // Add a marker in Sydney and move the camera
        // TTU memorial circle: 33.584468, -101.874658
        LatLng ttu = new LatLng(33.584468, -101.874658);
        mMap.addMarker(new MarkerOptions().position(ttu).title("Texas Tech Memorial Circle"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ttu));
    }

    // Add a marker in Sydney and move the camera}
    // LatLng sydney = new LatLng(-34, 151);
    // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

}
