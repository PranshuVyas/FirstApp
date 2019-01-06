package com.stepintoIt.firstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etxUserName;
    EditText etxPassword;
    Button btnSubmit;
    private SharedPreferences sharedPreferences;
    public static final String SHAREDPREFENCE_NAME = "FirstApp";
    public static final String KEY_USERNAME = "user_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etxUserName = findViewById(R.id.etx_username);
        etxPassword = findViewById(R.id.etx_password);
        btnSubmit = findViewById(R.id.btn_submit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etxUserName.getText().toString().equals("admin")
                        && etxPassword.getText().toString().equals("1234")) {
                    Toast.makeText(LoginActivity.this, "sucess", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor preferedEditor = sharedPreferences.edit();
                    preferedEditor.putString(KEY_USERNAME, etxUserName.getText().toString());
                    preferedEditor.apply();
                    Intent mainIntent = new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_LONG).show();
                }
            }
        });
        sharedPreferences = getSharedPreferences(SHAREDPREFENCE_NAME, MODE_PRIVATE);


    }


}
