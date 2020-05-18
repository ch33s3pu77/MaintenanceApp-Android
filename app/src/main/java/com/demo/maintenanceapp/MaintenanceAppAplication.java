package com.demo.maintenanceapp;

import android.app.Application;
import android.content.Context;

public class MaintenanceAppAplication extends Application {
    private static MaintenanceAppAplication sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }

    public static MaintenanceAppAplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
