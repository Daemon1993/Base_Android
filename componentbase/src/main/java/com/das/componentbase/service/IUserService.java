package com.das.componentbase.service;

import com.das.componentbase.base_callback.user2out.User2CallBack0;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

public interface IUserService {



     void initAction(RxAppCompatActivity appCompatActivity, User2CallBack0 user2CallBack0);
    String getAuthToken();

    void getAppSplash2(User2CallBack0 user2CallBack0);

    String getSplashImagePath();


    void getHomeMenuJson(User2CallBack0 user2CallBack0);

}
