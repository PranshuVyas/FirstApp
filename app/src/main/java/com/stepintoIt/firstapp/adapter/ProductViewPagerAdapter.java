package com.stepintoIt.firstapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stepintoIt.firstapp.activity.ProductDetailActivity;
import com.stepintoIt.firstapp.fragments.ProductDetailFragment;
import com.stepintoIt.firstapp.fragments.ProductImageFragment;
import com.stepintoIt.firstapp.fragments.ProductMapFragment;
import com.stepintoIt.firstapp.model.Product;

public class ProductViewPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private Product product;

    public ProductViewPagerAdapter(FragmentManager fragmentManager, Product product) {
        super(fragmentManager);
        this.product = product;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return ProductDetailFragment.newInstance(product);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return ProductImageFragment.newInstance(product);
            case 2: // Fragment # 1 - This will show SecondFragment
                return ProductMapFragment.newInstance(product);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0 ){
            return "Detail ";
        }
        else if (position == 1){
            return  "Image ";
        }
        return "Location";





    }


}

