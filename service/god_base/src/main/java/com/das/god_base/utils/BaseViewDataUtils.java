package com.das.god_base.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.das.god_base.R;
import com.das.god_base.ToastUtils;

public class BaseViewDataUtils {
    public static void layout_base_textview_style1DataAction(View layout, String title, String right_title) {
        TextView tv_title = layout.findViewById(R.id.tv_title);
        tv_title.setText(title);

        if (!TextUtils.isEmpty(right_title)) {
            TextView tv_right = layout.findViewById(R.id.tv_right);
            tv_right.setText(right_title);
        }
    }

    public static void layout_base_edittext_style1DataAction(View layout, String title, String right_title) {
        TextView tv_title = layout.findViewById(R.id.tv_title);
        tv_title.setText(title);
    }


    public static void toastErrorMsg(Exception e) {
        ToastUtils.toast("error "+e.getMessage());
    }
}
