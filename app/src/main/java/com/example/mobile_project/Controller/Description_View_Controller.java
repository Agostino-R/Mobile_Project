package com.example.mobile_project.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mobile_project.Model.Api_Desc_Struct_Resp;
import com.example.mobile_project.MyAnimeListAPI;
import com.example.mobile_project.View.AnimeDescActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Description_View_Controller{

    private AnimeDescActivity view;
    private String jsonList;
    private List<Integer> toWatchList;
    private SharedPreferences mSharedPreferences;
    private Boolean isInWatchList;

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
            mSharedPreferences = view.getSharedPreferences("userList", Context.MODE_PRIVATE);
            jsonList = mSharedPreferences.getString("ID_List", null);


            ArrayList<Double> malDoubleList;
            if(toWatchList == null)
            {
                toWatchList = new ArrayList<>();
            }

            malDoubleList = gson.fromJson(jsonList, ArrayList.class);
            if(malDoubleList!=null) {
                for (double d : malDoubleList) {
                    toWatchList.add((int) d);
                }
            }

            int temp_id = view.getMal_id();

            if(toWatchList.contains(temp_id))
                isInWatchList = true;
            else
                isInWatchList = false;
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

    public void addItemIntoList(int mal_id)
    {
        //On doit passer par une conversion car avec SharedPreferences on recupere une liste de double
        mSharedPreferences = view.getSharedPreferences("userList", Context.MODE_PRIVATE);
        ArrayList<Double> malDoubleList;
        if(toWatchList == null)
        {
            toWatchList = new ArrayList<>();
        }

        malDoubleList = gson.fromJson(jsonList, ArrayList.class);
        if(malDoubleList!=null) {
            toWatchList.clear();
            for (double d : malDoubleList) {
                toWatchList.add((int) d);
            }
        }
        if(!toWatchList.contains(mal_id)) {
            toWatchList.add(mal_id);
            isInWatchList = true;
            view.setButtonBehavior(isInWatchList);
            jsonList = gson.toJson(toWatchList);
            mSharedPreferences
                    .edit()
                    .putString("ID_List", jsonList)
                    .apply();
        }
    }

    public void removeItemFromList(int mal_id)
    {
        mSharedPreferences = view.getSharedPreferences("userList", Context.MODE_PRIVATE);
        ArrayList<Double> malDoubleList;

        malDoubleList = gson.fromJson(jsonList, ArrayList.class);
        if(malDoubleList!=null) {
            toWatchList.clear();
            for (double d : malDoubleList) {
                toWatchList.add((int) d);
            }
        }

        toWatchList.remove(new Integer(mal_id));
        isInWatchList = false;
        view.setButtonBehavior(isInWatchList);
        jsonList = gson.toJson(toWatchList);

        mSharedPreferences
                .edit()
                .putString("ID_List", jsonList)
                .apply();
    }

    public Boolean getIsInWatchList() {
        return isInWatchList;
    }
}
