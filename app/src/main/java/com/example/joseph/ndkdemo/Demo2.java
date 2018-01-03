package com.example.joseph.ndkdemo;

/**
 * Created by joseph on 2018/1/2.
 */

public class Demo2 {
    private static final String TAG = "Demo2";
    static {
        System.loadLibrary("demo2");
    }

    public int num = 0;
    public native static void getNUM(String s);

    public native void sayHello();
}
