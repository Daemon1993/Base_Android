package com.das.user;


import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.PermissionsUtils;
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
import com.das.user.user_center.Splash2Activity;
import com.socks.library.KLog;

import java.io.File;

import io.reactivex.rxjava3.annotations.NonNull;

@Route(path = Router_Pools.User_SplashActivity)
public class SplashActivity extends BaseActivity {

    private boolean isSpalshEnable=true;

    @Override
    protected void initLazyAction() {
        getSplashPic();

        PermissionsUtils.requestStorage(this, new PermissionsUtils.PermissonsCallback() {
            @Override
            public void resultOK() {

                getWindow().getDecorView().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        KLog.d("--------------");


                        doAction();


                    }
                }, 1500);

            }
        });



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

                        try {
                            if(response.getData().isEnable()){
                                isSpalshEnable=response.getData().isEnable();
                                NoSqlUtils.addObject(NoSqlUtils.splash_2_image,DasService.BASEURL + response.getData().getIamgeUrl());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        DImageUtils.dowload_image2local(getApplicationContext(), DasService.BASEURL + response.getData().getIamgeUrl(), new DImageUtils.ImageActionCallBack() {
//                            @Override
//                            public void doAction(File resource) {
//                                AsyncUtls.asyncTask(new AsyncUtls.AsyncTask<String>() {
//                                    @Override
//                                    public String doTask() {
//                                        String path = FileUtils.copyNio(getApplicationContext(), resource, "splash000.jpg");
//                                        return path;
//                                    }
//                                }, new AsyncUtls.AsyncCallBack<String>() {
//                                    @Override
//                                    public void onResult(String result) {
//
//                                        KLog.d("--splash image onResourceReady--" + result);
//
//                                        NoSqlUtils.addObject(NoSqlUtils.splash_2_image,result);
//
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//                                        KLog.d("--splash image onError--" + e.getLocalizedMessage());
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onError(String e) {
//
//                            }
//                        });

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

                        if(isSpalshEnable && !TextUtils.isEmpty( NoSqlUtils.getObject(NoSqlUtils.splash_2_image))) {
                            RouteUtils.openActivity(SplashActivity.this, Splash2Activity.class);
                        }else{
                            RouteUtils.openActivity(SplashActivity.this, MainActivity.class);
                        }
                        finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);

                        LoginActivity.openActivity(SplashActivity.this);
                    }
                });

    }
}