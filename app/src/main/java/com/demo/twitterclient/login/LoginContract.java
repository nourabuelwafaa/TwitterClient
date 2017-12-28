package com.demo.twitterclient.login;

import com.demo.twitterclient.MainContract;
import com.twitter.sdk.android.core.TwitterSession;

public interface LoginContract {

    String USERNAME_PREFS = "usernamePrefs";
    String USER_ID_PREFS = "userIdPrefs";
    String IS_LOGGED_PREFS = "isLoggedPrefs";
    String TOKEN_KEY_PREFS = "tokenKeyPrefs";
    String TOKEN_SECRET_PREFS = "tokenSecretPrefs";

    interface LoginView extends MainContract.MainView {
        void startMainActivity();
    }

    interface LoginPresenter {

        void onLoginSuccess(TwitterSession twitterSession);

    }
}
