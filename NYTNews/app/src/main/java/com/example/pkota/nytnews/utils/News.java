package com.example.pkota.nytnews.utils;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pkota on 07-09-2016.
 */
public class News {

    @SerializedName("title")
    private String title;

    @SerializedName("published_date")
    private Date date;

    @SerializedName("url")
    private String url;

    @SerializedName("section")
    private String category;

    public News(String title, Date date, String url, String category) {
        this.title = title;
        this.date = date;
        this.url = url;
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
