package com.study.maplib;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Created by joseph on 2017/12/28.
 */

public abstract class MapInitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        initMap(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    private static final String TAG = "MapInitActivity";
    MapView mapView;
    private MapboxMap mapboxMap;

    private void initMap(Bundle savedInstanceState) {
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
//                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                if(locationManager == null){
//                    return;
//                }
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0.0f, mLocationListener);
//                Location location;
//                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//                int num = 0;
//
////                Timer timer = new Timer();
//
//
//
//                while(location == null){
//                    num ++;
////                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0.0f, mLocationListener);
//                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//
//                    if(location != null){
//                        Log.e(TAG,location.getLatitude()+"--"+location.getLongitude());
//                    }else{
//                        Log.e(TAG,locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)+"location is null");
//                    }
//                }
//

                       Location location = LocationUtils.getInstance().getLocation(MapInitActivity.this,mLocationListener);
                       mLocationListener.onLocationChanged(location);

                       addMarkers(mapboxMap);
                       MapInitActivity.this.mapboxMap = mapboxMap;

            }
        });
    }



    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if(location != null){
                Log.i(TAG,location.getLatitude()+"---"+location.getLongitude());
            }else{
                Log.i(TAG,"null");
            }
                if(mapboxMap != null){
                LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(location.getLatitude(),location.getLongitude())).build();
                mapboxMap.setLatLngBoundsForCameraTarget(bounds);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i("locationpro","enable:"+provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i("locationpro","disable:"+provider);
        }
    };







    //添加标记
    abstract void addMarkers(MapboxMap mapboxMap);


    /**
     * -----------MapView lifecycle------------
     */
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}