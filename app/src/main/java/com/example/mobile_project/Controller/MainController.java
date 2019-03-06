package com.example.mobile_project.Controller;

import android.util.Log;

import com.example.mobile_project.Model.AnimeInSchedList;
import com.example.mobile_project.Model.AnimeInSeasList;
import com.example.mobile_project.Model.AnimeInUpcomingList;
import com.example.mobile_project.Model.Api_Sched_Struct_Resp;
import com.example.mobile_project.Model.Api_Seas_Struct_Resp;
import com.example.mobile_project.Model.Api_Top_Struct_Resp;
import com.example.mobile_project.Model.Api_Upcoming_Struct_Resp;
import com.example.mobile_project.View.ShowAnimeListActivity;
import com.example.mobile_project.Model.AnimeInTopList;
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

    private ShowAnimeListActivity view;

    public MainController(ShowAnimeListActivity showAnimeListActivity) {
        this.view = showAnimeListActivity;
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

    public void loadTopList(String param1, String param2, String param3)
    {
        Call<Api_Top_Struct_Resp> call = restApi.getTopListAnime(param1, param2, param3);
        call.enqueue(new Callback<Api_Top_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Top_Struct_Resp> call, Response<Api_Top_Struct_Resp> response) {
                if(response.isSuccessful()) {
                    Api_Top_Struct_Resp api_Top_Struct_Resp = response.body();
                    List<AnimeInTopList> listAnimeInTopList = api_Top_Struct_Resp.getResults();
                    view.setList(listAnimeInTopList);
                    view.showTopList(listAnimeInTopList);
                }
            }

            @Override
            public void onFailure(Call<Api_Top_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }

    public void getFollowingTopList(String param1, String param2, String param3)
    {
        Call<Api_Top_Struct_Resp> call = restApi.getTopListAnime(param1, param2, param3);
        call.enqueue(new Callback<Api_Top_Struct_Resp>() {
            @Override
            public void onResponse(Call<Api_Top_Struct_Resp> call, Response<Api_Top_Struct_Resp> response) {
                if(response.isSuccessful()) {
                    Api_Top_Struct_Resp api_Top_Struct_Resp = response.body();
                    List<AnimeInTopList> listAnimeInTopList = api_Top_Struct_Resp.getResults();
                    view.addToList(listAnimeInTopList);
                }
            }

            @Override
            public void onFailure(Call<Api_Top_Struct_Resp> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
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
