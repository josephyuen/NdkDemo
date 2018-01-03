package com.study.maplib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by joseph on 2017/12/28.
 */

public class LocationUtils {
    private static String TAG = LocationUtils.class.getSimpleName();
    public static int GPS_LOCATION_REQUEST_CODE = 9996;
    private static final String API_GOOGLE_LOCATION = "https://maps.googleapis.com/maps/api/geocode/json?";  //latlng=28.131486,112.991047&sensor=true";


    private LocationUtils() {
    }

    //在第一次被引用时被加载
    private static class NestClass {
        private static LocationUtils instance = new LocationUtils();
    }

    public static LocationUtils getInstance() {
        return NestClass.instance;
    }

    /**
     * 检查是否开启GPS或AGPS功能
     *
     * @return
     */
    public boolean isOpenGPS(Context context) {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        }
        return false;
    }

    /**
     * 开启GPS
     *
     * @param activity
     */
    public void openGPS(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(intent, GPS_LOCATION_REQUEST_CODE);
    }


    @SuppressLint("MissingPermission")
    public Location getLocation(Context context, LocationListener mListener) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        final Location location;

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f,mListener);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            String locationStr = "纬度：" + location.getLatitude() + "\n" + "经度：" + location.getLongitude();
            Log.e(TAG, locationStr);

            new Thread(new Runnable() {
                @Override
                public void run() {
                   getLocationFromServer(location);
                }
            }).start();

        } else {
            Log.e(TAG, "location = null");

        }

        return location;
    }


    private String getLocationFromServer(Location location){
        StringBuilder builder = new StringBuilder();
        builder.append(API_GOOGLE_LOCATION).append("latlng=").append(location.getLatitude()).append(",").
                append(location.getLongitude()).append("&sensor=true");
        try {
//            URL url = new URL(builder.toString());
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=28.131486,112.991047&sensor=true");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream is = conn.getInputStream();

            Log.i(TAG,conn.getResponseCode() + "---------" + conn.getResponseMessage());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int c = is.read();
            while(c != -1){
                baos.write(c);
                c = is.read();
            }
            String msg = new String(baos.toByteArray());
            Log.e(TAG,msg);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
