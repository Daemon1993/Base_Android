package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;

import com.das.god_base.images.DImageUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.MainActivity;
import com.das.user.R;
import com.das.user.databinding.UserActivitySplash2Binding;
import com.das.user.nosql.NoSqlUtils;
import com.socks.library.KLog;

import java.io.File;

public class Splash2Activity extends BaseActivity {

    UserActivitySplash2Binding viewDataBinding;

    @Override
    protected void initLazyAction() {


        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                MainActivity.openActivity(Splash2Activity.this);
                finish();

            }
        }, 1500);

    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_splash2);

        String img_path = NoSqlUtils.getObject(NoSqlUtils.splash_2_image);

        KLog.d("img_path " + img_path);

        DImageUtils.getBitmap_W_H(this, img_path, new DImageUtils.ImageActionCallBack1() {
            @Override
            public void doAction(Bitmap bitmap) {

                ViewGroup.LayoutParams layoutParams = viewDataBinding.ivSplash.getLayoutParams();
                layoutParams.height=bitmap.getHeight();
                layoutParams.width=bitmap.getWidth();

                viewDataBinding.ivSplash.setImageBitmap(bitmap);

            }
        });


    }
}