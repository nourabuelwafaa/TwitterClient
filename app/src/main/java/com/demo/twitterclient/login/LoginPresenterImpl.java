package com.demo.twitterclient.login;

import android.content.SharedPreferences;

import com.twitter.sdk.android.core.TwitterSession;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {


    private LoginContract.LoginView view;


    LoginPresenterImpl(LoginContract.LoginView view) {
        this.view = view;
    }

    @Override
    public void onLoginSuccess(TwitterSession twitterSession) {

        SharedPreferences prefs = view.getPrefs();

        prefs.edit().putString(LoginContract.USERNAME_PREFS, twitterSession.getUserName()).apply();
        prefs.edit().putLong(LoginContract.USER_ID_PREFS, twitterSession.getUserId()).apply();
        prefs.edit().putString(LoginContract.TOKEN_KEY_PREFS, twitterSession.getAuthToken().token).apply();
        prefs.edit().putString(LoginContract.TOKEN_SECRET_PREFS, twitterSession.getAuthToken().secret).apply();
        prefs.edit().putBoolean(LoginContract.IS_LOGGED_PREFS, true).apply();

        view.startMainActivity();
    }

}
