package com.das.user;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.router.Router_Pools;

import com.das.god_base.utils.Object2MapUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.request.UserLogin;
import com.das.user.network.response.LoginResponse;
import com.das.user.nosql.NoSqlUtils;
import com.das.user.user_center.UserFragment;
import com.socks.library.KLog;


import java.io.IOException;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;


@Route(path = Router_Pools.User_MainActivity)
public class MainActivity extends BaseActivity {

    public static void openActivity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }

    @Override
    protected void initLazyAction() {

    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        setContentView(R.layout.user_activity_main);

        FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, new UserFragment());

        // addToBackStack添加到回退栈,addToBackStack与ft.add(R.id.fragment, new
        // MyFragment())效果相当
        // ft.addToBackStack("test");

        fragmentTransaction.commit();

    }
}