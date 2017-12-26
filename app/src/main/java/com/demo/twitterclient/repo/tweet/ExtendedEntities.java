package com.demo.twitterclient.repo.tweet;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class ExtendedEntities {
    @SerializedName("media")
    private List<Media> media;

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }
}
