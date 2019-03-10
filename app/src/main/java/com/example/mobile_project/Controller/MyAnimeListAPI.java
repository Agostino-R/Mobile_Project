package com.example.mobile_project.Controller;

import com.example.mobile_project.Model.Api_Desc_Struct_Resp;
import com.example.mobile_project.Model.Api_Sched_Struct_Resp;
import com.example.mobile_project.Model.Api_Search_Struct_Resp;
import com.example.mobile_project.Model.Api_Seas_Struct_Resp;
import com.example.mobile_project.Model.Api_Top_Struct_Resp;
import com.example.mobile_project.Model.Api_Upcoming_Struct_Resp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface MyAnimeListAPI
{
    @GET("{param_a}/{param_b}/{param_c}")
    Call<Api_Top_Struct_Resp> getTopListAnime(@Path("param_a") String pa, @Path("param_b") String pb, @Path("param_c") String pc);

    @GET("{param_a}/{param_b}")
    Call<Api_Upcoming_Struct_Resp> getUpcomListAnime(@Path("param_a") String pa, @Path("param_b") String pb);

    @GET("{param_a}/{param_b}/{param_c}")
    Call<Api_Seas_Struct_Resp> getSeasListAnime(@Path("param_a") String pa, @Path("param_b") String pb, @Path("param_c") String pc);

    @GET("{param_a}/{param_b}")
    Call<Api_Sched_Struct_Resp> getSchedListAnime(@Path("param_a") String pa, @Path("param_b") String pb);

    @GET("{param_a}/{param_b}")
    Call<Api_Desc_Struct_Resp> getAnimeById(@Path("param_a") String pa, @Path("param_b") String pb);

    @GET()
    Call<Api_Search_Struct_Resp> getSearchListAnime(@Url String theUrl);

}
