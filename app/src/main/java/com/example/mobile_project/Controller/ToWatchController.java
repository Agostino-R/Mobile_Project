package com.example.mobile_project.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mobile_project.Model.AnimeInToWatchList;
import com.example.mobile_project.MyAnimeListAPI;
import com.example.mobile_project.View.ToWatchActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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

    private Gson gson;
    private Retrofit retrofit;
    private MyAnimeListAPI restApi;
    private SharedPreferences mSharedPreferences;
    private String jsonList;

    public void onCreate() {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restApi = retrofit.create(MyAnimeListAPI.class);

        mSharedPreferences = view.getSharedPreferences("userList", Context.MODE_PRIVATE);
        jsonList = mSharedPreferences.getString("ID_List", null);

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

    public List<Integer> loadlist(String jsonInString)
    {
        ArrayList<Integer> malIdList;
        ArrayList<Double> malDoubleList;
        malIdList = new ArrayList<Integer>();


        malDoubleList = gson.fromJson(jsonInString, ArrayList.class);
        if(malDoubleList!=null) {
            for (double d : malDoubleList) {
                malIdList.add((int) d);
            }
            Log.i("##################", "" + malIdList);
        }

        return malIdList;
    }

    public void clearCache()
    {
        String jsonList = "";
        mSharedPreferences
                .edit()
                .putString("ID_List", jsonList)
                .apply();
    }
}

