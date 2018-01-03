package com.example.joseph.ndkdemo;

import android.app.Application;

import com.mapbox.mapboxsdk.Mapbox;

/**
 * Created by joseph on 2017/12/28.
 */

public class NdkApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Mapbox.getInstance(getApplicationContext(),"pk.eyJ1IjoibWFyc2hhbGxwZWkiLCJhIjoiY2picHg4ZXdrNGQ1ZTMzcXk3eDlybWhqciJ9.134O2N0vviE0OgoMrdgFjg");
    }
}
