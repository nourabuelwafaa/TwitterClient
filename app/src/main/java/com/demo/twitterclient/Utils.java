package com.demo.twitterclient;

public class Utils {


    public static boolean checkNull(String s) {
        return s != null && !s.equals("");
    }

    public static String getBiggerPhoto(String photoUrl) {
        return photoUrl.replace("_normal", "");
    }

    public static String getTweetImage(String profileImageUrl) {
        return profileImageUrl + ":small";
    }
}
