package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;

import com.das.god_base.utils.BaseViewDataUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.R;
import com.das.user.RouteUtils;
import com.das.user.databinding.UserActivityInviteUserBinding;
import com.das.user.databinding.UserActivitySetBinding;

public class SetActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initLazyAction() {

        viewDataBinding.clClearCache.setOnClickListener(this);
        viewDataBinding.clAbout.setOnClickListener(this);
        viewDataBinding.clUpdatePwd.setOnClickListener(this);

        BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clUpdatePwd, "修改密码", null);
        BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clServerXy, "服务条款与隐私协议", null);
        BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clClearCache, "清除缓存", null);
        BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clAbout, "关于我们", null);

    }

    UserActivitySetBinding viewDataBinding;

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_set);

        setTopTitle("设置");
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.cl_clear_cache){
            showBaseDialog("您确定要清除缓存吗？", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return;
        }

        if (v.getId() == R.id.cl_about) {
            RouteUtils.openActivity(this, AboutActivity.class);
            return;
        }

        if (v.getId() == R.id.cl_update_pwd) {
            RouteUtils.openActivity(this, UpdatePwdActivity.class);
            return;
        }
    }
}