package com.das.god_base.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.das.god_base.R;

public class BaseLoadingDialog extends ProgressDialog {

    private String title;

    public BaseLoadingDialog(Context context) {
        super(context);
    }

    public BaseLoadingDialog(Context context, int theme) {
        super(context, theme);
    }
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
//        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.loading_base_layout);
        textView = findViewById(R.id.loading_title);
        if(!TextUtils.isEmpty(title)){
            textView.setText(title);
        }
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

    }

    public void setLoadingTitle(String title){
        this.title=title;
        if(textView != null && !TextUtils.isEmpty(title)){
            textView.setText(title + "");
        }
    }

    @Override
    public void show() {

        if(isShowing()){
            return;
        }

        super.show();
    }
}
