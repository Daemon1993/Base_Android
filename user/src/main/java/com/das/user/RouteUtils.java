package com.das.user;

import android.content.Context;
import android.content.Intent;

public class RouteUtils {

    public static void openActivity(Context context, Class clazz) {
        context.startActivity(new Intent(context, clazz));
    }


}
