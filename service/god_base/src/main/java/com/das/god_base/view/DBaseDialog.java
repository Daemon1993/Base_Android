package com.das.god_base.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.das.god_base.R;

import com.socks.library.KLog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;


/**
 * Created by yuanyf on 2018/11/5.
 */


public class DBaseDialog extends DialogFragment {


    private TextView tv_content;
    private TextView tv_left;
    private TextView tv_right;

    private String content;
    private View.OnClickListener rightListener;
    private View.OnClickListener leftListener;


    public void setRightListener(View.OnClickListener rightListener) {
        this.rightListener = rightListener;
    }


    public void setLeftListener(View.OnClickListener leftListener) {
        this.leftListener = leftListener;
    }

    private String right;
    private String left;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        KLog.e("onCreateView "+leftListener+" "+rightListener);


        View view = inflater.inflate(R.layout.dialog_dbase_layout, null, false);

        tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_left = (TextView) view.findViewById(R.id.tv_left);
        tv_right = (TextView) view.findViewById(R.id.tv_right);


        if (!TextUtils.isEmpty(content)) {
            tv_content.setText(content);
        }


        if (!TextUtils.isEmpty(left)) {
            tv_left.setText(left);
        }

        if (!TextUtils.isEmpty(right)) {
            tv_right.setText(right);
        }


        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (leftListener != null) {
                    leftListener.onClick(tv_left);
                }
            }
        });

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                KLog.e("  "+rightListener);
                if (rightListener != null) {
                    rightListener.onClick(view);
                }

            }
        });



        return view;
    }

    public void setShowContent(String content, String left, String right) {

        this.content = content;
        this.left = left;
        this.right = right;

        if (tv_content == null) {
            return;
        }
        tv_content.setText(content + "");

        if (!TextUtils.isEmpty(left)) {
            tv_left.setText(left + "");
        }
        if (!TextUtils.isEmpty(right)) {
            tv_right.setText(right + "");
        }
    }

    @Override
    public void onStart() {


        KLog.e("onStart");

        //设置DialogFragment所依附的window背景透明（不设置会有一块灰色的背景）
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置dialog的位置（自定义的布局并没有显示在window的中间，没达到我想要的效果）
        getDialog().getWindow().setGravity(Gravity.TOP);

        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.y= getResources().getDimensionPixelOffset(R.dimen.qb_px_167);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.qb_px_295);
        //设置Window的大小，想要自定义Dialog的位置摆放正确，将Window的大小保持和自定义Dialog的大小一样
        getDialog().getWindow().setLayout(dimensionPixelOffset, ConstraintLayout.LayoutParams.WRAP_CONTENT);

//        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        super.onStart();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        KLog.e("onCreateDialog ");
        return super.onCreateDialog(savedInstanceState);
    }
}
