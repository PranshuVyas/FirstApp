package com.stepintoIt.firstapp;

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
                } else {
                    Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
