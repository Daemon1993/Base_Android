package com.das.user;

import android.os.Bundle;
import android.util.Log;

import com.das.componentbase.ServiceFactory;
import com.das.god_base.network.DObserver;
import com.das.god_base.network.RxDUtils;

import com.das.god_base.view.BaseActivity;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        setContentView(R.layout.user_activity_main);



    }
}