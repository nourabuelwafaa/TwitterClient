package com.demo.twitterclient.repo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Followers {

    @SerializedName("users")
    private List<User> users = null;
    @SerializedName("next_cursor")
    private int nextCursor;
    @SerializedName("previous_cursor")
    private int previousCursor;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(int nextCursor) {
        this.nextCursor = nextCursor;
    }

    public int getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(int previousCursor) {
        this.previousCursor = previousCursor;
    }
}
