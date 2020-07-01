package com.das.god_base.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggingInterceptor  implements Interceptor {
    private static final String TAG = LoggingInterceptor.class.getName();

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        String content = responseBody.string();
        Log.e(TAG, "----------Request Start----------------");
        Log.e(TAG, "| " + request.toString() + request.headers().toString());
        Log.e(TAG, "| Response:" + content);
        Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
        return response;
    }
}