package com.stepintoIt.firstapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.stepintoIt.firstapp.R;
import com.stepintoIt.firstapp.api.ApiClient;
import com.stepintoIt.firstapp.api.ApiInterface;
import com.stepintoIt.firstapp.model.Get;
import com.stepintoIt.firstapp.model.MyResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;
import timber.log.Timber;

public class JsonRequestActivity extends AppCompatActivity {

    @BindView(R.id.txt_result)
    TextView txtResult;
    ApiInterface apiInterface;
    //RecyclerView rvUser;
    //public static final String GET_URL_ONE = "https://reqres.in/api/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_request);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @OnClick(R.id.btn_userid)
    public void getUserId(){
        //getJsonArray("userid");

        //getUser();
    }
    @OnClick(R.id.btn_id)
       public void getId(){
        getJsonArray("id");
    }
    @OnClick(R.id.btn_title)
    public void getTitles(){
        //getJsonArray("title");
    }
    @OnClick(R.id.btn_body)
    public void getBody(){
        //getJsonArray("body");
    }


    private void getJsonArray(final String type){
        Call<ArrayList<Get>> call = apiInterface.getJsonArray();
        call.enqueue(new Callback<ArrayList<Get>>() {
            @Override
            public void onResponse(Call<ArrayList<Get>> call, Response<ArrayList<Get>> response) {
                ArrayList<Get> userList = response.body();

                if (type == "userid"){
                    for (Get get : userList){
                        txtResult.append("UserId : " + get.getUserId()+ " ");
                    }
                }
               else if (type == "id"){
                    for (Get get : userList){
                        txtResult.append("Id : " + get.getIds()+ " ");
                    }
                }
                else if (type == "title"){
                    for (Get get : userList){
                        txtResult.append("Id : " + get.getTitle()+ " ");
                    }
                }
                else if (type == "body"){
                    for (Get get : userList){
                        txtResult.append("Id : " + get.getBody() + " ");
                    }
                }
                else{
                    txtResult.setText("Fatal Error");
                }

            }
            @Override
            public void onFailure(Call<ArrayList<Get>> call, Throwable t) {
                txtResult.setText("Error");
            }
        });


    }

//    private void getUser(){
//        Call<MyResponse> call = apiInterface.getUser();
//        call.enqueue(new Callback<MyResponse>() {
//            @Override
//            public void onResponse(Call<MyResponse> call, retrofit2.Response<MyResponse> response) {
//                MyResponse myResponse = response.body();
//
//                txtResult.setText("Id : " + myResponse.getUser().id);
//            }
//
//            @Override
//            public void onFailure(Call<MyResponse> call, Throwable t) {
//                txtResult.setText("error");
//            }
//        });
//
//
//
//    }

}







