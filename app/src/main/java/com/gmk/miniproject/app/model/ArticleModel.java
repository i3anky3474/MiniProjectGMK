package com.gmk.miniproject.app.model;

/**
 * Created by admin on 1/23/16 AD.
 */
public class ArticleModel {

    private String article_id;
    private String date;
    private String title;
    private String short_description;
    private String image;

    //Get Detail
    private String content;
    private String type;
    private String lat;
    private String lon;

    public ArticleModel() {
        //init
    }

    public ArticleModel(String article_id, String date, String title, String short_description, String image) {
        this.article_id = article_id;
        this.date = date;
        this.title = title;
        this.short_description = short_description;
        this.image = image;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
