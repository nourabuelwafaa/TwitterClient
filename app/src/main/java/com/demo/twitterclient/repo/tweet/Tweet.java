package com.demo.twitterclient.repo.tweet;

import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("text")
    private String text;
    @SerializedName("retweet_count")
    private int retweetCount;
    @SerializedName("favorite_count")
    private int favoriteCount;
    @SerializedName("extended_entities")
    private ExtendedEntities extendedEntities;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public ExtendedEntities getExtendedEntities() {
        return extendedEntities;
    }

    public void setExtendedEntities(ExtendedEntities extendedEntities) {
        this.extendedEntities = extendedEntities;
    }
}
