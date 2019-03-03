package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Api_Top_Struct_Resp
{
    private String request_hash;
    private Boolean request_cached;
    private int request_cache_expiry;

    @SerializedName("top")
    private List<AnimeInTopList> results;

    public void setRequest_hash(String request_hash) {
        this.request_hash = request_hash;
    }

    public void setRequest_cached(Boolean request_cached) {
        this.request_cached = request_cached;
    }

    public void setRequest_cache_expiry(int request_cache_expiry) {
        this.request_cache_expiry = request_cache_expiry;
    }

    public void setTest(List<AnimeInTopList> results) {
        this.results = results;
    }

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
        return results;
    }
}
