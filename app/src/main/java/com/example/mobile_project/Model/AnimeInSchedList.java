package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

public class AnimeInSchedList {

    private String day;

    @SerializedName("mal_id")
    private int mal_id;

    @SerializedName("title")
    private String title;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("score")
    private float score;

    public int getMal_id() {
        return mal_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public float getScore() {
        return score;
    }
}
