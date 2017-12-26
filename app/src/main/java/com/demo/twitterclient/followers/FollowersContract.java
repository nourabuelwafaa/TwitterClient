package com.demo.twitterclient.followers;

import com.demo.twitterclient.MainContract;
import com.demo.twitterclient.repo.User;

import java.util.List;

public interface FollowersContract {

    interface FollowersView extends MainContract.MainView {
        void onFollowersResponse(List<User> followers);

        void showUserDetails(User user);
    }

    interface FollowersPresenter {
        void getFollowers();

        void onUserClicked(int position);
    }
}
