package com.demo.twitterclient.repo.model;
import com.demo.twitterclient.repo.model.tweet.Tweet;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    public static final String USER_INTENT_KEY = "userIntentKey";

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("followers_count")
    private int followersCount;
    @SerializedName("screen_name")
    private String screenName;
    @SerializedName("friends_count")
    private int friendsCount;
    @SerializedName("profile_banner_url")
    private String profileBackgroundImageUrl;
    @SerializedName("profile_image_url")
    private String profileImageUrl;

    private List<Tweet> tweets;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
        this.profileBackgroundImageUrl = profileBackgroundImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public String getScreenName() {
        return "@" + screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
