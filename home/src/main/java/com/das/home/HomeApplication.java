package com.das.home;

import android.app.Application;

import com.das.componentbase.ServiceFactory;
import com.das.componentbase.base.BaseApp;
import com.das.home.service.HomeService;

public class HomeApplication extends BaseApp {

    @Override
    public void initModuleApp(Application application) {

        ServiceFactory.getInstance().setHomeService(new HomeService());

        HomeBaseInitDo.init(application);
    }

    @Override
    public void initModuleData(Application application) {

    }
}
