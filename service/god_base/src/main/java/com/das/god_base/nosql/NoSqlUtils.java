package com.das.god_base.nosql;

import android.app.Application;
import android.content.Context;

import io.paperdb.Paper;

public class NoSqlUtils {
    public static void init(Context context) {
        Paper.init(context);
    }
}
