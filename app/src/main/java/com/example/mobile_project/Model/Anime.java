package com.example.mobile_project.Model;

import com.google.gson.annotations.SerializedName;

public class Anime
{
    @SerializedName("mal_id")
    private int id;

    @SerializedName("rank")
    private int rank;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("type")
    private String type;

    @SerializedName("episodes")
    private int episodes;

    @SerializedName("start_date")
    private String start_date;

    @SerializedName("end_date")
    private String end_date;

    @SerializedName("members")
    private int members;

    @SerializedName("score")
    private float score;

    public void setId(int id) {
        this.id = id;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setStart(String start) {
        this.start_date = start;
    }

    public void setEnd(String end) {
        this.end_date = end;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getType() {
        return type;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getStart() {
        return start_date;
    }

    public String getEnd() {
        return end_date;
    }

    public int getMembers() {
        return members;
    }

    public float getScore() {
        return score;
    }
}
