package com.das.user.service;

import com.alibaba.android.arouter.launcher.ARouter;
import com.das.componentbase.base_callback.user2out.User2CallBack0;
import com.das.componentbase.router.Router_Pools;
import com.das.componentbase.service.IUserService;

import com.das.god_base.network.BaseResponseResult;
import com.das.god_base.network.DObserver;
import com.das.god_base.utils.Object2MapUtils;
import com.das.user.LoginActivity;
import com.das.user.network.DasService;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.request.UserLogin;
import com.das.user.network.response.LoginResponse;
import com.das.user.network.response.Splash2Response;
import com.das.user.nosql.NoSqlUtils;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;

public class UserServie implements IUserService {
    @Override
    public void initAction(RxAppCompatActivity activity, User2CallBack0 user2CallBack0) {

        UserLogin userLogin = NoSqlUtils.getObject(NoSqlUtils.login_user);
        if (userLogin == null) {
            ARouter.getInstance().build(Router_Pools.User_Login_Activity).navigation();
            return;
        }

        UserRetrofitHandler.getInstance().createDasService().login(Object2MapUtils.praseObject2Map(userLogin))
                .compose(activity.bindToLifecycle())
                .subscribe(new DObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {

                        NoSqlUtils.addObject(NoSqlUtils.login_user, userLogin);
                        NoSqlUtils.addObject(NoSqlUtils.login_response, loginResponse);
                        if (user2CallBack0 != null) {
                            user2CallBack0.baseOkCallBack();
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);

                        LoginActivity.openActivity(activity);
                    }
                });

    }

    @Override
    public String getAuthToken() {
        LoginResponse loginResponse = NoSqlUtils.getObject(NoSqlUtils.login_response);
        return "Bearer " + loginResponse.getData().getAccess_token();
    }

    @Override
    public void getAppSplash2(User2CallBack0 user2CallBack0) {

        UserRetrofitHandler.getInstance().createDasService().getAppSplash2()
                .subscribe(new DObserver<Splash2Response>() {
                    @Override
                    public void onSuccess(Splash2Response response) {

                        try {
                            if (response.getData().isEnable()) {

                                boolean isSpalshEnable = response.getData().isEnable();
                                NoSqlUtils.addObject(NoSqlUtils.splash_2_image, DasService.BASEURL + response.getData().getIamgeUrl());

                                if (user2CallBack0 != null) {
                                    user2CallBack0.baseOkCallBack(isSpalshEnable);
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    @Override
    public String getSplashImagePath() {
        return NoSqlUtils.getObject(NoSqlUtils.splash_2_image);
    }

    @Override
    public void getHomeMenuJson(User2CallBack0 user2CallBack0) {
        UserRetrofitHandler.getInstance().createDasService().GetMenuPageList()
                .subscribe(new DObserver<BaseResponseResult>() {
                    @Override
                    public void onSuccess(BaseResponseResult response) {
                        if (user2CallBack0 != null) {
                            user2CallBack0.baseOkCallBack(response.message);
                        }
                    }
                });

    }

}
