package com.demo.twitterclient.repo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Followers {

    @SerializedName("users")
    private List<User> users = new ArrayList<>();
    @SerializedName("next_cursor")
    private long nextCursor;
    @SerializedName("previous_cursor")
    private long previousCursor;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public long getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(long previousCursor) {
        this.previousCursor = previousCursor;
    }
}
