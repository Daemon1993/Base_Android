package com.das.componentbase.interceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.das.componentbase.router.Router_Pools;

@Interceptor(priority = 8,name = "用户登录拦截器")
public class UserInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        if(TextUtils.equals(postcard.getPath(), Router_Pools.User_MainActivity)){
            Log.d("UserInterceptor","看拦截判断");
            callback.onContinue(postcard);
            return;
        }
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

    }
}
