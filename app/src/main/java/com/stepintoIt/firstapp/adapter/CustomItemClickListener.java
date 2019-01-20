package com.stepintoIt.firstapp.adapter;

import android.view.View;

import com.stepintoIt.firstapp.model.Product;

public interface CustomItemClickListener {

    public void onItemClick(View v, int position, Product product);
}
