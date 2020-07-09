package com.das.god_base.view;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.das.god_base.R;
import com.das.god_base.life.ActivityManager;
import com.das.god_base.utils.StatusUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.socks.library.KLog;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import java.util.Stack;

import androidx.lifecycle.Observer;

public abstract class BaseActivity extends RxAppCompatActivity {


    private DBaseDialog dBaseDialog;
    public String Tag = "";
    private boolean isDestory;
    private BaseLoadingDialog baseLoadingDialog;
    private BaseProgressDialog mProgressDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏

        Tag = getClass().getSimpleName();
        onCreateNew(savedInstanceState);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                initLazyAction();
            }
        });

        setStatusBar();

        LiveEventBus
                .get(LiveEventBusProprs.OVER_Refresh, String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        doRefresh();
                    }
                });
    }

    private void doRefresh() {
        Stack<Activity> allActivityStacks = ActivityManager.getInstance().getAllActivityStacks();
        for (Activity activity : allActivityStacks) {
            if (this == activity) {
                KLog.e("----找到自己 重新绘制" + Tag);
                finish();
                startActivity(getIntent());
            }
        }
    }

    protected abstract void initLazyAction();


    public void setStatusBar() {

        StatusUtils.setStatusBarLightMode(this, Color.WHITE);

    }

    protected abstract void onCreateNew(Bundle savedInstanceState);


    public void showBaseDialog(String text, String left, String right, View.OnClickListener leftc, View.OnClickListener rightc) {

        if (dBaseDialog == null) {
            dBaseDialog = new DBaseDialog();
            dBaseDialog.setLeftListener(leftc);
            dBaseDialog.setRightListener(rightc);
        }

        if (dBaseDialog != null
                && dBaseDialog.getDialog() != null
                && dBaseDialog.getDialog().isShowing()
                && !dBaseDialog.isRemoving()) {

            dBaseDialog.setShowContent(text, left, right);
            return;
        }
        dBaseDialog.setShowContent(text, left, right);
        dBaseDialog.show(getSupportFragmentManager(), "dbase" + Tag);
    }


    public void showBaseDialog(String text, View.OnClickListener leftc, View.OnClickListener rightc) {
        showBaseDialog(text, "", "", leftc, rightc);
    }

    public void showLoading() {
        showLoading("上传中");
    }

    public void showLoading(String title) {
//        hideLoading();
        if (isDestory) {
            return;
        }

        if (baseLoadingDialog == null) {
            baseLoadingDialog = new BaseLoadingDialog(this, R.style.BaseDialog);
            baseLoadingDialog.setCanceledOnTouchOutside(false);
        }

        baseLoadingDialog.setLoadingTitle(title+"");
        if (!baseLoadingDialog.isShowing()) {

            baseLoadingDialog.show();
        }

    }

    public void updateProgress(long l) {
        if(mProgressDialog1==null){
            return;
        }

        if(mProgressDialog1.isShowing()){
            mProgressDialog1.updateProgress((int) l);
        }
    }

    public void showProgressLoading(String msg){
        if (mProgressDialog1 == null) {
            mProgressDialog1 = new BaseProgressDialog(this, R.style.BaseDialog);
            mProgressDialog1.setCanceledOnTouchOutside(false);
        }


        mProgressDialog1.setLoadingTitle(msg);
        if (!mProgressDialog1.isShowing()) {

            mProgressDialog1.show();
        }
    }

    public void showProgressLoading(){
        showProgressLoading("");
    }

    public void hideLoading() {

        if (baseLoadingDialog != null && baseLoadingDialog.isShowing()) {
            baseLoadingDialog.cancel();
        }
        if (mProgressDialog1 != null && mProgressDialog1.isShowing()) {
            mProgressDialog1.cancel();
        }
    }

    public void dismissDBaseDialog() {
        if (dBaseDialog == null || dBaseDialog.isResumed()) {
            return;
        }
        dBaseDialog.dismiss();
    }


    public void setLeftBack() {
        findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }
    public void setTopTitle(String title){
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title+"");
    }

    public void setRightAction(String name,View.OnClickListener onClickListener) {
        try {
            TextView das_right_title_text = findViewById(R.id.tv_right);
            das_right_title_text.setText(name+"");
            das_right_title_text.setOnClickListener(onClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void back() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        hideLoading();

        isDestory = true;
        baseLoadingDialog = null;
        mProgressDialog1 = null;


    }
}
