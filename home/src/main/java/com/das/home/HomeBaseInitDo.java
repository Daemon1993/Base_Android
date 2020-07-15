package com.das.home;

import android.app.Application;

import com.das.componentbase.ServiceFactory;
import com.das.home.nosql.NoSqlUtils;
import com.das.home.service.HomeService;

public class HomeBaseInitDo {

    public static void init(Application application) {

        NoSqlUtils.init(application);

    }
}
