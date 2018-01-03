package com.example.joseph.ndkdemo;

/**
 * Created by joseph on 2017/12/26.
 */

public class Demo1 {
    static {
        System.loadLibrary("demo1");
    }

    public static native void outPut();

    public static native void doit();

}
