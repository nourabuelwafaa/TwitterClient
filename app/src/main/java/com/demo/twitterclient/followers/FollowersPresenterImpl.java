package com.demo.twitterclient.followers;

import com.demo.twitterclient.R;
import com.demo.twitterclient.login.LoginContract;
import com.demo.twitterclient.repo.RetrofitClient;
import com.demo.twitterclient.repo.model.Followers;
import com.demo.twitterclient.repo.FollowersService;
import com.demo.twitterclient.repo.model.User;
import com.google.gson.Gson;


public class FollowersPresenterImpl implements FollowersContract.FollowersPresenter {

    private static final FollowersPresenterImpl instance = new FollowersPresenterImpl();
    private static final String CACHED_FOLLOWERS_KEY = "cachedFollowers";
    private static FollowersContract.FollowersView view;
    private Followers followersRef = new Followers();
    private static long cursor = -1;


    static FollowersPresenterImpl getInstance(FollowersContract.FollowersView view) {
        FollowersPresenterImpl.view = view;
        return instance;
    }

    private FollowersPresenterImpl() {

    }

    @Override
    public void getFollowers(int dataVersion) {

        if (!view.isConnected()) {
            Followers followers = getCachedFollowers();
            if (followersRef.getUsers().size() == 0) {
                view.onFollowersResponse(followers.getUsers());
                followersRef.setUsers(followers.getUsers());

            } else {
                view.onFollowersResponse(followersRef.getUsers());
                view.hideProgress();
                view.hidePlaceholder();
                view.hideScrolling();
                view.showSnackBar(R.string.notConnected, R.id.mainView);
            }

            return;
        }
        switch (dataVersion) {
            case FollowersContract.CACHED:
                Followers followers = getCachedFollowers();
                view.onFollowersResponse(followers.getUsers());
                view.hideProgress();
                view.hidePlaceholder();
                return;

            case FollowersContract.TOTALLY_NEW:
                cursor = -1;
                followersRef.getUsers().clear();
                triggerRequest();
                break;

            case FollowersContract.SCROLLING:
                triggerRequest();
                break;
        }

    }

    private void triggerRequest() {
        RetrofitClient.TOKEN_KEY = view.getPrefs().getString(LoginContract.TOKEN_KEY_PREFS, "");
        RetrofitClient.TOKEN_SECRET = view.getPrefs().getString(LoginContract.TOKEN_SECRET_PREFS, "");

        if (cursor != 0) {
            FollowersService.getInstance().getFollowers(cursor, new FollowersService.FollowersCallback() {
                @Override
                public void setFollowers(boolean isSuccess, Followers followers) {
                    if (isSuccess) {
                        cursor = followers.getNextCursor();
                        followersRef.getUsers().addAll(followers.getUsers());
                        String followersJson = new Gson().toJson(followersRef);
                        view.getPrefs().edit().putString(CACHED_FOLLOWERS_KEY, followersJson).apply();
                        view.onFollowersResponse(followersRef.getUsers());
                    } else {
                        view.showMessage(R.string.Error);
                        view.hideProgress();
                        view.hideScrolling();
                        view.hidePlaceholder();
                    }
                }
            });
        } else {
            view.hideProgress();
        }
    }

    private Followers getCachedFollowers() {
        return new Gson().fromJson(view.getPrefs().getString(CACHED_FOLLOWERS_KEY, ""), Followers.class);
    }

    @Override
    public void onUserClicked(int position) {

        final User user = followersRef.getUsers().get(position);
        view.showUserDetails(new Gson().toJson(user));

    }
}
