package com.stepintoIt.firstapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stepintoIt.firstapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

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
    public static final String BASE_URL = "https://reqres.in/api/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(SHAREDPREFENCE_NAME, MODE_PRIVATE);
        Timber.plant(new Timber.DebugTree());
    }
    @OnClick(R.id.btn_submit)
    public void logIn() {
        new LogingAsyncTask().execute(etxUserName.getText().toString(), etxPassword.getText().toString(), BASE_URL);


    }

    private class LogingAsyncTask extends AsyncTask<String, Integer, String>{

        @Override
        protected void onPreExecute() {
            pbLogin.setVisibility(View.VISIBLE);
            txtCount.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... strings) {
            String userName = strings[0];
            String passWord = strings[1];
            try {
                // This is getting the url from the string we passed in
                URL url = new URL(strings[2]);

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("POST");


                // OPTIONAL - Sets an authorization header
                urlConnection.setRequestProperty("Authorization", "someAuthString");
                JSONObject requestParameterJsonObject = new JSONObject();
                requestParameterJsonObject.put("email", userName);
                requestParameterJsonObject.put("password", passWord);

                // Send the post body
                if (requestParameterJsonObject != null) {
                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                    writer.write(requestParameterJsonObject.toString());
                    writer.flush();
                }

                int statusCode = urlConnection.getResponseCode();

                if (statusCode ==  200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    String response = convertInputStreamToString(inputStream);
                    return response;

                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    // Status code is not 200
                    // Do something to handle the error
                }

            } catch (Exception e) {
               e.printStackTrace();
            }
            return null;
        }

        private String convertInputStreamToString(InputStream inputStream) {
            try {
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');
                }
                return total.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(final Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject responseJsonObject = new JSONObject(result);
                String token = responseJsonObject.getString("token");


                if (token != null && !token.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "sucess", Toast.LENGTH_LONG).show();
//                    SharedPreferences.Editor preferedEditor = sharedPreferences.edit();
//                    preferedEditor.putString(KEY_USERNAME, etxUserName.getText().toString());
//                    preferedEditor.apply();
                     Intent mainIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                     startActivity(mainIntent);

                    //Timber.i("name : " + token);
//                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(LoginActivity.this, "Test", Toast.LENGTH_LONG).show();

            pbLogin.setVisibility(View.GONE);
            txtCount.setVisibility(View.GONE);
        }
    }




}
