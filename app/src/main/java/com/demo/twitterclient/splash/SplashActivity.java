package com.demo.twitterclient.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.demo.twitterclient.ParentActivity;
import com.demo.twitterclient.R;
import com.demo.twitterclient.followers.FollowersActivity;
import com.demo.twitterclient.login.LoginActivity;
import com.demo.twitterclient.login.LoginContract;


public class SplashActivity extends ParentActivity {

    @Override
    public void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (getPrefs().getBoolean(LoginContract.IS_LOGGED_PREFS, false)) {

                    startActivity(new Intent(SplashActivity.this, FollowersActivity.class));

                } else {

                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                }
                finish();

            }
        }, 1000);
    }


}
