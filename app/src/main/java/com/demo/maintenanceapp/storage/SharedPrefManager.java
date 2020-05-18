package com.demo.maintenanceapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String PREF_NAME = "MAINTENANCE_PREF";

    private String KEY_USERNAME = "pref_username";

    public SharedPrefManager(){

    }

    public void saveUsername(Context context, String data){
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(KEY_USERNAME, data);
        editor.apply();
    }

    public String getUsername(Context context){
        String data;
        SharedPreferences preferences;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        data = preferences.getString(KEY_USERNAME,"");
        return data;
    }
}
