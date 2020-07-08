package com.das.god_base.network;

import android.text.TextUtils;
import android.util.Log;

import com.das.god_base.ToastUtils;
import com.google.gson.JsonParseException;
import com.socks.library.KLog;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.adapter.rxjava3.HttpException;


public  abstract  class DObserver<T> implements Observer<T> {



    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
        onFinish();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        KLog.e("Retrofit DObserver" , e.getMessage());
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        }else if(e instanceof ServerException){
            onFail(e.getMessage());
        } else if (e instanceof SSLHandshakeException) {
            onException(ExceptionReason.SSL_ERROR);

        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
        onFinish();

    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     *
     */
    public void onFail(String message) {
        if(TextUtils.isEmpty(message)){
            message="未知异常 数据格式异常";
        }
        ToastUtils.toast(message);
    }

    public void onFinish(){}


    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.toast( "connect_error");
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.toast("connect_timeout");
                break;

            case BAD_NETWORK:
                ToastUtils.toast( "bad_network");
                break;

            case PARSE_ERROR:
                ToastUtils.toast( "parse_error");
                break;
            case SSL_ERROR:
                ToastUtils.toast( "SSL_ERROR");
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtils.toast( "unknown_error");
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,

        SSL_ERROR,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }


}
