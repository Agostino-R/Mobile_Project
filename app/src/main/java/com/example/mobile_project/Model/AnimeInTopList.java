package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

public class AnimeInTopList
{
    @SerializedName("mal_id") //nom du truc dans ton fichier json (Icon Name et Description)
    private int mal_id; //ici tu l'appelle comme tu veux osef

    @SerializedName("rank")
    private int rank;

    @SerializedName("title")
    private String title;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("score")
    private float score;


    public int getId() {
        return mal_id;
    }

    public int getRank() {
        return rank;
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
