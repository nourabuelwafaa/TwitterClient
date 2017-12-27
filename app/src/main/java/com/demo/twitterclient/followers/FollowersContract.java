package com.demo.twitterclient.followers;

import android.view.View;

import com.demo.twitterclient.MainContract;
import com.demo.twitterclient.repo.User;

import java.util.List;

public interface FollowersContract {

    interface FollowersView extends MainContract.MainView {
        void onFollowersResponse(List<User> followers);

        void showUserDetails(String user);
    }

    interface FollowersPresenter {
        void getFollowers(boolean forceRefresh);

        void onUserClicked(int position);
    }
}
