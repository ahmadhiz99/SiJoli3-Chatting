package com.SiJoLi.SiJoLi.Start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.SiJoLi.SiJoLi.Account.LoginMenu;
import com.SiJoLi.SiJoLi.R;

public class SplashScreen extends AppCompatActivity {
    private int waktu_loading=4000;

    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i =new Intent(SplashScreen.this, LoginMenu.class);
                startActivity(i);


            }
        },waktu_loading);
    }
}