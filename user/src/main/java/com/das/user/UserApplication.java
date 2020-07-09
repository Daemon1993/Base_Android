package com.das.user;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.das.componentbase.base.BaseApp;

import com.das.god_base.BaseInitGod;
import com.das.god_base.life.LifecycleApplication;
import com.socks.library.KLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        KLog.init(true,"user_v1r1");

        if (!isMainProcess()) {
            String packageName = getPackageName();
            KLog.d("AAA", packageName);

            return;
        }

        BaseInitGod.init(application);
        KLog.e("UserApplication");


    }

    @Override
    public void initModuleData(Application application) {
        UserBaseInitDo.init(this);

    }


}
