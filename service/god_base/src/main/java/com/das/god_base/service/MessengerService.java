package com.das.god_base.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;


import com.google.gson.Gson;


public class MessengerService extends Service {

    public static final int GET_DATA = 1;
    public static final int SET_DATA = 2;


    Messenger messenger = new Messenger(new ServiceHandler());
    Messenger replyMessenger; //向客服端返回信息
    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    class ServiceHandler  extends Handler {
        @Override
        public void handleMessage(Message msg) {
            replyMessenger = msg.replyTo;
            switch (msg.what) {
                case GET_DATA:
                    //客服端向服务端请求数据


                    break;
                case SET_DATA:
                    //客服端向服务端请求更新数据
                    String refresh = msg.getData().getString("refresh");


                    break;
            }
        }
    }



}
