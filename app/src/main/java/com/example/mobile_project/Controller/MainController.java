package com.example.mobile_project.Controller;

import android.util.Log;

import com.example.mobile_project.View.MainActivity;
import com.example.mobile_project.Model.Anime;
import com.example.mobile_project.Model.Api_Struct_Resp;
import com.example.mobile_project.MyAnimeListAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController
{

    private MainActivity view;

    public MainController(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void onCreate() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/top/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MyAnimeListAPI restApi = retrofit.create(MyAnimeListAPI.class);


        Call<Api_Struct_Resp> call = restApi.getListAnime();
        call.enqueue(new Callback<Api_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Struct_Resp> call, Response<Api_Struct_Resp> response) {
                Api_Struct_Resp api_Struct_Resp = response.body();
                List<Anime> listAnime = api_Struct_Resp.getResults();
                view.showList(listAnime);
            }

            @Override
            public void onFailure(Call<Api_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });

    }
}
