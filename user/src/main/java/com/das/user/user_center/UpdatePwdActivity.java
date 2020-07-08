package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.das.god_base.view.BaseActivity;
import com.das.user.R;

public class UpdatePwdActivity extends BaseActivity {



    @Override
    protected void initLazyAction() {

    }

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {
        setContentView(R.layout.user_activity_update_pwd);
    }
}