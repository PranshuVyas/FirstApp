package com.stepintoIt.firstapp.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

public class Get {
    private int userId;

    @SerializedName("id")
    public int id;

    private String title;

    private String body;

    public int getUserId() {
        return userId;
    }

    public double getIds() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
