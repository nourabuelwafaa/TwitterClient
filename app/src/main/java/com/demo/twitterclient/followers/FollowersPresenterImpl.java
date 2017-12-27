package com.demo.twitterclient.followers;

import com.demo.twitterclient.R;
import com.demo.twitterclient.login.LoginContract;
import com.demo.twitterclient.repo.Followers;
import com.demo.twitterclient.repo.FollowersService;
import com.demo.twitterclient.repo.User;
import com.google.gson.Gson;


public class FollowersPresenterImpl implements FollowersContract.FollowersPresenter {

    private static final String CACHED_FOLLOWERS_KEY = "cachedFollowers";
    private FollowersContract.FollowersView view;
    private Followers followers;


    FollowersPresenterImpl(FollowersContract.FollowersView view) {
        this.view = view;

    }

    @Override
    public void getFollowers(boolean forceRefresh) {
        String token = view.getPrefs().getString(LoginContract.TOKEN_PREFS, "");
        String tokenKey = view.getPrefs().getString(LoginContract.TOKEN_KEY_PREFS, "");

        if (!view.isConnected()) {
            Followers followers = getCachedFollowers();
            if (followers.getUsers() != null) {
                view.onFollowersResponse(followers.getUsers());
                this.followers = followers;
            } else {
                view.showSnackBar(R.string.notConnected, R.id.mainView);
            }

            return;
        }

        FollowersService.getInstance().getFollowers(token, tokenKey, forceRefresh, new FollowersService.FollowersCallback() {
            @Override
            public void setFollowers(boolean isSuccess, Followers followers) {
                if (isSuccess) {
                    FollowersPresenterImpl.this.followers = followers;
                    String followersJson = new Gson().toJson(followers);
                    view.getPrefs().edit().putString(CACHED_FOLLOWERS_KEY, followersJson).apply();
                    view.onFollowersResponse(followers.getUsers());
                } else {
                    view.showMessage(R.string.Error);
                }
            }
        });

    }

    private Followers getCachedFollowers() {
        return new Gson().fromJson(view.getPrefs().getString(CACHED_FOLLOWERS_KEY, ""), Followers.class);
    }

    @Override
    public void onUserClicked(int position) {

        final User user = followers.getUsers().get(position);
        view.showUserDetails(new Gson().toJson(user));

    }
}
