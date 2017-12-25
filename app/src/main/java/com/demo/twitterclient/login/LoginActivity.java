package com.demo.twitterclient.login;

import android.content.Intent;
import android.os.Bundle;

import com.demo.twitterclient.ParentActivity;
import com.demo.twitterclient.R;
import com.demo.twitterclient.followers.FollowersActivity;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class LoginActivity extends ParentActivity implements LoginContract.LoginView {
    TwitterLoginButton loginButton;
    LoginContract.LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.login_button);
        presenter = new LoginPresenterImpl(this);

        loginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                    presenter.onLoginSuccess(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                showMessage(exception.getLocalizedMessage());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startMainActivity() {

        startActivity(new Intent(this, FollowersActivity.class));
        finish();

    }

}
