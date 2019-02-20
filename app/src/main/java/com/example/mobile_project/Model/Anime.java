package com.example.mobile_project.Model;

public class Anime
{
    private int id;
    private String url;
    private String title;
    private Date start;
    private Date end;

    public Anime(int id, String url, String title, Date start, Date end) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
