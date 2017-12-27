package com.demo.twitterclient;

import android.content.SharedPreferences;
import android.support.annotation.StringRes;

public interface MainContract {

    interface MainView {

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showProgress();

        void hideProgress();

        void showSnackBar(@StringRes int message, int viewId);

        boolean isConnected();

        SharedPreferences getPrefs();
    }

}
