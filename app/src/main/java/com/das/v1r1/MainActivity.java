package com.das.v1r1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.das.componentbase.router.Router_Pools;
import com.das.v1r1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding viewDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

         viewDataBinding.btDo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ARouter.getInstance().build(Router_Pools.User_SplashActivity).navigation();
             }
         });

    }
}