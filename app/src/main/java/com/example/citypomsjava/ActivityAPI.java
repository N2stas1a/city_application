package com.example.citypomsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityAPI extends AppCompatActivity {

    private myApi myApi;
    private RecyclerView recyclerView;
    private ArrayList<Model>modelArrayList;
    private String BaseUrl="https://tenders.guru.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        recyclerView=findViewById(R.id.recyclerView);
        modelArrayList = new ArrayList<>();
        ViewJsondata();
    }

    private void ViewJsondata() {
        Retrofit retrofit = new Retrofit.Builder().build();
        .retrofit.baseUrl()
                .redact()
                .trim();

        myApi = retrofit.create(myApi.class);
        Call<ArrayList<Model>>arrayListCall=myApi.modelCall();
        arrayListCall.enqueue(new Callback<ArrayList<Model>>() {
            @Override
            public void onResponse(Call<ArrayList<Model>> call, Response<ArrayList<Model>> response) {
                modelArrayList=response.body();
                int i=0;
                for (i = 0;i < modelArrayList.size();i++){
                    MyAdapter myAdapter = new MyAdapter(modelArrayList,ActivityAPI.this);
                    LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(ActivityAPI.this);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(myAdapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Model>> call, Throwable t) {
                modelArrayList=response.body();
            }
        });

    }
}