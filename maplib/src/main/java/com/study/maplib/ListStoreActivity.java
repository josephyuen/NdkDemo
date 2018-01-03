package com.study.maplib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewConfiguration;

/**
 * Created by joseph on 2018/1/2.
 */

public class ListStoreActivity extends AppCompatActivity{

    private RecyclerView recycler;
    private int minVelocity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_store);
        recycler = findViewById(R.id.recycler);
        minVelocity = ViewConfiguration.get(this).getScaledMinimumFlingVelocity();

        recycler.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                if(velocityY < minVelocity){

                }

                return false;
            }
        });

    }

}