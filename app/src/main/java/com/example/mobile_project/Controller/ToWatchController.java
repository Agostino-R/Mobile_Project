package com.example.mobile_project.Controller;

import android.util.Log;

import com.example.mobile_project.Model.AnimeInSeasList;
import com.example.mobile_project.Model.AnimeInToWatchList;
import com.example.mobile_project.View.AnimeDescActivity;
import com.example.mobile_project.View.SeasonActivity;
import com.example.mobile_project.View.ToWatchActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToWatchController {
    private ToWatchActivity view;

    public ToWatchController(ToWatchActivity toWatchActivity) {
        this.view = toWatchActivity;
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

    public void loadToWatchAnime(String param1, final String param2)
    {
        Call<AnimeInToWatchList> call = restApi.getToWatchAnimeById(param1, param2);
        call.enqueue(new Callback<AnimeInToWatchList>() {
            @Override
            public void onResponse(Call<AnimeInToWatchList> call, Response<AnimeInToWatchList> response) {
                if(response.isSuccessful()) {
                    AnimeInToWatchList animeInToWatchList = response.body();
                    animeInToWatchList.setId(Integer.parseInt(param2));
                    view.addToList(animeInToWatchList);
                    view.showToWatchList();
                }
            }

            @Override
            public void onFailure(Call<AnimeInToWatchList> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }
}

