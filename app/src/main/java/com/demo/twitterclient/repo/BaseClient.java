package com.demo.twitterclient.repo;


import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class BaseClient {
    public static final String CONSUMER_KEY = "";
    public static final String CONSUMER_SECRET = "";
    public static final String BASE_URL = "https://api.twitter.com/1.1";


    final static private OAuth10aService service = new ServiceBuilder()
            .apiKey(CONSUMER_KEY)
            .apiSecret(CONSUMER_SECRET)
            .build(com.github.scribejava.apis.TwitterApi.instance());

    public static void serviceCall(OAuth1AccessToken token, final OAuthRequest request, final OnResponseHandler onResponse) {

        service.signRequest(token, request);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    onResponse.onResponseHandler(true, service.execute(request).getBody());
                } catch (IOException | InterruptedException | ExecutionException e) {
                    onResponse.onResponseHandler(false, "");
                    e.printStackTrace();

                }

            }
        });
        thread.start();


    }

}
