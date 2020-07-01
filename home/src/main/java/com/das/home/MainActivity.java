package com.das.home;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.os.Bundle;
import android.util.Log;

import com.das.componentbase.ServiceFactory;
import com.das.god_base.network.DObserver;
import com.das.god_base.network.RxDUtils;
import com.das.home.network.DasService;
import com.das.home.network.RetrofitHandler;
import com.das.home.service.HomeService;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import java.io.IOException;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main2);


        DasService dasService = RetrofitHandler.getInstance().create(DasService.class);
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