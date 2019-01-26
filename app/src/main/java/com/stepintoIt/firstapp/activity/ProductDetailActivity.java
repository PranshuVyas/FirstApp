package com.stepintoIt.firstapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stepintoIt.firstapp.R;
import com.stepintoIt.firstapp.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity {

    @BindView(R.id.txt_product_name)
    TextView productName;

    @BindView(R.id.img_item)
    ImageView imgItem;

    public static final String KEY_PRODUCT = "product";
    public static final String IPHONEX_IMAGE_URL = "https://i.imgur.com/vsGLfep.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra(KEY_PRODUCT);
        productName.setText("Product detail : " + product.getName());
        Picasso.get().load(IPHONEX_IMAGE_URL).into(imgItem);
    }
}
