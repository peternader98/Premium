package com.example.premium;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import gr.net.maroulis.library.EasySplashScreen;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen splashScreen = new EasySplashScreen(Splash_screen.this)
                .withFullScreen()
                .withTargetActivity(LogIn.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#FF9900"))
                .withLogo(R.drawable.menu);

        setContentView(splashScreen.create());
    }
}
