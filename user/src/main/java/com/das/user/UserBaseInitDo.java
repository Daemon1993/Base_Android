package com.das.user;

import android.app.Application;
import android.util.Log;

import com.das.componentbase.ServiceFactory;
import com.das.god_base.Dlog;

public class UserBaseInitDo {

    public static void init(Application application) {


        boolean homeOk = ServiceFactory.getInstance().getHomeService().isHomeOk();
        Dlog.d("homeok "+homeOk);


    }
}
