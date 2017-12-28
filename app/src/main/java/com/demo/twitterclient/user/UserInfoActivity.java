package com.demo.twitterclient.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.twitterclient.OnItemCLicked;
import com.demo.twitterclient.ParentActivity;
import com.demo.twitterclient.R;
import com.demo.twitterclient.repo.model.User;
import com.demo.twitterclient.repo.model.tweet.Tweet;
import com.demo.twitterclient.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends ParentActivity implements UserInfoContract.UserInfoView {
    UserInfoContract.UserPresenter presenter;
    List<Tweet> tweets = new ArrayList<>();
    TweetsAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        String userJson = getIntent().getStringExtra(User.USER_INTENT_KEY);
        initUi();
        presenter = new UserPresenterImpl(this);
        presenter.onHandleUserInfo(userJson);

    }

    private void initUi() {
        showProgress();
        showPlaceHolder();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new TweetsAdapter(this, tweets);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void showTweets(final List<Tweet> tweets) {

        hideProgress();
        hidePlaceholder();
        UserInfoActivity.this.tweets.clear();
        UserInfoActivity.this.tweets.addAll(tweets);
        recyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void showUserDetails(final User user) {
        CircleImageView userImage = findViewById(R.id.userPhotoIv);
        Picasso.with(this).load(Utils.getBiggerPhoto(user.getProfileImageUrl())).into(userImage);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(ImageViewFragment.newInstance(user.getProfileImageUrl()));
            }
        });

        TextView usernameTv = findViewById(R.id.usernameTv);
        usernameTv.setText(user.getName());

        TextView screenNameTv = findViewById(R.id.screenNameTv);
        screenNameTv.setText(user.getScreenName());

        ImageView backgroundImage = findViewById(R.id.backgroundIv);
        Picasso.with(this).load(user.getProfileBackgroundImageUrl()).into(backgroundImage);
        backgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(ImageViewFragment.newInstance(user.getProfileBackgroundImageUrl()));
            }
        });

    }

}
