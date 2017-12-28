package com.demo.twitterclient.repo;

import android.support.annotation.NonNull;

import com.demo.twitterclient.repo.model.Followers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FollowersService {
    private static final FollowersService instance = new FollowersService();
    private Followers followers;

    public static FollowersService getInstance() {
        return instance;
    }

    private FollowersService() {
    }


    public void getFollowers(long cursor, final FollowersCallback callback) {


        RetrofitClient.getInstance().create(GetFollowers.class).getFollowers(cursor).enqueue(new Callback<Followers>() {
            @Override
            public void onResponse(@NonNull Call<Followers> call, @NonNull Response<Followers> response) {
                if (response.isSuccessful()) {
                    followers = response.body();
                    callback.setFollowers(true, response.body());

                } else {
                    callback.setFollowers(false, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Followers> call, @NonNull Throwable t) {
                t.printStackTrace();
                callback.setFollowers(false, null);
            }
        });
    }

    public interface FollowersCallback {

        void setFollowers(boolean isSuccess, Followers followers);
    }

    private interface GetFollowers {
        @GET("followers/list.json")
        Call<Followers> getFollowers(@Query("cursor") long page);
    }
}
