package com.das.user.user_center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.das.god_base.ToastUtils;
import com.das.god_base.network.BaseResponseResult;
import com.das.god_base.network.DObserver;
import com.das.god_base.utils.BaseViewDataUtils;
import com.das.god_base.view.BaseActivity;
import com.das.user.R;
import com.das.user.UserApplication;
import com.das.user.databinding.UserActivityUpdatePwdBinding;
import com.das.user.network.UserRetrofitHandler;
import com.das.user.network.request.UpdatePwd;
import com.das.user.network.request.UserLogin;
import com.das.user.nosql.NoSqlUtils;

public class UpdatePwdActivity extends BaseActivity {


    @Override
    protected void initLazyAction() {


        setLeftBack();

        setTopTitle("修改密码");
        BaseViewDataUtils.layout_base_edittext_style1DataAction(viewDataBinding.clOldPwd, "旧密码", null);
        BaseViewDataUtils.layout_base_edittext_style1DataAction(viewDataBinding.clNewPwd1, "新密码", null);
        BaseViewDataUtils.layout_base_edittext_style1DataAction(viewDataBinding.clNewPwd2, "确认密码", null);

        setRightAction("完成", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdate();
            }
        });
    }

    private void doUpdate() {

        EditText tv_right_old = viewDataBinding.clOldPwd.findViewById(R.id.tv_right);
        EditText tv_right_new1 = viewDataBinding.clNewPwd1.findViewById(R.id.tv_right);
        EditText tv_right_new2 = viewDataBinding.clNewPwd2.findViewById(R.id.tv_right);

        tv_right_old.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tv_right_new1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tv_right_new2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        if (TextUtils.isEmpty(tv_right_old.getText())) {
            ToastUtils.toast("旧密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(tv_right_new1.getText())) {
            ToastUtils.toast("新密码不能为空");
            return;
        }

        if (TextUtils.isEmpty(tv_right_new2.getText())) {
            ToastUtils.toast("确认密码不能为空");
            return;
        }

        if (!TextUtils.equals(tv_right_new1.getText(), tv_right_new2.getText())) {
            ToastUtils.toast("新密码不一致");
            return;
        }

        UpdatePwd updatePwd = new UpdatePwd(tv_right_old.getText().toString(),
                tv_right_new1.getText().toString(), UserApplication.userName);

//
        UserRetrofitHandler.getInstance().createDasService().updatePwd(updatePwd)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DObserver<BaseResponseResult>() {
                    @Override
                    public void onSuccess(BaseResponseResult response) {

                        ToastUtils.toast(response.message + "");
                        if (response.isOk()) {
                            NoSqlUtils.updateUserLoginPwd(tv_right_new1.getText().toString());
                            finish();
                        }
                    }
                });
    }

    UserActivityUpdatePwdBinding viewDataBinding;

    @Override
    protected void onCreateNew(Bundle savedInstanceState) {

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.user_activity_update_pwd);


    }
}