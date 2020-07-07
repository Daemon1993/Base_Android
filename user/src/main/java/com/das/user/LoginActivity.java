package com.das.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.das.god_base.view.BaseActivity;
import com.das.user.databinding.UserActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    public static void openActivity(Context context){
        context.startActivity(new Intent(context,LoginActivity.class));
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
                 MainActivity.openActivity(LoginActivity.this);
             }
         });
    }
}