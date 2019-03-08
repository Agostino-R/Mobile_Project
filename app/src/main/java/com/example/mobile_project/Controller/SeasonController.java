package com.example.mobile_project.Controller;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.mobile_project.Model.AnimeInSeasList;
import com.example.mobile_project.Model.Api_Seas_Struct_Resp;
import com.example.mobile_project.View.MainMenuActivity;
import com.example.mobile_project.View.SeasonActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeasonController {
    private SeasonActivity view;

    public SeasonController(SeasonActivity seasonActivity) {
        this.view = seasonActivity;
    }

    Gson gson;
    Retrofit retrofit;
    MyAnimeListAPI restApi;

    public void onCreate() {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restApi = retrofit.create(MyAnimeListAPI.class);

    }

    public void loadSeasList(String param1, String param2)
    {
        Call<Api_Seas_Struct_Resp> call = restApi.getSeasListAnime("season", param1, param2);
        call.enqueue(new Callback<Api_Seas_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Seas_Struct_Resp> call, Response<Api_Seas_Struct_Resp> response) {
                if(response.isSuccessful()) {
                    Api_Seas_Struct_Resp api_Seas_Struct_Resp = response.body();
                    List<AnimeInSeasList> listAnimeInSeasList = api_Seas_Struct_Resp.getAnime();
                    view.showSeasList(listAnimeInSeasList);
                }
            }

            @Override
            public void onFailure(Call<Api_Seas_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }
}
