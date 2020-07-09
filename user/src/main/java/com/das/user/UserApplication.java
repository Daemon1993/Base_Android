package com.das.user;

import android.app.Application;

import com.das.componentbase.base.BaseApp;

import com.das.god_base.BaseInitGod;
import com.das.god_base.life.LifecycleApplication;
import com.socks.library.KLog;

public class UserApplication extends BaseApp {

    public static String userName;

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);

    }

    @Override
    public void initModuleApp(Application application) {

        registerActivityLifecycleCallbacks(new LifecycleApplication());

        BaseInitGod.init(application);
        KLog.init(true,"user_v1r1");
        KLog.e("UserApplication");


    }

    @Override
    public void initModuleData(Application application) {
        UserBaseInitDo.init(this);

    }
}
