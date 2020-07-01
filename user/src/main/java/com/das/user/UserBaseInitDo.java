package com.das.user;

import android.app.Application;
import android.util.Log;

import com.das.componentbase.ServiceFactory;

public class UserBaseInitDo {

    public static void init(Application application) {


        boolean homeOk = ServiceFactory.getInstance().getHomeService().isHomeOk();
        Log.d("User","homeok "+homeOk);


    }
}
