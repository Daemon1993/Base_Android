package com.das.god_base.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


import com.das.god_base.PermissionsUtils;
import com.das.god_base.R;
import com.das.god_base.ToastUtils;
import com.das.god_base.databinding.ActivityWebviewBinding;
import com.das.god_base.view.BaseActivity;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.socks.library.KLog;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;


public class WebViewActivity extends BaseActivity {

    public static final String LINK_URL = "link_url";

    private long startTime;

    private String url;
    private String link_url;
    private boolean isht;
    private String temp_fn;


    public static void openActivity(Context context, String link_url) {


        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(LINK_URL, link_url);

        context.startActivity(intent);


    }


    private String mobile;
    private String last_tokens;


    public static final int REQUEST_CODE_CHOOSE = 10555;

    ActivityWebviewBinding mViewDataBinding;


    private String jsNeedBean_json;

    @Override
    protected void initLazyAction() {

    }

    @Override
    public void onCreateNew(Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);

        temp_fn = "";
        // Bind to the service
        bindService(new Intent(this, MessengerService.class), serviceConnection,
                Context.BIND_AUTO_CREATE);
        startTime = System.currentTimeMillis();

        IX5WebViewExtension x5WebViewExtension = mViewDataBinding.webview.getX5WebViewExtension();
        KLog.e("   " + x5WebViewExtension);


        url=getIntent().getStringExtra(LINK_URL);

        setUp();


    }


    private void setUp() {
        WebSettings webSettings = mViewDataBinding.webview.getSettings();
        mViewDataBinding.webview.setWebChromeClient(webChromeClient);
        mViewDataBinding.webview.setWebViewClient(webViewClient);
        webSettings.setDomStorageEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);

        //location add
        webSettings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(dir);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //add end
        JsInteration jsInteration = new JsInteration();
        mViewDataBinding.webview.addJavascriptInterface(jsInteration, "android");


        webSettings.setJavaScriptEnabled(true);//允许使用js
        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        mViewDataBinding.webview.loadUrl(url);
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            KLog.e("over " + url + "   time " + (System.currentTimeMillis() - startTime));
//

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载

            KLog.e("---onPageStarted--");

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://www.google.com/")) {
                ToastUtils.toast("国内不能访问google,拦截该url");
                return true;//表示我已经处理过了
            }
            if (url.startsWith("mailto:") || url.startsWith("geo:")) {
                String other = url.substring(url.lastIndexOf(":") + 1);
                if (other == null || other.length() < 5) {
                    ToastUtils.toast("无效的邮箱");
                } else {
                    Intent i = new Intent(Intent.ACTION_SENDTO);
                    i.setData(Uri.parse("mailto:" + other));
                    i.setType("message/rfc822"); // 真机上使用这行
                    i.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{other});
                    startActivity(Intent.createChooser(i,
                            "Select email application."));
                }
            }

            if ((url.startsWith("tel:"))) {
                mobile = url.substring(url.lastIndexOf(":") + 1);
                if (mobile == null || mobile.length() < 5) {
                    Toast.makeText(WebViewActivity.this, "无效号码", Toast.LENGTH_SHORT).show();
                    return true;
                } else {

                    return true;
                }
            }

            view.loadUrl(url);
            return true;
        }
    };


    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
//        @Override
//        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
//            AlertDialog.Builder localBuilder = new AlertDialog.Builder(WebViewActivity.this);
//            localBuilder.setMessage(message).setPositiveButton("确定", null);
//            localBuilder.setCancelable(false);
//            localBuilder.create().show();
//            //注意:
//            //必须要这一句代码:result.confirm()表示:
//            //处理结果为确定状态同时唤醒WebCore线程
//            //否则不能继续点击按钮
//            result.confirm();
//            return true;
//        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

            KLog.e("onReceivedTitle");


        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }

        //location add
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissionsCallback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d("webview", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return super.onConsoleMessage(consoleMessage);

        }

        //add end
    };
    Handler handler = new Handler(Looper.getMainLooper());


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


