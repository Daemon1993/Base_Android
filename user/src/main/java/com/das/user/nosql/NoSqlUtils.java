package com.das.user.nosql;

import android.content.Context;
import android.text.TextUtils;

import com.das.user.UserApplication;
import com.das.user.network.request.UserLogin;

import io.paperdb.Paper;

public class NoSqlUtils {

    //login  userbean
    public static final String login_user="login_user";

    //login response
    public static final String login_response="login_response";

    public static final String splash_2_image="splash_2_image";


    public static void init(Context context) {
        Paper.init(context);
    }

    public static void addObject(String key,Object obj){
        Paper.book().write(key,obj);

        if(TextUtils.equals(login_user,key)){
            //登陆用户
            UserLogin userLogin= (UserLogin) obj;
            UserApplication.userName=userLogin.UserName;
        }
    }

    public static <T> T getObject(String key) {
        return Paper.book().read(key);
    }

    public static void updateUserLoginPwd(String pwd) {
        UserLogin userLogin = getObject(login_user);
        userLogin.setPassword(pwd);
        addObject(login_user,userLogin);

    }
}
