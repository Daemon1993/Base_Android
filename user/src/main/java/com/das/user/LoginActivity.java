package com.das.user;

import androidx.databinding.DataBindingUtil;
import io.reactivex.rxjava3.functions.Consumer;
import okhttp3.ResponseBody;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.das.god_base.network.BaseResponseResult;
import com.das.god_base.network.DObserver;
import com.das.god_base.utils.BaseViewDataUtils;
import com.das.god_base.utils.Object2MapUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.databinding.UserActivityLoginBinding;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.request.UserLogin;
import com.das.user.network.response.LoginResponse;
import com.das.user.nosql.NoSqlUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private boolean user_ok;
    private boolean pwd_ok;

    public static void openActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    UserActivityLoginBinding viewDataBinding;

    @Override
    protected void initLazyAction() {

    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_login);

        viewDataBinding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 showBaseDialog("Daemon",null,null);
                doLogin();
            }
        });

        updateEnableButton();

        viewDataBinding.etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    user_ok = true;
                } else {
                    user_ok = false;
                }
                updateEnableButton();
            }
        });

        viewDataBinding.etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    pwd_ok = true;
                } else {
                    pwd_ok = false;
                }
                updateEnableButton();
            }
        });

    }

    private void updateEnableButton() {
        viewDataBinding.btLogin.setEnabled(user_ok && pwd_ok);
    }

    private void doLogin() {
        String name, pwd;
        try {
            name = viewDataBinding.etUser.getText().toString().trim();
            pwd = viewDataBinding.etPwd.getText().toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            BaseViewDataUtils.toastErrorMsg(e);
            return;
        }

        UserLogin userLogin = new UserLogin(name, pwd);
        UserRetrofitHandler.getInstance().createDasService().login(Object2MapUtils.praseObject2Map(userLogin))
        .compose(bindToLifecycle())
        .subscribe(new DObserver<LoginResponse>(){
            @Override
            public void onSuccess(LoginResponse loginResponse) {

                NoSqlUtils.addObject(NoSqlUtils.login_user,userLogin);
                NoSqlUtils.addObject(NoSqlUtils.login_response,loginResponse);


                MainActivity.openActivity(LoginActivity.this);
                finish();
            }
        });

    }
}