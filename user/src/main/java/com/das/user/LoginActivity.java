package com.das.user;

import androidx.databinding.DataBindingUtil;
import io.reactivex.rxjava3.functions.Consumer;
import okhttp3.ResponseBody;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.das.componentbase.router.Router_Pools;
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

@Route(path = Router_Pools.User_Login_Activity)
public class LoginActivity extends BaseActivity {

    private boolean user_ok;
    private boolean pwd_ok;

    public static void openActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
//        activity.finish();
    }

    UserActivityLoginBinding viewDataBinding;

    @Override
    protected void initLazyAction() {

        viewDataBinding.etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        UserLogin userLogin = NoSqlUtils.getObject(NoSqlUtils.login_user);
        if(userLogin==null){
            viewDataBinding.etPwd.setText("");
            viewDataBinding.etPwd.setText("");
            return;
        }

        String password = userLogin.getPassword();
        String userName = userLogin.getUserName();

        if(!TextUtils.isEmpty(password)){
            viewDataBinding.etPwd.setText(password);
        }

        if(!TextUtils.isEmpty(userName)) {
            viewDataBinding.etUser.setText(userName);
        }

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
                .subscribe(new DObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {

                        NoSqlUtils.addObject(NoSqlUtils.login_user, userLogin);
                        NoSqlUtils.addObject(NoSqlUtils.login_response, loginResponse);


                        MainActivity.openActivity(LoginActivity.this);
                        finish();
                    }
                });

    }
}