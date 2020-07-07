package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.das.god_base.view.BaseActivity;
import com.das.user.LoginActivity;
import com.das.user.R;
import com.das.user.databinding.UserActivityInviteUserBinding;
import com.xuexiang.xqrcode.XQRCode;

public class InviteUserActivity extends BaseActivity {

    UserActivityInviteUserBinding viewDataBinding;


    @Override
    protected void initLazyAction() {
        setTopTitle("邀请好友");
        setLeftBack();
        Bitmap build = XQRCode.newQRCodeBuilder("Daemon").build();
        viewDataBinding.ivCode.setImageBitmap(build);
    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_invite_user);

    }
}