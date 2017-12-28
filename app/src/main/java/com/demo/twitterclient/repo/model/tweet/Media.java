package com.demo.twitterclient.repo.model.tweet;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("media_url")
    private String mediaUrl;
    @SerializedName("type")
    private String type;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
