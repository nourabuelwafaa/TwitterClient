package com.demo.twitterclient.repo.model.tweet;

import com.demo.twitterclient.repo.model.User;
import com.google.gson.annotations.SerializedName;

public class RetweetedStatus {
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
