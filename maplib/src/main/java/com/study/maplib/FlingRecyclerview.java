package com.study.maplib;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.LruCache;
import android.view.ViewConfiguration;

/**
 * Created by joseph on 2018/1/2.
 */

public class FlingRecyclerview extends RecyclerView{
    private int minFlingVelocity;

    public FlingRecyclerview(Context context) {
        this(context,null);
    }

    public FlingRecyclerview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlingRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        minFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityY = 6 * velocityY;
        return velocityY >= minFlingVelocity && super.fling(velocityX, velocityY);
    }


    private void bitmapCache(){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(maxMemory / 8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }
}