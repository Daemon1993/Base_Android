package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.das.god_base.ToastUtils;
import com.das.god_base.service.WebViewActivity;
import com.das.god_base.utils.AsyncUtls;
import com.das.god_base.utils.BaseViewDataUtils;
import com.das.god_base.utils.FileUtils;
import com.das.god_base.view.BaseActivity;
import com.das.god_base.view.LiveEventBusProprs;
import com.das.user.LoginActivity;
import com.das.user.R;
import com.das.user.RouteUtils;
import com.das.user.databinding.UserActivityInviteUserBinding;
import com.das.user.databinding.UserActivitySetBinding;
import com.das.user.network.DasService;
import com.das.user.nosql.NoSqlUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.socks.library.KLog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Set;

public class SetActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initLazyAction() {

        viewDataBinding.clClearCache.setOnClickListener(this);
        viewDataBinding.clAbout.setOnClickListener(this);
        viewDataBinding.clUpdatePwd.setOnClickListener(this);
        viewDataBinding.tvLogout.setOnClickListener(this);

        BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clUpdatePwd, "修改密码", null);
        BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clServerXy, "服务条款与隐私协议", null);

        BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clAbout, "关于我们", null);

        viewDataBinding.clServerXy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.openActivity(SetActivity.this, DasService.BASEURL+"/transition/provisions.html");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        showCahceDataView();
    }

    private void showCahceDataView() {
        KLog.d("  showCahceDataView ");
        AsyncUtls.asyncTask(new AsyncUtls.AsyncTask<String>() {
            @Override
            public String doTask() {
                long l = FileUtils.queryAllCacheFileSize(SetActivity.this);
                double distanceValue = l/(1024*1024f);
                DecimalFormat decimalFormat =new DecimalFormat("0.00");
                String distanceString = decimalFormat.format(distanceValue) + "M";//form
                return distanceString;
            }
        }, new AsyncUtls.AsyncCallBack<String>() {
            @Override
            public void onResult(String result) {
                BaseViewDataUtils.layout_base_textview_style1DataAction(viewDataBinding.clClearCache, "清除缓存", result);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void doDeleteCacher() {
        
        AsyncUtls.asyncTask(new AsyncUtls.AsyncTask<Object>() {
            @Override
            public Object doTask() {

                FileUtils.clearLocalCache(SetActivity.this);
                return "清除完毕";
            }
        }, new AsyncUtls.AsyncCallBack<Object>() {
            @Override
            public void onResult(Object result) {
                ToastUtils.toast(result.toString());
                showCahceDataView();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
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
                    doDeleteCacher();
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

        if (v.getId() == R.id.tv_logout) {
            NoSqlUtils.loginOut();
            LoginActivity.openActivity(this);
            return;
        }
    }
}