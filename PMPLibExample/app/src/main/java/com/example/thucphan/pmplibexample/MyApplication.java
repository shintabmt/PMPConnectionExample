package com.example.thucphan.pmplibexample;

import android.app.Application;

import com.example.thucphan.pmplibexample.managers.PMPConnectionManager;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;


    public static MyApplication getInstance(){
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        PMPConnectionManager.getInstance().init();
    }

}
