package com.demo.twitterclient.user;

import com.demo.twitterclient.R;
import com.demo.twitterclient.login.LoginContract;
import com.demo.twitterclient.repo.RetrofitClient;
import com.demo.twitterclient.repo.TweetsService;
import com.demo.twitterclient.repo.model.User;
import com.demo.twitterclient.repo.model.tweet.Tweet;
import com.google.gson.Gson;

import java.util.List;

public class UserPresenterImpl implements UserInfoContract.UserPresenter {

    private UserInfoContract.UserInfoView view;

    UserPresenterImpl(UserInfoContract.UserInfoView view) {
        this.view = view;
        RetrofitClient.TOKEN_KEY = view.getPrefs().getString(LoginContract.TOKEN_KEY_PREFS, "");
        RetrofitClient.TOKEN_SECRET = view.getPrefs().getString(LoginContract.TOKEN_SECRET_PREFS, "");

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
            view.hidePlaceholder();
            return;
        }

        TweetsService.getInstance().getTweets(userId, new TweetsService.TweetsCallback() {
            @Override
            public void setTweets(boolean isSuccess, List<Tweet> tweets) {
                view.hideProgress();
                if (isSuccess) {
                    view.showTweets(tweets);
                } else {
                    view.showMessage(R.string.Error);

                }
            }
        });
    }
}
