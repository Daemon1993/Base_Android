package com.das.god_base.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.das.god_base.R;
import com.das.god_base.utils.StatusUtils;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import androidx.annotation.LayoutRes;

public abstract class BaseActivity extends RxAppCompatActivity {


    private DBaseDialog dBaseDialog;
    public String Tag = "";

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
            das_right_title_text.setOnClickListener(onClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void back() {
        finish();
    }
}
