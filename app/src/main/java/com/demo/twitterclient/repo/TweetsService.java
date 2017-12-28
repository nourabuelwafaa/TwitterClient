package com.demo.twitterclient.repo;

import android.support.annotation.NonNull;

import com.demo.twitterclient.repo.model.Followers;
import com.demo.twitterclient.repo.model.tweet.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TweetsService {

    private static final TweetsService instance = new TweetsService();
    private Followers followers;

    public static TweetsService getInstance() {
        return instance;
    }

    private TweetsService() {
    }


    public void getTweets(long userId, final TweetsCallback callback) {

        RetrofitClient.getInstance().create(GetTweets.class).getTweets(userId).enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tweet>> call, @NonNull Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    callback.setTweets(true, response.body());
                } else {
                    callback.setTweets(false, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Tweet>> call, @NonNull Throwable t) {
                t.printStackTrace();
                callback.setTweets(false, null);
            }
        });
    }

    public interface TweetsCallback {

        void setTweets(boolean isSuccess, List<Tweet> tweets);
    }

    interface GetTweets {
        @GET("statuses/user_timeline.json?count=10")
        Call<List<Tweet>> getTweets(@Query("user_id") long userId);
    }
}
