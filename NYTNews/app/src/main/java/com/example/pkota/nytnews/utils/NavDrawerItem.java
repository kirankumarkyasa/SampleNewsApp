package com.example.pkota.nytnews.utils;

/**
 * Created by pkota on 08-09-2016.
 */
public class NavDrawerItem {

    private String title;
    private int icon;


    public NavDrawerItem(){}

    public NavDrawerItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public NavDrawerItem(String title, int icon, boolean isCounterVisible, String count){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }


    public void setTitle(String title){
        this.title = title;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

}