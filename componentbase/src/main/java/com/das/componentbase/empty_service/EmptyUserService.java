package com.das.componentbase.empty_service;

import com.das.componentbase.base_callback.user2out.User2CallBack0;
import com.das.componentbase.service.IUserService;

import com.socks.library.KLog;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

public class EmptyUserService implements IUserService {


    @Override
    public void initAction(RxAppCompatActivity appCompatActivity, User2CallBack0 user2CallBack0) {
        KLog.d("EmptyLoginService initAction");
    }

    @Override
    public String getAuthToken() {
        return "token";
    }

    @Override
    public void getAppSplash2(User2CallBack0 user2CallBack0) {
        if (user2CallBack0==null){
            return;
        }
        user2CallBack0.baseOkCallBack(false);
    }

    @Override
    public String getSplashImagePath() {
        return "";
    }

    @Override
    public void getHomeMenuJson(User2CallBack0 user2CallBack0) {
        user2CallBack0.baseOkCallBack("");
    }
}
