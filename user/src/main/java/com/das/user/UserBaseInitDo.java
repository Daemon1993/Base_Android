package com.das.user;

import android.app.Application;

import com.das.componentbase.ServiceFactory;
import com.das.god_base.Dlog;
import com.das.user.nosql.NoSqlUtils;
import com.das.user.out_service.LoginServie;

public class UserBaseInitDo {

    public static void init(Application application) {


        boolean homeOk = ServiceFactory.getInstance().getHomeService().isHomeOk();
        Dlog.d("homeok "+homeOk);

        NoSqlUtils.init(application);
        ServiceFactory.getInstance().setLoginService(new LoginServie());

    }
}
