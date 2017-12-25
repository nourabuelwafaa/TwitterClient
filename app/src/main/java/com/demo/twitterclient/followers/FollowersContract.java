package com.demo.twitterclient.followers;

import com.demo.twitterclient.MainContract;

public interface FollowersContract {

    interface FollowersView extends MainContract.MainView {

    }

    interface FollowersPresenter {
        void getFollowers();
    }
}
