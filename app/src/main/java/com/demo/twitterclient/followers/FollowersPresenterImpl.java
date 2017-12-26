package com.demo.twitterclient.followers;

import com.demo.twitterclient.MyLog;
import com.demo.twitterclient.R;
import com.demo.twitterclient.login.LoginContract;
import com.demo.twitterclient.repo.BaseClient;
import com.demo.twitterclient.repo.Followers;
import com.demo.twitterclient.repo.User;
import com.demo.twitterclient.repo.OnResponseHandler;
import com.demo.twitterclient.repo.tweet.Tweet;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FollowersPresenterImpl implements FollowersContract.FollowersPresenter {

    private FollowersContract.FollowersView view;
    private static final String FOLLOWERS_URL = BaseClient.BASE_URL + "/followers/list.json?cursor=-1";
    private static final String TWEETS_URL = BaseClient.BASE_URL + "/statuses/user_timeline.json?user_id=";
    private Followers followers;
    private OAuth1AccessToken oAuth1AccessToken;

    FollowersPresenterImpl(FollowersContract.FollowersView view) {
        this.view = view;
        String token = view.getPrefs().getString(LoginContract.TOKEN_PREFS, "");
        String tokenKey = view.getPrefs().getString(LoginContract.TOKEN_KEY_PREFS, "");
        oAuth1AccessToken = new OAuth1AccessToken(token, tokenKey);

    }

    @Override
    public void getFollowers() {

        BaseClient.serviceCall(oAuth1AccessToken, new OAuthRequest(Verb.GET, FOLLOWERS_URL), new OnResponseHandler() {
            @Override
            public void onResponseHandler(boolean isSuccess, String response) {
                if (isSuccess) {
                    followers = new Gson().fromJson(response, Followers.class);
                    view.onFollowersResponse(followers.getUsers());
                    MyLog.d(response);
                } else {
                    view.showMessage(R.string.Error);
                }
            }
        });
    }

    @Override
    public void onUserClicked(int position) {
        User user = followers.getUsers().get(position);

        BaseClient.serviceCall(oAuth1AccessToken, new OAuthRequest(Verb.GET, getTweetsUrl(user.getId())), new OnResponseHandler() {
            @Override
            public void onResponseHandler(boolean isSuccess, String response) {
                if (isSuccess) {
                    Type type = new TypeToken<List<Tweet>>() {
                    }.getType();

                    List<Tweet> tweets = new Gson().fromJson(response, type);
                    MyLog.d(response);
                } else {
                    view.showMessage(R.string.Error);
                }
            }
        });


    }

    private String getTweetsUrl(int userId) {
        return TWEETS_URL + userId + "&count=10&exclude_replies=true";
    }

}
