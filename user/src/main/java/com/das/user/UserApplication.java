package com.das.user;

import android.app.Application;

import com.das.componentbase.base.BaseApp;

public class UserApplication extends BaseApp {

    @Override
    public void initModuleApp(Application application) {

    }

    @Override
    public void initModuleData(Application application) {
        UserBaseInitDo.init(this);
    }
}
