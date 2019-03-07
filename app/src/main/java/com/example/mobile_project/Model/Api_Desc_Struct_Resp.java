package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Api_Desc_Struct_Resp {

    @SerializedName("request_hash")
    private String request_hash;

    @SerializedName("request_cached")
    private boolean request_cached;

    @SerializedName("request_cache_expiry")
    private int request_cache_expiry;

    @SerializedName("url")
    private String url;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("episodes")
    private int episodes;

    @SerializedName("title")
    private String title;

    @SerializedName("status")
    private String status;

    @SerializedName("airing")
    private boolean airing;

    @SerializedName("score")
    private float score;

    @SerializedName("rank")
    private int rank;

    @SerializedName("popularity")
    private int popularity;

    @SerializedName("synopsis")
    private String synopsis;

    @SerializedName("background")
    private String  background;

    @SerializedName("genres")
    private List<AnimeGenres> genres;

    public String getRequest_hash() {
        return request_hash;
    }

    public boolean isRequest_cached() {
        return request_cached;
    }

    public int getRequest_cache_expiry() {
        return request_cache_expiry;
    }

    public String getUrl() {
        return url;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public boolean isAiring() {
        return airing;
    }

    public float getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getBackground() {
        return background;
    }

    public List<AnimeGenres> getGenres() {
        return genres;
    }
}
