package com.deaftone.tableware.raidernav.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.deaftone.tableware.raidernav.AddressMap;
import com.deaftone.tableware.raidernav.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;

    //private static final int INITIAL_STROKE_WIDTH_PX = 10;

    Location myLocation;
    LatLng singleDestination; //gps coordinates for destination
    String singleDestinationName;
    LocationManager locationManager;
    String bestProvider;
    boolean pathDrawn = false;
    boolean isLoneDestination = false;

    @SuppressLint({"MissingPermissions", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle extras = getIntent().getExtras();

        if(havePermissions()) {
            try {
                isLoneDestination = extras.getBoolean("isLoneDestination");
            } catch(NullPointerException e) { //this will happen if extras.getString has no 'destinationName' key
                e.printStackTrace();
            }

            if(isLoneDestination) { //user clicked compass icon on main screen
                singleDestinationName = extras.getString("destinationName");
                if (singleDestinationName != null) {
                    //String destXYstring = AddressMap.fetch(singleDestinationName); //looks like "33.593170, -101.897627"
                    //System.out.println("onCreate: destXYstring: " + destXYstring);
                    //double[] dests = AddressMap.parseXY(destXYstring);
                    //singleDestination = new LatLng(dests[0], dests[1]);
                    singleDestination = AddressMap.getLatLng(singleDestinationName);
                }
            } else { //user clicked map icon on main screen


            }

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            listPoints = new ArrayList<>();

            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            bestProvider = locationManager.getBestProvider(criteria, true);
            for (String provider : locationManager.getAllProviders()) {
                locationManager.requestLocationUpdates(provider, 1000, 0, this);
            }
            myLocation = getLastKnownLocation();
            if (myLocation != null) {
                onLocationChanged(myLocation);
            }
        }
    }

    @SuppressLint({"MissingPermissions", "MissingPermission"})
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("ONREQUESTPERMISSIONSRESULT");
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mMap != null)
                        mMap.setMyLocationEnabled(true);

                }
                break;
        }
        //super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
        // TTU memorial circle: 33.584468, -101.874658
        //LatLng ttu = new LatLng(33.584468, -101.874658);
        //mMap.addMarker(new MarkerOptions().position(ttu).title("Texas Tech Memorial Circle"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ttu));
    }

    @Override
    public boolean onMyLocationButtonClick() {
        pathDrawn = false;
        if(havePermissions()) {
            myLocation = getLastKnownLocation();
            System.out.println("onMyLocationButtonClick(): Last known location: " + myLocation);
            //Toast.makeText(this, "Last known location: "+myLocation, Toast.LENGTH_SHORT).show();

            //onLocationChanged(myLocation);
            if (myLocation != null) {
                onLocationChanged(myLocation);
            } else {
                Toast.makeText(this, "Unable to determine your location.", Toast.LENGTH_SHORT).show();
            }

        }

        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    public void onLocationChanged(Location location) {
        //Toast.makeText(this, "location changed: "+location, Toast.LENGTH_SHORT).show();
        //System.out.println("location changed: "+location);
        myLocation = location;
        if(myLocation != null && mMap != null) {
            if (isLoneDestination && singleDestination != null) { //handle single destination
                doPathToSingleDestination();
            } else { //handle schedule-based waypoints

            }
        }
    }

    @Override
    public void onProviderEnabled(String s) {
        System.out.println("Provider enabled: "+s);
        Toast.makeText(this, "Enabled new provider " + bestProvider,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        System.out.println("Provider disabled: "+s);
        Toast.makeText(this, "Disabled provider " + bestProvider,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle b) {
        //System.out.println("Status changed: "+s+" "+i); //this is spammy

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint({"MissingPermissions", "MissingPermission"})
    @Override
    public void onMapReady(GoogleMap googleMap) {
        System.out.println("onMapReady(): Map ready.");

        mMap = googleMap;

        //By default, show the map zoomed in on Memorial Circle until
        //we know what the user wants to do
        LatLng ttu = new LatLng(33.584468, -101.874658);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ttu, 15));


        mMap.getUiSettings().setZoomControlsEnabled(true); //Yong 03082018

        if(havePermissions()) {

            mMap.setMyLocationEnabled(true);   //Yong 03082018
            mMap.setOnMyLocationButtonClickListener(this);


            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    {
                        if (listPoints.size() == 2) {
                            listPoints.clear();
                            mMap.clear();
                        }
                        listPoints.add(latLng);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);

                        if (listPoints.size() == 1) {
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        } else {
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        }
                        mMap.addMarker(markerOptions);
                        //TODO get direction url

                    }
                }

            });
        }

     }

    private void doPathToSingleDestination() {
        if(singleDestination != null) {

            //LatLng ttu = new LatLng(33.584468, -101.874658);

            //simple line from source to destination; replace with actual directions
            /*mMap.addPolyline(new PolylineOptions()
                    .add(ttu, singleDestination)
                    .width(INITIAL_STROKE_WIDTH_PX)
                    .color(Color.BLUE)
                    .geodesic(true)
                    .clickable(false));
            */
            //mMap.addMarker(new MarkerOptions().position(ttu).title("Texas Tech Memorial Circle"));

            mMap.addMarker(new MarkerOptions().position(singleDestination).title(singleDestinationName));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(singleDestination, 15));

            final String serverKey = getApplicationContext().getString(R.string.directions_key);
            if(myLocation != null) {
                if(!pathDrawn) {
                    System.out.println("Creating oneshot destination");
                    LatLng origin = new LatLng(myLocation.getLatitude(), myLocation.getLongitude()); //ttu;
                    LatLng destination = singleDestination;
                    GoogleDirection.withServerKey(serverKey)
                            .from(origin)
                            .to(destination)
                            .transportMode(TransportMode.WALKING)
                            .execute(new DirectionCallback() {
                                @Override
                                public void onDirectionSuccess(Direction direction, String rawBody) {
                                    System.out.println("Direction success: " + rawBody);
                                    Route route = direction.getRouteList().get(0);
                                    Leg leg = route.getLegList().get(0);
                                    ArrayList<LatLng> directionPositionList = leg.getDirectionPoint();
                                    PolylineOptions polylineOptions = DirectionConverter.createPolyline(getApplicationContext(), directionPositionList, 5, Color.RED);
                                    mMap.addPolyline(polylineOptions);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()), 15));
                                }

                                @Override
                                public void onDirectionFailure(Throwable t) {
                                    System.out.println(t);
                                }
                            });
                    pathDrawn = true;
                }
            } else {
                //this should not happen
                Toast.makeText(this, "No location; aborted directions", 5).show();
            }
        } else {
            //System.out.println("Do not have oneshot destination");
        }
    }

    @SuppressLint({"MissingPermissions", "MissingPermission"})
    private Location getLastKnownLocation() {
        System.out.println("getLastKnownLocation(): started");

        if(havePermissions()) {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            try {
                List<String> providers = locationManager.getProviders(true);
                if (providers != null) {
                    Location bestLocation = null;
                    for (String provider : providers) {
                        Location l = locationManager.getLastKnownLocation(provider);
                        if (l == null) {
                            System.out.println("getLastKnownLocation(): Location from provider " + provider + ": null");
                            continue;
                        }
                        System.out.println("getLastKnownLocation(): Location from provider " + provider + ": " + l);
                        if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                            // Found best last known location: %s", l);
                            bestLocation = l;
                        }
                    }
                    return bestLocation;
                }
            } catch (NullPointerException e) { //locationManager.getProviders was null
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean havePermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("getPermissions(): Permission to GPS denied.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            //return false;
        } else if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("getPermissions(): Permission to coarse location denied.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST);
            return false;
        }
        return true;
    }



}
