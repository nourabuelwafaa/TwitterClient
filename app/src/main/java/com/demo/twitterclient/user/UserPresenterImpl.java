package com.demo.twitterclient.user;

import android.widget.ExpandableListView;

import com.demo.twitterclient.MyLog;
import com.demo.twitterclient.R;
import com.demo.twitterclient.followers.FollowersContract;
import com.demo.twitterclient.login.LoginContract;
import com.demo.twitterclient.repo.BaseClient;
import com.demo.twitterclient.repo.Followers;
import com.demo.twitterclient.repo.OnResponseHandler;
import com.demo.twitterclient.repo.User;
import com.demo.twitterclient.repo.tweet.Tweet;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class UserPresenterImpl implements UserInfoContract.UserPresenter {

    private UserInfoContract.UserInfoView view;
    private static final String TWEETS_URL = BaseClient.BASE_URL + "/statuses/user_timeline.json?user_id=";
    private OAuth1AccessToken oAuth1AccessToken;


    UserPresenterImpl(UserInfoContract.UserInfoView view) {
        this.view = view;
        String token = view.getPrefs().getString(LoginContract.TOKEN_PREFS, "");
        String tokenKey = view.getPrefs().getString(LoginContract.TOKEN_KEY_PREFS, "");
        oAuth1AccessToken = new OAuth1AccessToken(token, tokenKey);

    }

    @Override
    public void onHandleUserInfo(String userJson) {
        User user = new Gson().fromJson(userJson, User.class);
        view.showUserDetails(user);
        getTweets(user.getId());
    }

    private void getTweets(long userId) {

        if (!view.isConnected()) {
            view.showSnackBar(R.string.notConnected, R.id.mainView);
            view.hideProgress();
        }

        BaseClient.serviceCall(oAuth1AccessToken, new OAuthRequest(Verb.GET, getTweetsUrl(userId)), new OnResponseHandler() {
            @Override
            public void onResponseHandler(boolean isSuccess, String response) {
                if (isSuccess && !response.contains("Not authorized")) {
                    Type type = new TypeToken<List<Tweet>>() {
                    }.getType();
                    List<Tweet> tweets = new Gson().fromJson(response, type);
                    view.showTweets(tweets);
                    MyLog.d(response);
                } else {
                    view.showMessage(R.string.Error);
                    view.hideProgress();
                }
            }
        });
    }

    private String getTweetsUrl(long userId) {
        return TWEETS_URL + userId + "&count=10";
//        &exclude_replies=true
    }
}
