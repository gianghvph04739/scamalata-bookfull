package com.skyreds.truyenfull.networking;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SharePref {
    private static SharePref sharePref = new SharePref();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static final String LIST_SONG = "LIST_SONG";

    private SharePref() {}

    public static SharePref getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }
}
