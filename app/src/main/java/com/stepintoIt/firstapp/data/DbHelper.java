package com.stepintoIt.firstapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import com.stepintoIt.firstapp.model.Product;

import java.util.ArrayList;

public class DbHelper {

    private static DbHelper instance;
    private static SQLiteDatabase db;

    private DbHelper(){}

    public static DbHelper getInstance(Context context){
        if (instance == null){
            instance = new DbHelper();
            db = new DbOpenHelper(context).getWritableDatabase();
        }
        return instance;
    }




    public void insetProducts(ArrayList<Product> productArrayList){
        for (Product product: productArrayList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(IDbConstants.Product.PRODUCT_ID , product.getProductId());
            contentValues.put(IDbConstants.Product.NAME, product.getName());
            contentValues.put(IDbConstants.Product.DESCRIPTION , product.getDescription());
            db.insert(IDbConstants.Product.TABLE_NAME, null, contentValues);
        }
    }


    public ArrayList<Product> getProducts(){
        ArrayList<Product> productArrayList = null;
        Cursor cursor = db.query(IDbConstants.Product.TABLE_NAME,null,
                null,null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

             productArrayList = new ArrayList<Product>();

            do {
                Product product = new Product();
                product.setProductId(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.PRODUCT_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(IDbConstants.Product.DESCRIPTION)));
                productArrayList.add(product);

            }
            while(cursor.moveToNext());

            cursor.close();
        }

    return productArrayList;

    }


}
