package com.das.home.nosql;

import android.content.Context;
import android.text.TextUtils;


import io.paperdb.Paper;

public class NoSqlUtils {


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
