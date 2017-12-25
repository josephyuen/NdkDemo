package com.example.joseph.ndkdemo;

/**
 * Created by joseph on 2017/11/9.
 */

public class Crytor {
    static{
        System.loadLibrary("crypt");
    }

    public static native int getMaxOrMin(int array[],boolean max);

    public static native void cryptFile(String src,String dest);

    public static native void decryptFile(String src,String dest);

    public static native String getName();

    public static native void testMethod1();

}
