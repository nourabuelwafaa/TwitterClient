package com.demo.twitterclient.followers;

import com.demo.twitterclient.MyLog;
import com.demo.twitterclient.R;
import com.demo.twitterclient.login.LoginContract;
import com.demo.twitterclient.repo.BaseClient;
import com.demo.twitterclient.repo.Followers;
import com.demo.twitterclient.repo.User;
import com.demo.twitterclient.repo.OnResponseHandler;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;

public class FollowersPresenterImpl implements FollowersContract.FollowersPresenter {

    private FollowersContract.FollowersView view;
    private static final String FOLLOWERS_URL = BaseClient.BASE_URL + "/followers/list.json?cursor=-1";

    FollowersPresenterImpl(FollowersContract.FollowersView view) {
        this.view = view;

    }

    @Override
    public void getFollowers() {
        String token = view.getPrefs().getString(LoginContract.TOKEN_PREFS, "");
        String tokenKey = view.getPrefs().getString(LoginContract.TOKEN_KEY_PREFS, "");
        BaseClient.serviceCall(new OAuth1AccessToken(token, tokenKey), new OAuthRequest(Verb.GET, FOLLOWERS_URL), new OnResponseHandler() {
            @Override
            public void onResponseHandler(boolean isSuccess, String response) {
                if (isSuccess) {

                    Followers followers = new Gson().fromJson(response, Followers.class);
                    MyLog.d(response);
                } else {
                    view.showMessage(R.string.Error);
                }
            }
        });
    }


}
