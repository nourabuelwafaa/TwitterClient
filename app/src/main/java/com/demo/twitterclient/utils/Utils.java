package com.demo.twitterclient.utils;

public class Utils {


    public static boolean checkNull(String s) {
        return s != null && !s.equals("");
    }

    public static String getBiggerBackground(String photoUrl) {
        return photoUrl.replace("_normal", "");
    }

    public static String getBiggerPhoto(String photoUrl) {
        return photoUrl.replace("normal", "bigger");
    }


    public static String getTweetImage(String profileImageUrl) {
        return profileImageUrl + ":small";
    }
}
