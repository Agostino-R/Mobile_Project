package com.example.mobile_project;

import com.example.mobile_project.Model.Api_Top_Struct_Resp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyAnimeListAPI
{
    @GET("{param_a}/{param_b}")
    Call<Api_Top_Struct_Resp> getListAnime(@Path("param_a") String pa, @Path("param_b") String pb);
}
