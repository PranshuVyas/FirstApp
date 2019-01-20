package com.stepintoIt.firstapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stepintoIt.firstapp.R;

public class SplashScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getSharedPreferences(LoginActivity.SHAREDPREFENCE_NAME, MODE_PRIVATE);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                if (sharedPreferences.getString(LoginActivity.KEY_USERNAME, "").isEmpty()){
                    Intent mainIntent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(mainIntent);
                }
                else{
                    Intent mainIntent = new Intent(SplashScreenActivity.this,DashboardActivity.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
