package com.demo.twitterclient.repo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String CONSUMER_KEY = "jdkY6HoL6306ljfnsbRlSPKHE";
    public static final String CONSUMER_SECRET = "yh8bcbOuLJpj8UBAAzsPfGnfLmDciWvHYqxkfGN85Sr6IxNqHy";
    public static String TOKEN_KEY = "";
    public static String TOKEN_SECRET = "";
    private static final String baseUrl = "https://api.twitter.com/1.1/";

    static Retrofit getInstance() {

        Oauth1SigningInterceptor interceptor = new Oauth1SigningInterceptor.Builder()
                .consumerKey(CONSUMER_KEY)
                .consumerSecret(CONSUMER_SECRET)
                .accessToken(TOKEN_KEY)
                .accessSecret(TOKEN_SECRET)
                .build();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}


