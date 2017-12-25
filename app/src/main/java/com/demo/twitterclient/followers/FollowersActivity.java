package com.demo.twitterclient.followers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.twitterclient.ParentActivity;
import com.demo.twitterclient.R;

public class FollowersActivity extends ParentActivity implements FollowersContract.FollowersView {
    private FollowersContract.FollowersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        presenter = new FollowersPresenterImpl(this);
        presenter.getFollowers();
    }
}
