package com.das.god_base.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.das.god_base.R;

class BaseProgressDialog extends ProgressDialog {

    private ProgressBar pb_load;
    private String title;

    public BaseProgressDialog(Context context) {
        super(context);
    }

    public BaseProgressDialog(Context context, int theme) {
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

        setContentView(R.layout.loading_base_layout1);
        textView = findViewById(R.id.loading_title);
        if(!TextUtils.isEmpty(title)){
            textView.setText(title);
        }
        pb_load = findViewById(R.id.pb_load);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

    }

    public void setLoadingTitle(String title) {
        this.title=title;
        if (textView != null && !TextUtils.isEmpty(title)) {
            textView.setText(title + "");
        }
    }

    public void updateProgress(int progress) {
        if (pb_load != null) {
            pb_load.setProgress(progress);
        }
    }

    @Override
    public void show() {

        if (isShowing()) {
            return;
        }

        super.show();
    }
}

