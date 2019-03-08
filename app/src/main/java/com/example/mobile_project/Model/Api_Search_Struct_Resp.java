package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Api_Search_Struct_Resp {
    @SerializedName("request_hash")
    private String request_hash;

    @SerializedName("request_cached")
    private boolean request_cached;

    @SerializedName("request_cache_expiry")
    private int request_cache_expiry;

    @SerializedName("results")
    private List<AnimeInSearchList> results;

    @SerializedName("last_page")
    private int last_page;

    public int getLast_page() {
        return last_page;
    }

    public String getRequest_hash() {
        return request_hash;
    }

    public boolean isRequest_cached() {
        return request_cached;
    }

    public int getRequest_cache_expiry() {
        return request_cache_expiry;
    }

    public List<AnimeInSearchList> getResults() {
        return results;
    }
}
