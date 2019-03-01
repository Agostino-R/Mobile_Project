package com.example.mobile_project.Model;

public class Anime
{
    private int id;
    private int rank;
    private String title;
    private String url;
    private String image_url;
    private String type;
    private int episodes;
    private String start;
    private String end;
    private int members;
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
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
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
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getMembers() {
        return members;
    }

    public float getScore() {
        return score;
    }
}
