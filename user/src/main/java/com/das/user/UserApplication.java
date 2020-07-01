package com.das.user;

import android.app.Application;

public class UserApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UserBaseInitDo.init(this);
    }
}
