package com.example.mobile_project.Controller;

import android.util.Log;

import com.example.mobile_project.Model.AnimeInSchedList;
import com.example.mobile_project.Model.Api_Sched_Struct_Resp;
import com.example.mobile_project.View.SchedActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchedController {

    private SchedActivity view;

    public SchedController(SchedActivity schedActivity) {
        this.view = schedActivity;
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

    public void loadSchedList(String param1, String param2)
    {
        Call<Api_Sched_Struct_Resp> call = restApi.getSchedListAnime(param1, param2);
        call.enqueue(new Callback<Api_Sched_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Sched_Struct_Resp> call, Response<Api_Sched_Struct_Resp> response) {
                if(response.isSuccessful()) {
                    Api_Sched_Struct_Resp api_Sched_Struct_Resp = response.body();
                    List<AnimeInSchedList> listAnimeInSchedList = api_Sched_Struct_Resp.getAnimeL();
                    view.showSchedList(listAnimeInSchedList);
                }
            }

            @Override
            public void onFailure(Call<Api_Sched_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }
}
