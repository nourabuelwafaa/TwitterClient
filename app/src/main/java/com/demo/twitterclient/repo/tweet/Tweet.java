package com.demo.twitterclient.repo.tweet;

import com.demo.twitterclient.repo.User;
import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("text")
    private String text;
    @SerializedName("user")
    private User user;
    @SerializedName("retweet_count")
    private int retweetCount;
    @SerializedName("favorite_count")
    private int favoriteCount;
    @SerializedName("extended_entities")
    private ExtendedEntities extendedEntities;
    @SerializedName("retweeted_status")
    private RetweetedStatus retweetedStatus;

    public RetweetedStatus getRetweetedStatus() {
        return retweetedStatus;
    }

    public void setRetweetedStatus(RetweetedStatus retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
