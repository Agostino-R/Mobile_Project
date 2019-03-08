package com.example.mobile_project.Controller;

import android.util.Log;

import com.example.mobile_project.Model.AnimeInUpcomingList;
import com.example.mobile_project.Model.Api_Upcoming_Struct_Resp;
import com.example.mobile_project.View.UpcomingActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpcomingController {
    private UpcomingActivity view;

    public UpcomingController(UpcomingActivity upcomingActivity) {
        this.view = upcomingActivity;
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

    public void loadUpcomList(String param1, String param2)
    {
        Call<Api_Upcoming_Struct_Resp> call = restApi.getUpcomListAnime(param1, param2);
        call.enqueue(new Callback<Api_Upcoming_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Upcoming_Struct_Resp> call, Response<Api_Upcoming_Struct_Resp> response) {
                if(response.isSuccessful()) {
                    Api_Upcoming_Struct_Resp api_Struct_Resp = response.body();
                    List<AnimeInUpcomingList> listAnimeInUpcomingList = api_Struct_Resp.getAnime();
                    view.showUpcomList(listAnimeInUpcomingList);
                }
            }

            @Override
            public void onFailure(Call<Api_Upcoming_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }
}
