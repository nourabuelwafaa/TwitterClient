package com.demo.twitterclient.user;

import com.demo.twitterclient.MainContract;
import com.demo.twitterclient.repo.model.User;
import com.demo.twitterclient.repo.model.tweet.Tweet;

import java.util.List;

public interface UserInfoContract {

    interface UserInfoView extends MainContract.MainView {
        void showTweets(List<Tweet> tweets);

        void showUserDetails(User user);
    }

    interface UserPresenter {

        void onHandleUserInfo(String userJson);



    }
}