//        finish();
//
        back();
        return true;
//        if (keyCode == KeyEvent.KEYCODE_BACK && mViewDataBinding.webview.canGoBack()) {
//            mViewDataBinding.webview.goBack();
//            return true;
//        } else {
//            onBackPressed();
////            mViewDataBinding.webview.loadUrl("javascript:goback()");
//        }
//        return super.onKeyDown(keyCode, event);
    }

    //
    @Override
    public void back() {
        super.back();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("-------   ", "javascript:goback()");
//                mViewDataBinding.webview.loadUrl("javascript:goback()");
//            }
//        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //H5调用原生方法
    public class JsInteration extends Object {
        @JavascriptInterface
        public void requireToken1(String func) {
            Log.e("AAA", "------requireToken----- " + func);


        }


        @JavascriptInterface
        public void back() {
            finish();
        }

        @JavascriptInterface
        public void web2Native(String request) {
            //request  json 通过类型来判断
            temp_fn = "";
            Log.d("AAAA", request);

            Web2NativeBean web2Native = null;
            try {
                web2Native = new Gson().fromJson(request, Web2NativeBean.class);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                return;
            }

            if (web2Native.isRefresh()) {
                actionRefreshDo(web2Native);
                return;
            }


            if (!TextUtils.isEmpty(web2Native.getFn())) {
                temp_fn = web2Native.getFn();
            }


        }

    }

    private void actionRefreshDo(Web2NativeBean web2Native) {

        setData("refresh", new Gson().toJson(web2Native));

    }


    /**
     * native返回原生
     *
     * @param response
     */
    private void actionNative2Web(String response) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(temp_fn)) {
                    temp_fn = "native2Web";
                }
                Log.e("-------   ", "javascript:" + temp_fn + "('" + response + "')");
                mViewDataBinding.webview.loadUrl("javascript:" + temp_fn + "('" + response + "')");
                temp_fn = "";
            }
        });

    }

    @Override
    protected void onDestroy() {

        if (mViewDataBinding.webview != null) {

            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = mViewDataBinding.webview.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mViewDataBinding.webview);
            }

            mViewDataBinding.webview.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            CookieSyncManager.createInstance(this);
            CookieManager.getInstance().removeAllCookie();
            mViewDataBinding.webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            mViewDataBinding.webview.clearCache(true);


            mViewDataBinding.webview.getSettings().setJavaScriptEnabled(false);
            mViewDataBinding.webview.clearHistory();
            mViewDataBinding.webview.clearView();
            mViewDataBinding.webview.removeAllViews();
            mViewDataBinding.webview.destroy();

        }

        if (mBound) {
            unbindService(serviceConnection);
            mBound = false;
        }

        handler1.removeCallbacksAndMessages(null);

        super.onDestroy();
        System.exit(0);


    }


    Messenger messenger;
    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.GET_DATA:
                    String type = (String) msg.getData().get("type");
                    KLog.d("---"+type);

                    break;
            }
        }
    };

    Messenger messengerReply = new Messenger(handler1);


    boolean mBound;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("AAA", "onServiceConnected");
            messenger = new Messenger(service);
            mBound = true;

            getData();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("AAA", "onServiceDisconnected");
            messenger = null;
            mBound = false;
        }

    };

    private void getData() {
        if (!mBound) return;
        Message message = Message.obtain(null, MessengerService.GET_DATA, 0, 0);
        //用于服务端应答
        message.replyTo = messengerReply;
        sendMessage(message);
    }

    private void setData(String key, String str) {
        if (!mBound) return;
        Message message = Message.obtain(null, MessengerService.SET_DATA, 0, 0);
        Bundle bundle = new Bundle();
        bundle.putString(key, str);
        message.setData(bundle);
        sendMessage(message);
    }

    private void sendMessage(Message message) {
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
