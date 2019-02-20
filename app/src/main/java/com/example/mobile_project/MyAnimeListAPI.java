package com.example.mobile_project;

import com.example.mobile_project.Model.Api_Struct_Resp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyAnimeListAPI
{
    @GET("top/")
    Call<Api_Struct_Resp> getListAnime();
}
