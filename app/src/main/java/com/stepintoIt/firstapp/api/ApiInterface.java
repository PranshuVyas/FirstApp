package com.stepintoIt.firstapp.api;

import com.stepintoIt.firstapp.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

@GET("/PranshuVyas/FirstApp/productData")
Call<ArrayList<Product>> getProducts();

}
