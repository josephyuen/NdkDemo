package com.example.joseph.ndkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Runnable{
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.sample_text);
        tv.setText(Crytor.getName());
        edit = findViewById(R.id.editText);
        findViewById(R.id.btn_max).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Demo2 demo2 = new Demo2();
                demo2.sayHello();

                Toast.makeText(MainActivity.this, "value:" + demo2.num, Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.btn_min).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"!!--"+Crytor.getMaxOrMin(StringArrayToIntArray(),false),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int[] StringArrayToIntArray(){
        String s_array[] = edit.getText().toString().trim().split(" ");
        int int_arry[] = new int[s_array.length];
        for (int i = 0;i < s_array.length;i++){
            try{
                int_arry[i] = Integer.parseInt(s_array[i]);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }

        return int_arry;
    }

    @Override
    public void run() {
        startActivity(new Intent(this,VoiceEffectActivity.class));

    }
}
