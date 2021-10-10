package com.example.notes.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.notes.MainActivity;
import com.example.notes.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //hiding the action bar
        getSupportActionBar().hide();
        //code for the Splash Screen implementation
          new Handler().postDelayed(() -> {
              //changes to mainActivity after 2sec and finishes this activity
              startActivity(new Intent(SplashScreen.this, MainActivity.class));
              finish();
          },2000);
    }
}