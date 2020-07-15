package com.das.home;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;


import android.os.Bundle;

import com.das.god_base.network.DObserver;
import com.das.god_base.network.RxDUtils;
import com.das.home.network.DasHomeService;
import com.das.home.network.RetroHomefitHandler;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main2);


        DasHomeService dasService = RetroHomefitHandler.getInstance().create(DasHomeService.class);
        Observable<ResponseBody> test = dasService.test("http://www.baidu.com");

          RxDUtils.doRxAction(test)
                .compose(bindToLifecycle())
                .subscribe(new DObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody response) {

                    }
                });



    }
}