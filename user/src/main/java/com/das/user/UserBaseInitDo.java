package com.das.user;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.das.componentbase.ServiceFactory;
import com.das.god_base.Dlog;
import com.das.user.nosql.NoSqlUtils;
import com.das.user.out_service.LoginServie;

import okhttp3.OkHttpClient;

public class UserBaseInitDo {

    public static void init(Application application) {


// Adding an Network Interceptor for Debugging purpose :
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(application.getApplicationContext(),okHttpClient);

        boolean homeOk = ServiceFactory.getInstance().getHomeService().isHomeOk();
        Dlog.d("homeok "+homeOk);

        NoSqlUtils.init(application);
        ServiceFactory.getInstance().setLoginService(new LoginServie());

    }
}
