package com.stepintoIt.firstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.txt_username) TextView txtUsername;
    Button btnLogOut;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        sharedPreferences =  getSharedPreferences(LoginActivity.SHAREDPREFENCE_NAME, MODE_PRIVATE);
        String userName = sharedPreferences.getString(LoginActivity.KEY_USERNAME, "");
        txtUsername.setText("USERNAME : " + userName);
    }

    @OnClick(R.id.btn_logout)
    public void logOut(){
        SharedPreferences.Editor preferedEditor = sharedPreferences.edit();
        preferedEditor.putString(LoginActivity.KEY_USERNAME, "");
        preferedEditor.apply();
        Intent mainIntent = new Intent(DashboardActivity.this,LoginActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
