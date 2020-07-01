package com.das.god_base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;



/**
 * Created by liubo on 2018/10/18.
 */

public class ToastUtils {
    /**
     * 全局Toast对象
     */
    private static Toast mToast;
    //创建可以处理main线程的Handler对象
    private static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            //先取消正在显示的Toast
            if (mToast != null) {
                mToast.cancel();
            }
            String message = (String) msg.obj;
            mToast = Toast.makeText(BaseInitGod.application, message, msg.arg2);
            mToast.show();
        }
    };
    public static void toast(String message, int duration) {
        //将Toast需要的参数发送到消息队列
        handler.sendMessage(handler.obtainMessage(0, 0, duration, message));
    }
    public static void toast(String message) {
        if (!TextUtils.isEmpty(message)) {
            toast(message, Toast.LENGTH_SHORT);
        }
    }
    public static void toast1(String message) {
        if (!TextUtils.isEmpty(message)) {
            toast(message, Toast.LENGTH_LONG);
        }
    }
}
