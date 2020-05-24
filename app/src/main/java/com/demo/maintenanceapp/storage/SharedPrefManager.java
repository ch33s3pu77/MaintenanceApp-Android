package com.demo.maintenanceapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String PREF_NAME = "MAINTENANCE_PREF";

    private String KEY_USERNAME = "pref_username";
    private String KEY_JOB_ID = "pref_job_id";

    public SharedPrefManager(){

    }

    public void saveJobID(Context context, String data){
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(KEY_JOB_ID, data);
        editor.apply();
    }

    public String getJobID(Context context){
        String data;
        SharedPreferences preferences;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        data = preferences.getString(KEY_JOB_ID, "");
        return data;
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
