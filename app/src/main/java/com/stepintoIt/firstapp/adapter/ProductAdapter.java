package com.stepintoIt.firstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepintoIt.firstapp.R;
import com.stepintoIt.firstapp.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context context;
    private ArrayList<Product> productArrayList;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList){

        this.context = context;
        this.productArrayList = productArrayList;
    }




    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cell_product, null, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = productArrayList.get(i);
        productViewHolder.txtPrice.setText("Price = " +product.getPrice());
        productViewHolder.txtProductname.setText(product.getName());
        Timber.i("The product name is " + product.getName());
        Timber.i("The product id " + product.getProductId());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_price)
        public TextView txtPrice;
        @BindView(R.id.txt_productname)
        public TextView txtProductname;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView );
        }


    }

}
