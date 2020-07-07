package com.das.user;

import android.app.Application;

import com.das.componentbase.ServiceFactory;
import com.das.componentbase.base.BaseApp;

import com.das.user.out_service.LoginServie;
import com.socks.library.KLog;

public class UserApplication extends BaseApp {

    @Override
    public void initModuleApp(Application application) {

        KLog.init(true,"user_v1r1");
        ServiceFactory.getInstance().setLoginService(new LoginServie());
    }

    @Override
    public void initModuleData(Application application) {
        UserBaseInitDo.init(this);
    }
}
