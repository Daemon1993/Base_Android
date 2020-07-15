package com.das.home.out_service;

import android.text.TextUtils;

import com.das.componentbase.ServiceFactory;

public class OutServiceUtils {

    //更新后这里需要更新
    public static String cache_token = "";


    public static void updateUserAuthToken(String token){
        cache_token=token;
    }
    public static String getUserAuthToken() {
        if(TextUtils.isEmpty(cache_token)) {
            cache_token = ServiceFactory.getInstance().getUserService().getAuthToken();
        }
        return cache_token;
    }
}
