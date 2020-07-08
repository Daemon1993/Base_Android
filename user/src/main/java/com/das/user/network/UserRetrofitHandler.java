package com.das.user.network;

import com.das.user.network.convert.DGsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class UserRetrofitHandler {

    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private static final String TAG = "RetrofitHandler";


    private Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static UserRetrofitHandler mRetrofitHandler;


    private UserRetrofitHandler() {
        initRetrofit();
    }

    public static synchronized UserRetrofitHandler getInstance() {
        if (mRetrofitHandler == null) {
            synchronized (UserRetrofitHandler.class) {
                if (mRetrofitHandler == null) {
                    mRetrofitHandler = new UserRetrofitHandler();
                }
            }
        }
        return mRetrofitHandler;
    }

    /**
     * 获取 Retrofit
     */
    private void initRetrofit() {
        initOkHttpClient();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(DasService.BASEURL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(DGsonConverterFactory.create())
                .build();

    }





    /**
     * 单例模式获取 OkHttpClient
     */
    private  void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (UserRetrofitHandler.class) {
                if (mOkHttpClient == null) {

                    mOkHttpClient = new OkHttpClient.Builder()
                            //添加log拦截器
                            .addInterceptor(new UserLoggingInterceptor())
                            //设置连接超时时间
                            .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)//连接超时时间
                            .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)//设置写操作超时时间
                            .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)//设置读操作超时时间
                            //默认重试一次
                            .retryOnConnectionFailure(true)
                            .build();
                }
            }
        }
    }


    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public DasService createDasService() {
        return mRetrofit.create(DasService.class);
    }
}
