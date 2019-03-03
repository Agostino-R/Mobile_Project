package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Api_Top_Struct_Resp
{
    @SerializedName("request_hash")
    private String request_hash;

    @SerializedName("request_cached")
    private Boolean request_cached;

    @SerializedName("request_cache_expiry")
    private int request_cache_expiry;

    @SerializedName("top")
    private List<AnimeInTopList> top;

    public String getRequest_hash() {
        return request_hash;
    }

    public Boolean getRequest_cached() {
        return request_cached;
    }

    public int getRequest_cache_expiry() {
        return request_cache_expiry;
    }

    public List<AnimeInTopList> getResults() {
        return top;
    }
}
