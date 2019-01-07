package com.stepintoIt.firstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etx_username)
    EditText etxUserName;
    @BindView(R.id.etx_password)
    EditText etxPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;


    private SharedPreferences sharedPreferences;
    public static final String SHAREDPREFENCE_NAME = "FirstApp";
    public static final String KEY_USERNAME = "user_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(SHAREDPREFENCE_NAME, MODE_PRIVATE);
    }

    @OnClick(R.id.btn_submit)
    public void logIn() {
        if (etxUserName.getText().toString().equals("admin")
                && etxPassword.getText().toString().equals("1234")) {
            Toast.makeText(LoginActivity.this, "sucess", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor preferedEditor = sharedPreferences.edit();
            preferedEditor.putString(KEY_USERNAME, etxUserName.getText().toString());
            preferedEditor.apply();
            Intent mainIntent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(mainIntent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_LONG).show();
        }
    }

}
