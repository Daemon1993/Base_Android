package com.das.god_base.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import androidx.annotation.LayoutRes;

public abstract class BaseActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏

        onCreateNew(savedInstanceState);
    }




    protected abstract void onCreateNew(Bundle savedInstanceState);

}
