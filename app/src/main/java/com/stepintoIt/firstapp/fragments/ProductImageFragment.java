package com.stepintoIt.firstapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepintoIt.firstapp.R;
import com.stepintoIt.firstapp.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductImageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PRODUCT = "product";
    private Product product;

    @BindView(R.id.txt_data)
    TextView txtData;


    public ProductImageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProductImageFragment newInstance(Product product) {
        ProductImageFragment fragment = new ProductImageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, product);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = getArguments().getParcelable(ARG_PRODUCT);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this,view);
        txtData.setText("Product image :  " + product.getName());
        return view;
    }


}
