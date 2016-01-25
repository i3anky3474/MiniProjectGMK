package com.gmk.miniproject.app.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.gmk.miniproject.app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMyLocationChangeListener{

    private GoogleMap mMap;
    Circle myCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide TitleBar
        hideTitleBar();

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //For My Location
        //setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
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
        mMap.setOnMyLocationChangeListener(this);
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(2));

        //Add Article Marker
        //Toast.makeText(this,"LAT,LON : "+DetailActivity.articleModelClick.getLat()+" : "+DetailActivity.articleModelClick.getLon(),Toast.LENGTH_LONG).show();
        if (isDouble(DetailActivity.articleModelClick.getLat()) && isDouble(DetailActivity.articleModelClick.getLon())) {
            LatLng sydney = new LatLng(Double.parseDouble(DetailActivity.articleModelClick.getLat()), Double.parseDouble(DetailActivity.articleModelClick.getLon()));
            //LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Article Red Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_icon)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(2));
        }

//        //Add Current Location
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        if(location!=null)
//        {
//            double longitude = location.getLongitude();
//            double latitude = location.getLatitude();
//            Toast.makeText(this, "LAT,LON : " + longitude + " : " + latitude, Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(this, "LAT,LON : NULL", Toast.LENGTH_LONG).show();
//        }
    }

    private void hideTitleBar() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void onMyLocationChange(Location location) {
        //double longitude = location.getLongitude();
        //double latitude = location.getLatitude();
        //Toast.makeText(this, "LAT,LON : " + longitude + " : " + latitude, Toast.LENGTH_LONG).show();

//        //To clear map data Add current location
//        mMap.clear();
//
//        //To hold location
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//
//        //To create marker in map
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("I'm Here");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_icon));
//        //adding marker to the map
//        mMap.addMarker(markerOptions);
//
//        //opening position with some zoom level in the map
//        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 2.0f));
//        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
//
//        //Add Location Acticle
//        if (isDouble(DetailActivity.articleModelClick.getLat()) && isDouble(DetailActivity.articleModelClick.getLon())) {
//            LatLng sydney = new LatLng(Double.parseDouble(DetailActivity.articleModelClick.getLat()), Double.parseDouble(DetailActivity.articleModelClick.getLon()));
//            //LatLng sydney = new LatLng(-34, 151);
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Article Red Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_icon)));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//            //mMap.animateCamera(CameraUpdateFactory.zoomTo(2));
//        }
    }
}
