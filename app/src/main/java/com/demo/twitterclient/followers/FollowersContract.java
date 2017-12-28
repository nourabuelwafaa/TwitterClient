package com.demo.twitterclient.followers;

import com.demo.twitterclient.MainContract;
import com.demo.twitterclient.repo.model.User;

import java.util.List;

public interface FollowersContract {

    int TOTALLY_NEW = 15;
    int CACHED = 17;
    int SCROLLING = 22;

    interface FollowersView extends MainContract.MainView {
        void onFollowersResponse(List<User> followers);

        void showUserDetails(String user);

        void hideScrolling();
    }

    interface FollowersPresenter {
        void getFollowers(int dataVersion);

        void onUserClicked(int position);
    }
}
