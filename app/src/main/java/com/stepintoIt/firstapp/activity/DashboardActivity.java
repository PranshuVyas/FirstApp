package com.stepintoIt.firstapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stepintoIt.firstapp.R;
import com.stepintoIt.firstapp.adapter.CustomItemClickListener;
import com.stepintoIt.firstapp.adapter.ProductAdapter;
import com.stepintoIt.firstapp.api.ApiClient;
import com.stepintoIt.firstapp.api.ApiInterface;
import com.stepintoIt.firstapp.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.txt_username)
    TextView txtUsername;
    ApiInterface apiInterface;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(LoginActivity.SHAREDPREFENCE_NAME, MODE_PRIVATE);
        String userName = sharedPreferences.getString(LoginActivity.KEY_USERNAME, "");
        txtUsername.setText("USERNAME : " + userName);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        Timber.plant(new Timber.DebugTree());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getProducts();



    }

    private void getProducts(){
        Call<ArrayList<Product>> call = apiInterface.getProducts();
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> productArrayList = response.body();

                ProductAdapter productAdapter = new ProductAdapter(DashboardActivity.this, productArrayList);
                productAdapter.setOnItemClickListener(new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position, Product product) {
                        Log.i("Test","Product Name ===== " + product.getName());
                        Intent intent = new Intent(DashboardActivity.this, ProductDetailActivity.class);
                        intent.putExtra(ProductDetailActivity.KEY_PRODUCT, product);
                        startActivity(intent);

                    }
                });
                rvProduct.setAdapter(productAdapter);


//                for (Product product: productArrayList) {
//                    Timber.i("The product name is " + product.getName());
//                    Timber.i("The product id " + product.getProductId());
//                    Timber.i("The product description " + product.getDescription());
//                    Timber.i("The product weight " + product.getWeight());
//                    Timber.i("Images " + product.getImages());
//                    Timber.i("phone : " + product.getPhone());
//                    Timber.i("web : " + product.getWeb());
//                    Timber.i("price " + product.getPrice());
//                    Timber.i("tags  " + product.getTags());
//                    Timber.i("The product lenght is " + product.getDimensions().getLength());
//                    Timber.i("The product width is " +  product.getDimensions().getWidth());
//                    Timber.i("The product height is " + product.getDimensions().getHeight());
//                    Timber.i("The product latitude is " + product.getWarehouseLocation().getLatitude());
//                    Timber.i("The product lognitude is " + product.getWarehouseLocation().getLongitude());
//                }

            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }



//    private class LogingAsyncTask extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                // This is getting the url from the string we passed in
//                URL url = new URL(strings[0]);
//
//                // Create the urlConnection
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//
//                //urlConnection.setDoInput(true);
//                //urlConnection.setDoOutput(true);
//
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//
//                urlConnection.setRequestMethod("GET");
//
//
//                // OPTIONAL - Sets an authorization header
//                urlConnection.setRequestProperty("Authorization", "someAuthString");
//                JSONObject requestParameterJsonObject = null;
//
//
//                // Send the post body
//                if (requestParameterJsonObject != null) {
//                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
//                    writer.write(requestParameterJsonObject.toString());
//                    writer.flush();
//                }
//
//                int statusCode = urlConnection.getResponseCode();
//
//                if (statusCode == 200) {
//
//                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//
//                    String response = convertInputStreamToString(inputStream);
//                    return response;
//
//                    // From here you can convert the string to JSON with whatever JSON parser you like to use
//                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
//                } else {
//                    // Status code is not 200
//                    // Do something to handle the error
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        private String convertInputStreamToString(InputStream inputStream) {
//            try {
//                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
//                StringBuilder total = new StringBuilder();
//                for (String line; (line = r.readLine()) != null; ) {
//                    total.append(line).append('\n');
//                }
//                return total.toString();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(final Integer... values) {
//            super.onProgressUpdate(values);
//
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            try {
//                JSONArray requestInArray = new JSONArray(result);
//
//                for (int i = 0; i <requestInArray.length(); i++) {
//                    Timber.i("product = " + i);
//                    JSONObject productJsonObject = requestInArray.getJSONObject(i);
//                    String name = productJsonObject.getString("name");
//                    String productID = productJsonObject.getString("productId");
//                    String description = productJsonObject.getString("description");
//                    String weight = productJsonObject.getString("weight");
//                    JSONArray images = productJsonObject.getJSONArray("images");
//                    String phone = productJsonObject.getString("phone");
//                    String web = productJsonObject.getString("web");
//                    double price = productJsonObject.getDouble("price");
//                    JSONArray tags = productJsonObject.getJSONArray("tags");
//                    JSONObject dimensions = productJsonObject.getJSONObject("dimensions");
//                    double length = dimensions.getDouble("length");
//                    double width = dimensions.getDouble("width");
//                    double height = dimensions.getDouble("height");
//                    JSONObject warehouselocationn = productJsonObject.getJSONObject("warehouseLocation");
//                    double latitude = warehouselocationn.getDouble("latitude");
//                    double longitude = warehouselocationn.getDouble("longitude");
//
//                    Product product = new Product();
//                    product.setName(name);
//                    product.setDescription(description);
//                    product.setDimensions(dimensions);
//                    product.setHeight(height);
//                    product.setImages(images);
//                    product.setLatitude(latitude);
//                    product.setLongitude(longitude);
//                    product.setLength(length);
//                    product.setPhone(phone);
//                    product.setPrice(price);
//                    product.setProductID(productID);
//                    product.setTags(tags);
//                    product.setWarehouselocationn(warehouselocationn);
//                    product.setWeb(web);
//                    product.setWeight(weight);
//                    product.setWidth(width);
//
//                    Timber.i("The product name is " + product.getName());
//                    Timber.i("The product id " + product.getProductID());
//                    Timber.i("The product description " + product.getDescription());
//                    Timber.i("The product weight " + product.getWeight());
//                    Timber.i("Images " + product.getImages());
//                    Timber.i("phone : " + product.getPhone());
//                    Timber.i("web : " + product.getWeb());
//                    Timber.i("price " + product.getPrice());
//                    Timber.i("tags  " + product.getTags());
//                    Timber.i("The product lenght is " + product.getLength());
//                    Timber.i("The product width is " +  product.getWidth());
//                    Timber.i("The product height is " + product.getHeight());
//                    Timber.i("The product latitude is " + product.getLatitude());
//                    Timber.i("The product lognitude is " + product.getLongitude());
//
//
//
//                }
//
//
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

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
