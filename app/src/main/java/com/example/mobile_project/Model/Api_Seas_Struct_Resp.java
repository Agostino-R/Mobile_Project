package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Api_Seas_Struct_Resp {
    @SerializedName("request_hash")
    private String request_hash;

    @SerializedName("request_cached")
    private boolean request_cached;

    @SerializedName("request_cache_expiry")
    private int request_cache_expiry;

    @SerializedName("anime")
    private List<AnimeInSeasList> anime;

    public String getRequest_hash() {
        return request_hash;
    }

    public boolean isRequest_cached() {
        return request_cached;
    }

    public int getRequest_cache_expiry() {
        return request_cache_expiry;
    }

    public List<AnimeInSeasList> getAnime() {
        return anime;
    }
}
