package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ViewFlipper;

public class Loading extends AppCompatActivity {

    ViewFlipper vfr_Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        vfr_Loading = findViewById(R.id.vfr_Loading);

        new CountDownTimer(2100, 1800){
            @Override
            public void onTick(long l) {
                if(!vfr_Loading.isFlipping()){
                    vfr_Loading.startFlipping();
                }
            }

            @Override
            public void onFinish() {
                vfr_Loading.stopFlipping();
                Intent intent = new Intent(Loading.this, MainActivity.class);
                startActivity(intent);
                Loading.this.finish();
            }
        }.start();
    }
}
