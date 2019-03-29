package com.example.mobile_project.Controller;

import android.util.Log;

import com.example.mobile_project.Model.AnimeInSearchList;
import com.example.mobile_project.Model.Api_Search_Struct_Resp;
import com.example.mobile_project.MyAnimeListAPI;
import com.example.mobile_project.View.SearchActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchController {
    private SearchActivity view;

    public SearchController(SearchActivity searchActivity) {

        this.view = searchActivity;
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

    public void loadSearchResults(String param1, String param2, String param3) {
        //Log.i("S Controller", "##########" +retrofit.baseUrl()+ param1 + "/" + param2 + param3);
        Call<Api_Search_Struct_Resp> call = restApi.getSearchListAnime(param1+"/"+param2+param3);
        //Log.i("S Call", "##########" + call);
        call.enqueue(new Callback<Api_Search_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Search_Struct_Resp> call, Response<Api_Search_Struct_Resp> response) {
                //Log.i("S Controller", "##########" + response.code());
                if(response.isSuccessful()) {
                    Api_Search_Struct_Resp api_Search_Struct_Resp = response.body();
                    //Log.i("S Controller", "##########" + api_Search_Struct_Resp);
                    List<AnimeInSearchList> listAnimeInSearchRes= api_Search_Struct_Resp.getResults();
                    view.setList(listAnimeInSearchRes);
                    view.showSearchResults(listAnimeInSearchRes);
                }
            }

            @Override
            public void onFailure(Call<Api_Search_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }


    public void getFollowingSearchRes(String param1, String param2, String param3)
    {
        Call<Api_Search_Struct_Resp> call = restApi.getSearchListAnime(param1+"/"+param2+param3);
        call.enqueue(new Callback<Api_Search_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Search_Struct_Resp> call, Response<Api_Search_Struct_Resp> response) {
                if(response.isSuccessful()) {
                    Api_Search_Struct_Resp api_Search_Struct_Resp = response.body();
                    List<AnimeInSearchList> listAnimeInSearchRes= api_Search_Struct_Resp.getResults();
                    view.addToList(listAnimeInSearchRes);
                }
            }

            @Override
            public void onFailure(Call<Api_Search_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }

}
