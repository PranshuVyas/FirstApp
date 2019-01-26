package com.stepintoIt.firstapp.data;

public interface IDbConstants {

    String DB_NAME = "stepintodb";
    int DB_VERSION = 1;
    String COMMA = ", ";
    interface Product{
        String  TABLE_NAME = "product";
        String PRODUCT_ID = "productID";
        String NAME = "productName";
        String DESCRIPTION = "description";


        String CREATE = " create table " + TABLE_NAME + " ( "
        + PRODUCT_ID + " text " + COMMA
        + NAME + " TEXT " + COMMA
        +DESCRIPTION + " TEXT "   + " ) ";

    }
}
