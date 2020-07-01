package com.das.god_base;

import android.app.Application;

public class BaseInitGod {
    public static  Application application;
    public static void init (Application application1){
        application=application1;
    }
}
