package com.stepintoIt.firstapp.model;

import com.google.gson.annotations.SerializedName;

public class MyResponse {

    @SerializedName("data")
    User user;

    public User getUser() {
        return user;
    }
}
