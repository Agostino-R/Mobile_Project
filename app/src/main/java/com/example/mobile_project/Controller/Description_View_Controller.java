package com.example.mobile_project.Controller;

import android.util.Log;

import com.example.mobile_project.Model.Api_Desc_Struct_Resp;
import com.example.mobile_project.View.AnimeDescActivity;
import com.example.mobile_project.View.ShowAnimeListActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Description_View_Controller{

    private AnimeDescActivity view;

        public Description_View_Controller(AnimeDescActivity animeDescActivity) {
            this.view = animeDescActivity;
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

    public void loadAnimeDescription(String param1, String param2)
    {
        Call<Api_Desc_Struct_Resp> call = restApi.getAnimeById(param1, param2);
        call.enqueue(new Callback<Api_Desc_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Desc_Struct_Resp> call, Response<Api_Desc_Struct_Resp> response) {
                if(response.isSuccessful()) {
                    Api_Desc_Struct_Resp api_Desc_Struct_Resp = response.body();
                    view.showAnimeDesc(api_Desc_Struct_Resp);
                }
            }

            @Override
            public void onFailure(Call<Api_Desc_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }
}
