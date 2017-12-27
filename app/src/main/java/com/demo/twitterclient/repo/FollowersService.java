package com.demo.twitterclient.repo;

import com.demo.twitterclient.MyLog;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;

import java.util.List;

public class FollowersService {
    private static final String FOLLOWERS_URL = BaseClient.BASE_URL + "/followers/list.json?cursor=-1";
    private static final FollowersService instance = new FollowersService();
    private Followers followers;

    public static FollowersService getInstance() {
        return instance;
    }

    private FollowersService() {
    }


    public void getFollowers(String token, String tokenKey, boolean forceRefresh, final FollowersCallback callback) {


        if (!forceRefresh && followers != null) {
            callback.setFollowers(true, followers);
            return;
        }

        OAuth1AccessToken oAuth1AccessToken = new OAuth1AccessToken(token, tokenKey);
        BaseClient.serviceCall(oAuth1AccessToken, new OAuthRequest(Verb.GET, FOLLOWERS_URL), new OnResponseHandler() {
            @Override
            public void onResponseHandler(boolean isSuccess, String response) {
                if (isSuccess) {
                    followers = new Gson().fromJson(response, Followers.class);
                    callback.setFollowers(true, followers);
                    MyLog.d(response);
                } else {
                    callback.setFollowers(false, followers);
                }
            }
        });
    }

    public interface FollowersCallback {

        void setFollowers(boolean isSuccess, Followers followers);
    }
}
