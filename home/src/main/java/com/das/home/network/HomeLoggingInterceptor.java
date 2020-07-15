package com.das.home.network;


import com.das.home.BuildConfig;
import com.das.home.out_service.OutServiceUtils;
import com.socks.library.KLog;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HomeLoggingInterceptor implements Interceptor {
    private static final String TAG = HomeLoggingInterceptor.class.getName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        long startTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        if (BuildConfig.DEBUG_Home) {
            try {
                final Request copy = original.newBuilder().build();
                if (copy.body() != null) {
                    RequestBody body = copy.body();
                    if (body instanceof FormBody) {
                        FormBody body1 = (FormBody) body;

                        for (int i = 0; i < body1.size(); i++) {
                            sb.append(body1.encodedName(i) + "=" + body1.encodedValue(i) + ",");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Request.Builder requestBuilder = original.newBuilder();


        // Request customization: add request headers
        requestBuilder.addHeader("Authorization", OutServiceUtils.getUserAuthToken());


        Request request = requestBuilder.build();

        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        String content = responseBody.string();

        KLog.d(TAG, "----------Request Start----------------");
        KLog.d(TAG, request.url() + "   " + sb.toString());
//        KLog.d(TAG, "| Response:" + content);
        KLog.d(TAG, "----------Request End:" + duration + "毫秒----------");
        return response;
    }
}