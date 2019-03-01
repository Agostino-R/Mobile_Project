package com.example.mobile_project.Model;

import java.util.List;

public class Api_Struct_Resp
{
    String request_hash;
    Boolean request_cached;
    int request_cache_expiry;
    List<Anime> results;


    public void setRequest_hash(String request_hash) {
        this.request_hash = request_hash;
    }

    public void setRequest_cached(Boolean request_cached) {
        this.request_cached = request_cached;
    }

    public void setRequest_cache_expiry(int request_cache_expiry) {
        this.request_cache_expiry = request_cache_expiry;
    }

    public void setTest(List<Anime> results) {
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

    public List<Anime> getResults() {
        return results;
    }
}
