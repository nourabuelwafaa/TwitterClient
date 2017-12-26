package com.demo.twitterclient.followers;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        initUi();
        presenter = new FollowersPresenterImpl(this);
        presenter.getFollowers();
    }

    private void initUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        followersAdapter = new FollowersAdapter(this, this, followers);
        recyclerView.setAdapter(followersAdapter);

    }

    @Override
    public void onItemClicked(int position) {
       // presenter.onUserClicked(position);
        startActivity(new Intent(this, UserInfoActivity.class));
        //   showFragment(new ImageViewFragment());

    }

    @Override
    public void onFollowersResponse(final List<User> followers) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                FollowersActivity.this.followers.clear();
                FollowersActivity.this.followers.addAll(followers);
                followersAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void showUserDetails(User user) {

    }

    @Override
    public void onRefresh() {
        presenter.getFollowers();

    }
}
