package com.stepintoIt.firstapp;

import org.json.JSONArray;
import org.json.JSONObject;

public class Product {
    String name;
    String productID;
    String description;
    String weight;
    JSONArray images ;
    String phone;
    String web;
    double price;
    JSONArray tags;
    JSONObject dimensions;
    double length;
    double width;
    double height;
    JSONObject warehouselocationn;
    double latitude;
    double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public JSONArray getImages() {
        return images;
    }

    public void setImages(JSONArray images) {
        this.images = images;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public JSONArray getTags() {
        return tags;
    }

    public void setTags(JSONArray tags) {
        this.tags = tags;
    }

    public JSONObject getDimensions() {
        return dimensions;
    }

    public void setDimensions(JSONObject dimensions) {
        this.dimensions = dimensions;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public JSONObject getWarehouselocationn() {
        return warehouselocationn;
    }

    public void setWarehouselocationn(JSONObject warehouselocationn) {
        this.warehouselocationn = warehouselocationn;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
