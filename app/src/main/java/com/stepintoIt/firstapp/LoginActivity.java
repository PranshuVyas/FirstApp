package com.stepintoIt.firstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.pb_login)
    ProgressBar pbLogin;


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
        new LogingAsyncTask().execute(etxUserName.getText().toString(), etxPassword.getText().toString());


    }

    private class LogingAsyncTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected void onPreExecute() {
            pbLogin.setVisibility(View.VISIBLE);
            txtCount.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String userName = strings[0];
            String passWord = strings[1];

            for (int i = 0; i <5 ; i++) {

                try {
                    Thread.sleep(1000);
                    onProgressUpdate(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (etxUserName.getText().toString().equals("admin")
                    && etxPassword.getText().toString().equals("1234")){
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(final Integer... values) {
            super.onProgressUpdate(values);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtCount.setText("count = " + values[0]);
                }
            });

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == true) {
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

            pbLogin.setVisibility(View.GONE);
            txtCount.setVisibility(View.GONE);
        }
    }




}
