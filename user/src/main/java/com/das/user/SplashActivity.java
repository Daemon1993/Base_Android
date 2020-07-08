package com.das.user;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.images.DImageUtils;
import com.das.god_base.network.BaseResponseResult;
import com.das.god_base.network.DObserver;
import com.das.god_base.utils.AsyncUtls;
import com.das.god_base.utils.FileUtils;
import com.das.god_base.utils.Object2MapUtils;
import com.das.user.network.DasService;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.request.UserLogin;
import com.das.user.network.response.LoginResponse;
import com.das.user.network.response.Splash2Response;
import com.das.user.nosql.NoSqlUtils;
import com.das.god_base.view.BaseActivity;
import com.socks.library.KLog;

import java.io.File;

@Route(path = Router_Pools.User_SplashActivity)
public class SplashActivity extends BaseActivity {

    @Override
    protected void initLazyAction() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                KLog.d("--------------");
                getSplashPic();

                doAction();


            }
        }, 1500);

    }


    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
//        setContentView(R.layout.user_activity_splash);

    }


    private void getSplashPic() {

        UserRetrofitHandler.getInstance().createDasService().getAppSplash2()
                .subscribe(new DObserver<Splash2Response>() {
                    @Override
                    public void onSuccess(Splash2Response response) {
                        DImageUtils.dowload_image2local(getApplicationContext(), DasService.BASEURL + response.getData().getIamgeUrl(), new DImageUtils.ImageActionCallBack() {
                            @Override
                            public void doAction(File resource) {
                                AsyncUtls.asyncTask(new AsyncUtls.AsyncTask<String>() {
                                    @Override
                                    public String doTask() {
                                        String path = FileUtils.copyNio(getApplicationContext(), resource, "splash000.jpg");
                                        return path;
                                    }
                                }, new AsyncUtls.AsyncCallBack<String>() {
                                    @Override
                                    public void onResult(String result) {

                                        KLog.d("--splash image onResourceReady--" + result);

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        KLog.d("--splash image onError--" + e.getLocalizedMessage());
                                    }
                                });
                            }

                            @Override
                            public void onError(String e) {

                            }
                        });

                    }
                });

    }

    private void doAction() {


        UserLogin userLogin = NoSqlUtils.getObject(NoSqlUtils.login_user);
        if (userLogin == null) {
            LoginActivity.openActivity(SplashActivity.this);
            return;
        }

        UserRetrofitHandler.getInstance().createDasService().login(Object2MapUtils.praseObject2Map(userLogin))
                .compose(bindToLifecycle())
                .subscribe(new DObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {

                        NoSqlUtils.addObject(NoSqlUtils.login_user, userLogin);
                        NoSqlUtils.addObject(NoSqlUtils.login_response, loginResponse);


                        MainActivity.openActivity(SplashActivity.this);
                        finish();
                    }
                });

    }
}