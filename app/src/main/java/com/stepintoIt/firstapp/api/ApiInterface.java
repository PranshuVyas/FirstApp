package com.stepintoIt.firstapp.api;

import com.stepintoIt.firstapp.model.Get;
import com.stepintoIt.firstapp.model.MyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

//@GET("/PranshuVyas/FirstApp/productData")
//Call<ArrayList<Product>> getProducts();


@GET("/posts")
Call<ArrayList<Get>> getJsonArray();


@GET("/api/users/2")
Call<MyResponse> getUser();

}
