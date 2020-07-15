package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.das.god_base.utils.AsyncUtls;
import com.das.god_base.utils.FileUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.LoginActivity;
import com.das.user.R;
import com.das.user.databinding.UserActivityInviteUserBinding;
import com.das.user.network.DasService;
import com.das.user.utils.FileProviderUtils;
import com.socks.library.KLog;
import com.xuexiang.xqrcode.XQRCode;

import java.io.File;

public class InviteUserActivity extends BaseActivity {

    UserActivityInviteUserBinding viewDataBinding;


    @Override
    protected void initLazyAction() {
        setTopTitle("邀请好友");
        setLeftBack();
        Bitmap build = XQRCode.newQRCodeBuilder(DasService.BASEURL+"/transition/index.html").build();
        viewDataBinding.ivCode.setImageBitmap(build);

        viewDataBinding.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShare(build);
            }
        });
    }

    private void doShare(Bitmap share_bitmap) {

        AsyncUtls.asyncTask(new AsyncUtls.AsyncTask<String>() {
            @Override
            public String doTask() {
                String s = FileUtils.save_download_image(InviteUserActivity.this, share_bitmap, "das_share.jpg");
                return s;
            }
        }, new AsyncUtls.AsyncCallBack<String>() {
            @Override
            public void onResult(String result) {
                    realShare(result);
            }

            @Override
            public void onError(Throwable e) {

            }
        });


        //先本地保存





    }

    private void realShare(String path) {
        KLog.e("realShare "+path);
        Intent intent=new Intent(Intent.ACTION_SEND);
        Uri uri= FileProviderUtils.getUriForFile(this,new File(path));
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent,"share"));
    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_invite_user);

    }
}