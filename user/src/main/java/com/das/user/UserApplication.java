package com.das.user;

import android.app.Application;

import com.das.componentbase.base.BaseApp;
import com.das.god_base.log.DLog;

public class UserApplication extends BaseApp {

    @Override
    public void initModuleApp(Application application) {

        DLog.init("user_v1r1");
    }

    @Override
    public void initModuleData(Application application) {
        UserBaseInitDo.init(this);
    }
}
