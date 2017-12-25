package com.demo.twitterclient.repo;

public interface OnResponseHandler {

    void onResponseHandler(boolean isSuccess, String response);
}
