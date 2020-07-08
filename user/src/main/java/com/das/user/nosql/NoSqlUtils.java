package com.das.user.nosql;

import android.content.Context;

import io.paperdb.Paper;

public class NoSqlUtils {

    //login  userbean
    public static final String login_user="login_user";

    //login response
    public static final String login_response="login_response";


    public static void init(Context context) {
        Paper.init(context);
    }

    public static void addObject(String key,Object obj){
        Paper.book().write(key,obj);
    }

    public static <T> T getObject(String key) {
        return Paper.book().read(key);
    }
}
