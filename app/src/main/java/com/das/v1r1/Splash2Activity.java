package com.das.v1r1;

import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.das.componentbase.ServiceFactory;
import com.das.componentbase.router.Router_Pools;
import com.das.god_base.images.DImageUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.MainActivity;


import com.das.user.RouteUtils;
import com.das.user.nosql.NoSqlUtils;
import com.das.v1r1.databinding.ActivitySplash2Binding;
import com.socks.library.KLog;

public class Splash2Activity extends BaseActivity {

    ActivitySplash2Binding viewDataBinding;

    @Override
    protected void initLazyAction() {


        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                RouteUtils.openActivity(Splash2Activity.this,HomeActivity.class);
//                ARouter.getInstance().build(Router_Pools.User_MainActivity).navigation();

                finish();

            }
        }, 1500);

    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash2);

        String img_path = ServiceFactory.getInstance().getUserService().getSplashImagePath();

        KLog.d("img_path " + img_path);

        DImageUtils.getBitmap_W_H(this, img_path, new DImageUtils.ImageActionCallBack1() {
            @Override
            public void doAction(Bitmap bitmap) {

                ViewGroup.LayoutParams layoutParams = viewDataBinding.ivSplash.getLayoutParams();
                layoutParams.height = bitmap.getHeight();
                layoutParams.width = bitmap.getWidth();

                viewDataBinding.ivSplash.setImageBitmap(bitmap);

            }
        });


    }
}