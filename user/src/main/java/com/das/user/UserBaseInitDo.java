package com.das.user;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.das.componentbase.ServiceFactory;
import com.das.god_base.Dlog;
import com.das.user.nosql.NoSqlUtils;
import com.das.user.service.UserServie;
import com.socks.library.KLog;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import okhttp3.OkHttpClient;

public class UserBaseInitDo {

    public static void init(Application application) {


        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                //tbs内核下载完成回调
                KLog.d("onDownloadFinish "+i);
            }

            @Override
            public void onInstallFinish(int i) {
                //内核安装完成回调，
                KLog.d("onInstallFinish "+i);
            }

            @Override
            public void onDownloadProgress(int i) {
                //下载进度监听
                KLog.d("onDownloadProgress "+i);
            }
        });

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                KLog.d(" onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(application.getApplicationContext(),  cb);

// Adding an Network Interceptor for Debugging purpose :
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(application.getApplicationContext(),okHttpClient);




        boolean homeOk = ServiceFactory.getInstance().getHomeService().isHomeOk();
        Dlog.d("homeok "+homeOk);

        NoSqlUtils.init(application);
        ServiceFactory.getInstance().setUserService(new UserServie());

    }
}
