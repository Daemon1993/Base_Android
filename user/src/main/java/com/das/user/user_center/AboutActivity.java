package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.das.god_base.ToastUtils;
import com.das.god_base.network.BaseResponseResult;
import com.das.god_base.network.DObserver;
import com.das.god_base.network.JsDownloadListener;
import com.das.god_base.utils.AppUtils;
import com.das.god_base.utils.BaseViewDataUtils;
import com.das.god_base.utils.FileUtils;
import com.das.god_base.view.BaseActivity;
import com.das.god_base.view.LiveEventBusProprs;
import com.das.user.R;
import com.das.user.databinding.UserActivityAboutBinding;
import com.das.user.network.DasService;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.response.VersionAppResponse;
import com.das.user.utils.LinDownloadAPk;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.socks.library.KLog;

import java.io.IOException;
import java.io.InputStream;

public class AboutActivity extends BaseActivity {



    @Override
    protected void initLazyAction() {

        setTopTitle("关于我们");
        String versionName = AppUtils.getVersionName(this);

        BaseViewDataUtils.
                layout_base_textview_style1DataAction(viewDataBinding.clVersion,
                        "应用版本号",versionName);

        viewDataBinding.clVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                queryUpdate();
            }
        });

    }

    private void queryUpdate() {

        UserRetrofitHandler.getInstance().createDasService().GetMaximumVersion("android")
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(new DObserver<VersionAppResponse>() {
                    @Override
                    public void onSuccess(VersionAppResponse response) {
                        int versionCode = AppUtils.getVersionCode(AboutActivity.this);
//                        if(response.getData().getMinor()>versionCode){
                            needUpdate(response.getData().getVersionName(), DasService.BASEURL+response.getData().getMapPath());
//                        }
                    }
                });

    }

    private void needUpdate(String versionName, String url_path) {

        showBaseDialog(versionName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRealUpdate(url_path);
            }
        });
    }

    private void doRealUpdate(String url_path) {
        String path = FileUtils.getDirPath(getApplicationContext()) + "/last_v1r1.apk";

        showProgressLoading("下载中");

        AndroidNetworking.download(url_path, FileUtils.getDirPath(getApplicationContext()),"last_v1r1.apk")
                .setTag("doRealUpdate")
                .setPriority(Priority.HIGH)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {


                        updateProgress(bytesDownloaded*100/totalBytes);
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion

                        hideLoading();

                        LinDownloadAPk.installApk(getApplicationContext(),path);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        hideLoading();
                        ToastUtils.toast(error.getMessage()+"");
                    }
                });
    }



    UserActivityAboutBinding viewDataBinding;
    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

         viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_about);



    }
}