package com.das.user;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.view.BaseActivity;

@Route(path = Router_Pools.User_SplashActivity)
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
//        setContentView(R.layout.user_activity_splash);
    }
}