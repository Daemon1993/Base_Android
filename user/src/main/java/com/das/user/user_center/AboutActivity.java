package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.Application;
import android.os.Bundle;

import com.das.god_base.utils.AppUtils;
import com.das.god_base.utils.BaseViewDataUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.R;
import com.das.user.databinding.UserActivityAboutBinding;

public class AboutActivity extends BaseActivity {



    @Override
    protected void initLazyAction() {

        String versionName = AppUtils.getVersionName(this);

        BaseViewDataUtils.
                layout_base_textview_style1DataAction(viewDataBinding.clVersion,
                        "应用版本号",versionName);

    }
    UserActivityAboutBinding viewDataBinding;
    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

         viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_about);



    }
}