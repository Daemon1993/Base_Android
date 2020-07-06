package com.das.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.ServiceFactory;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.network.DObserver;
import com.das.god_base.network.RxDUtils;

import com.das.god_base.view.BaseActivity;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

@Route(path = Router_Pools.User_MainActivity)
public class MainActivity extends BaseActivity {

    public static void openActivity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        setContentView(R.layout.user_activity_main);

    }
}