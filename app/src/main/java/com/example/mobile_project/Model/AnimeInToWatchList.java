package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

public class AnimeInToWatchList {
    @SerializedName("request_hash")
    private String request_hash;

    @SerializedName("request_cached")
    private boolean request_cached;

    @SerializedName("request_cache_expiry")
    private int request_cache_expiry;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("title")
    private String title;

    @SerializedName("score")
    private float score;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public String getImage_url() {
        return image_url;
    }

    public String getTitle() {
        return title;
    }

    public float getScore() {
        return score;
    }
}
