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
import com.demo.twitterclient.repo.model.User;
import com.demo.twitterclient.user.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends ParentActivity implements FollowersContract.FollowersView, OnItemCLicked
        , SwipeRefreshLayout.OnRefreshListener {
    private static final String ORIENTATION_CHANGE = "orientationChange";
    private FollowersContract.FollowersPresenter presenter;
    private FollowersAdapter followersAdapter;
    private List<User> followers = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private View sharedElementView;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        initUi();
        presenter = FollowersPresenterImpl.getInstance(this);

        if (savedInstanceState != null && savedInstanceState.getBoolean(ORIENTATION_CHANGE)) {
            presenter.getFollowers(FollowersContract.CACHED);
        } else {
            presenter.getFollowers(FollowersContract.TOTALLY_NEW);

        }
    }

    private void initUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showProgress();
        showPlaceHolder();
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        if (isPortrait()) {
            recyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        }
        followersAdapter = new FollowersAdapter(this, this, followers);
        recyclerView.setAdapter(followersAdapter);
        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                if (followers.size() >= 20) {
                    handleOnFlang(isPortrait());
                }
                return false;
            }

        });

    }

    private void handleOnFlang(boolean isPortrait) {

        if (!isConnected()) return;

        int visibleItemCount;
        int totalItemCount;
        int pastVisibleItems;

        if (isPortrait) {
            visibleItemCount = linearLayoutManager.getChildCount();
            totalItemCount = linearLayoutManager.getItemCount();
            pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

        } else {
            visibleItemCount = staggeredGridLayoutManager.getChildCount();
            totalItemCount = staggeredGridLayoutManager.getItemCount();
            int[] pastVisible = staggeredGridLayoutManager.findFirstVisibleItemPositions(null);
            pastVisibleItems = pastVisible[0];
        }

        if (pastVisibleItems + visibleItemCount >= totalItemCount) {
            showProgress();
            presenter.getFollowers(FollowersContract.SCROLLING);
        }
    }

    @Override
    public void onItemClicked(int position, View view) {
        sharedElementView = view;
        presenter.onUserClicked(position);

    }

    @Override
    public void onFollowersResponse(final List<User> followers) {

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        hideProgress();
        hidePlaceholder();
        FollowersActivity.this.followers.clear();
        FollowersActivity.this.followers.addAll(followers);
        followersAdapter.notifyDataSetChanged();

    }

    @Override
    public void showUserDetails(final String user) {

        Intent intent = new Intent(FollowersActivity.this, UserInfoActivity.class);
        String transitionName = getString(R.string.userImage);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(FollowersActivity.this, sharedElementView, transitionName);
        intent.putExtra(User.USER_INTENT_KEY, user);
        startActivity(intent, activityOptions.toBundle());

    }

    @Override
    public void hideScrolling() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        showProgress();
        presenter.getFollowers(FollowersContract.TOTALLY_NEW);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ORIENTATION_CHANGE, true);
    }
}
