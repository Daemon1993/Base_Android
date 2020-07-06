package com.das.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.das.god_base.view.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        setContentView(R.layout.user_activity_login);
    }
}