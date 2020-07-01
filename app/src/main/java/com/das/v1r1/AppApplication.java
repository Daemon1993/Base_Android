package com.das.v1r1;

import android.app.Application;

import com.das.home.HomeBaseInitDo;
import com.das.user.UserBaseInitDo;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        HomeBaseInitDo.init(this);
        UserBaseInitDo.init(this);

    }
}
