package com.das.god_base.utils;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AsyncUtls {

    public interface AsyncCallBack<T>{
        void onResult(T result);
        void onError(Throwable e);
    }

    public interface AsyncTask<T>{
        T doTask();
    }


    public static <T> void  asyncTask( Observable<T> observable,
                                      final AsyncCallBack<T> asyncCallBack){

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        asyncCallBack.onResult(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        asyncCallBack.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static <T> void  asyncTask( AsyncTask<T> asyncTask,
                                       final AsyncCallBack<T> asyncCallBack){
        Observable<T> stringObservable = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                T t = asyncTask.doTask();
                emitter.onNext(t);
            }
        });

        stringObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        asyncCallBack.onResult(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        asyncCallBack.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
