package com.das.user;


import android.os.Bundle;
import android.view.Window;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.log.DLog;
import com.das.god_base.view.BaseActivity;

@Route(path = Router_Pools.User_SplashActivity)
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
//        setContentView(R.layout.user_activity_splash);


        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                DLog.d("--------------");

                MainActivity.openActivity(SplashActivity.this);

            }
        },2000);
    }
}