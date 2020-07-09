package com.das.user.network;

import android.app.Activity;


import com.das.god_base.life.ActivityManager;
import com.das.god_base.utils.Object2MapUtils;
import com.das.god_base.view.LiveEventBusProprs;
import com.das.user.LoginActivity;
import com.das.user.network.request.UserLogin;
import com.das.user.network.response.LoginResponse;
import com.das.user.nosql.NoSqlUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.socks.library.KLog;

import java.io.IOException;


import androidx.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

/**
 * Created by liubo on 2018/10/29.
 */

public class TokenAuthenticator implements Authenticator {

    public static long refreshTokenTime = 0;


    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (responseCount(response) >= 3) {
            return null; // If we've failed 3 times, give up.
        }


        LoginResponse loginResponse = NoSqlUtils.getObject(NoSqlUtils.login_response);
        KLog.e("Thread name" + Thread.currentThread().getName() + " " + " -》 " + loginResponse.getData().getRefresh_token());
//        XLog.e("TokenAuthenticator old headers " + response.request().headers());

        UserLogin userLogin = new UserLogin(loginResponse.getData().getRefresh_token());
        userLogin.setGrant_type("refresh_token");

        Call<LoginResponse> loginResponseCall = UserRetrofitHandler.getInstance().createDasService().
                refreshToken(Object2MapUtils.praseObject2Map(userLogin));
        LoginResponse body  = loginResponseCall.execute().body();
        KLog.d("   "+body.retCode+" ");
        if (body!=null && body.retCode == 0) {

            NoSqlUtils.addObject(NoSqlUtils.login_response, loginResponse);

            if (System.currentTimeMillis() - refreshTokenTime >= (1000 * 100)) {
                KLog.e("100S 内 只刷新一次 " + refreshTokenTime);
//                EventBus.getDefault().post(new RefreshActivityBean());
                LiveEventBus
                        .get(LiveEventBusProprs.OVER_Refresh)
                        .post("gg_refresh");

                refreshTokenTime = System.currentTimeMillis();
            }
        } else {
            KLog.e(body.message + "");
            go2Login();
        }

        return response.request().newBuilder()
                .addHeader("Authorization", "Bearer " + loginResponse.getData().getAccess_token())
                .build();

    }

    public static void go2Login() {
        try {
            Activity currentActivity = ActivityManager.getInstance().getCurrentActivity();
            LoginActivity.openActivity(currentActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }

        return result;
    }

}
