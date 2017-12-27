package com.demo.twitterclient.followers;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.demo.twitterclient.OnItemCLicked;
import com.demo.twitterclient.ParentActivity;
import com.demo.twitterclient.R;
import com.demo.twitterclient.repo.User;
import com.demo.twitterclient.user.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends ParentActivity implements FollowersContract.FollowersView, OnItemCLicked, SwipeRefreshLayout.OnRefreshListener {
    private FollowersContract.FollowersPresenter presenter;
    private FollowersAdapter followersAdapter;
    List<User> followers = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    View sharedElementView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        initUi();
        presenter = new FollowersPresenterImpl(this);
        presenter.getFollowers(false);

    }

    private void initUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showProgress();
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        }
        followersAdapter = new FollowersAdapter(this, this, followers);
        recyclerView.setAdapter(followersAdapter);

    }

    @Override
    public void onItemClicked(int position, View view) {
        sharedElementView = view;
        presenter.onUserClicked(position);

    }

    @Override
    public void onFollowersResponse(final List<User> followers) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                hideProgress();
                FollowersActivity.this.followers.clear();
                FollowersActivity.this.followers.addAll(followers);
                followersAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void showUserDetails(final String user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FollowersActivity.this, UserInfoActivity.class);
                String transitionName = getString(R.string.userImage);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(FollowersActivity.this, sharedElementView, transitionName);
                intent.putExtra(User.USER_INTENT_KEY, user);
                startActivity(intent, activityOptions.toBundle());
            }
        });

    }

    @Override
    public void onRefresh() {
        presenter.getFollowers(true);

    }
}
