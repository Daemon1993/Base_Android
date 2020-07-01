package com.das.home;

import android.app.Application;

public class HomeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        HomeBaseInitDo.init(this);
    }
}
