package com.example.pkota.nytnews.utils;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pkota on 07-09-2016.
 */
public class News {

    @SerializedName("title")
    private String title;

    @SerializedName("published_date")
    private String date;

    @SerializedName("url")
    private String url;

    @SerializedName("thumbnail_standard")
    private String thumbnail;

    @SerializedName("section")
    private String category;

    @SerializedName("results")
    private List<News> results;


    public News(String title, String date, String url, String thumbnail, String category) {
        this.title = title;
        this.date = date;
        this.url = url;
        this.thumbnail = thumbnail;
        this.category = category;
    }

    public List<News> getResults() {
        return results;
    }

    public void setResults(List<News> results) {
        this.results = results;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
