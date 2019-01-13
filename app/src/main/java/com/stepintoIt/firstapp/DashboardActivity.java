package com.stepintoIt.firstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.txt_username)
    TextView txtUsername;


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(LoginActivity.SHAREDPREFENCE_NAME, MODE_PRIVATE);
        String userName = sharedPreferences.getString(LoginActivity.KEY_USERNAME, "");
        txtUsername.setText("USERNAME : " + userName);
        new LogingAsyncTask().execute("http://my-json-server.typicode.com/PranshuVyas/FirstApp/productData");
        Timber.plant(new Timber.DebugTree());


    }

    private class LogingAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // This is getting the url from the string we passed in
                URL url = new URL(strings[0]);

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                //urlConnection.setDoInput(true);
                //urlConnection.setDoOutput(true);

                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("GET");


                // OPTIONAL - Sets an authorization header
                urlConnection.setRequestProperty("Authorization", "someAuthString");
                JSONObject requestParameterJsonObject = null;


                // Send the post body
                if (requestParameterJsonObject != null) {
                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                    writer.write(requestParameterJsonObject.toString());
                    writer.flush();
                }

                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {

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
                JSONArray requestInArray = new JSONArray(result);

                for (int i = 0; i <requestInArray.length(); i++) {
                    Timber.i("product = " + i);
                    JSONObject productJsonObject = requestInArray.getJSONObject(i);
                    String name = productJsonObject.getString("name");
                    String productID = productJsonObject.getString("productId");
                    String description = productJsonObject.getString("description");
                    String weight = productJsonObject.getString("weight");
                    JSONArray images = productJsonObject.getJSONArray("images");
                    String phone = productJsonObject.getString("phone");
                    String web = productJsonObject.getString("web");
                    double price = productJsonObject.getDouble("price");
                    JSONArray tags = productJsonObject.getJSONArray("tags");
                    JSONObject dimensions = productJsonObject.getJSONObject("dimensions");
                    double length = dimensions.getDouble("length");
                    double width = dimensions.getDouble("width");
                    double height = dimensions.getDouble("height");
                    JSONObject warehouselocationn = productJsonObject.getJSONObject("warehouseLocation");
                    double latitude = warehouselocationn.getDouble("latitude");
                    double longitude = warehouselocationn.getDouble("longitude");
                    Timber.i("The product name is " + name);
                    Timber.i("The product id " + productID);
                    Timber.i("The product description " + description);
                    Timber.i("The product weight " + weight);
                    Timber.i("Images " + images.toString());
                    Timber.i("phone : " + phone);
                    Timber.i("web : " + web);
                    Timber.i("price " + price);
                    Timber.i("tags  " + tags.toString());
                    Timber.i("The product lenght is " + length);
                    Timber.i("The product width is " +  width);
                    Timber.i("The product height is " + height);
                    Timber.i("The product latitude is " + latitude);
                    Timber.i("The product lognitude is " + longitude);



                }




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @OnClick(R.id.btn_logout)
    public void logOut() {
        SharedPreferences.Editor preferedEditor = sharedPreferences.edit();
        preferedEditor.putString(LoginActivity.KEY_USERNAME, "");
        preferedEditor.apply();
        Intent mainIntent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
