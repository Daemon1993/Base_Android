package com.das.v1r1;


import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.das.componentbase.ServiceFactory;
import com.das.componentbase.base_callback.user2out.User2CallBack0;
import com.das.componentbase.router.Router_Pools;
import com.das.componentbase.service.IHomeService;
import com.das.componentbase.service.IUserService;
import com.das.god_base.PermissionsUtils;
import com.das.god_base.network.DObserver;
import com.das.god_base.utils.Object2MapUtils;
import com.das.user.LoginActivity;
import com.das.user.MainActivity;
import com.das.user.RouteUtils;
import com.das.user.network.DasService;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.request.UserLogin;
import com.das.user.network.response.LoginResponse;
import com.das.user.network.response.Splash2Response;
import com.das.user.nosql.NoSqlUtils;
import com.das.god_base.view.BaseActivity;
import com.socks.library.KLog;

import io.reactivex.rxjava3.annotations.NonNull;


public class SplashActivity extends BaseActivity {

    private boolean isSpalshEnable = true;

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

        ServiceFactory.getInstance().getUserService().getAppSplash2(new User2CallBack0() {
            @Override
            public void baseOkCallBack() {

            }

            @Override
            public void baseOkCallBack(boolean isOk) {
                isSpalshEnable=isOk;
            }
        });



    }

    private void doAction() {


        ServiceFactory.getInstance().getUserService().initAction(this, new User2CallBack0() {
            @Override
            public void baseOkCallBack() {

                if (isSpalshEnable && !TextUtils.isEmpty(NoSqlUtils.getObject(NoSqlUtils.splash_2_image))) {
                    RouteUtils.openActivity(SplashActivity.this, Splash2Activity.class);
                } else {
                    ARouter.getInstance().build(Router_Pools.User_MainActivity).navigation();
                }
                finish();
            }
        });


    }
}