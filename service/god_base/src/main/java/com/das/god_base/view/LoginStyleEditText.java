package com.das.god_base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.das.god_base.R;

import com.das.god_base.utils.ScreenUtils;

import java.lang.reflect.Field;

import androidx.appcompat.widget.AppCompatEditText;

public class LoginStyleEditText extends AppCompatEditText {

    /*
     * 定义属性变量
     * */
    private Paint mPaint; // 画笔

    private Drawable ic_delete; // 删除图标


    private Drawable  ic_left;


    private int cursor; // 光标

    // 分割线变量
    private int lineColor_click,lineColor_unclick;// 点击时 & 未点击颜色
    private int color;
    private int linePosition;
    private int ic_left_clickResID;
    private boolean show_delete;
    private int delete_size;
    private Rect rect_delete;


    public LoginStyleEditText(Context context) {
        super(context);

    }

    public LoginStyleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LoginStyleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 步骤1：初始化属性
     */

    private void init(Context context, AttributeSet attrs) {

        // 获取控件资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginstyleEditText);

        ic_left_clickResID = typedArray.getResourceId(R.styleable.LoginstyleEditText_ic_left, 0);
        // 2. 根据资源ID获取图标资源（转化成Drawable对象）
        ic_left =  getResources().getDrawable(ic_left_clickResID);

        /**
         * 初始化删除图标
         */
        // 2. 根据资源ID获取图标资源（转化成Drawable对象）
        ic_delete =  getResources().getDrawable(R.drawable.delete_click);

        delete_size = ScreenUtils.dp2px(context, 20);



        /**
         * 初始化分割线（颜色、粗细、位置）
         */
        // 1. 设置画笔
        mPaint = new Paint();
        mPaint.setStrokeWidth(2.0f); // 分割线粗细

        // 2. 设置分割线颜色（使用十六进制代码，如#333、#8e8e8e）
        int lineColorClick_default = context.getResources().getColor(R.color.base_gray0); // 默认 = 蓝色#1296db
        int lineColorunClick_default = context.getResources().getColor(R.color.base_gray0); // 默认 = 灰色#9b9b9b
        lineColor_click = typedArray.getColor(R.styleable.LoginstyleEditText_lineColor_click, lineColorClick_default);
        lineColor_unclick = typedArray.getColor(R.styleable.LoginstyleEditText_lineColor_unclick, lineColorunClick_default);
        color = lineColor_unclick;

        mPaint.setColor(lineColor_unclick); // 分割线默认颜色 = 灰色

//        int color_text = context.getResources().getColor(R.color.base_gray2); // 默认 = 灰色#9b9b9b
//        setTextColor(color_text); // 字体默认颜色 = 灰色
//        setHintTextColor(color_text);

        // 3. 分割线位置
        linePosition = typedArray.getInteger(R.styleable.LoginstyleEditText_linePosition, 1);

        typedArray.recycle();

        // 消除自带下划线
        setBackground(null);


        setCompoundDrawablesWithIntrinsicBounds(ic_left,null,null,null);
    }

    /**
     * 复写EditText本身的方法：onTextChanged（）
     * 调用时刻：当输入框内容变化时
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setDeleteIconVisible(hasFocus() && text.length() > 0,hasFocus());
        // hasFocus()返回是否获得EditTEXT的焦点，即是否选中
        // setDeleteIconVisible（） = 根据传入的是否选中 & 是否有输入来判断是否显示删除图标->>关注1
    }

    /**
     * 复写EditText本身的方法：onFocusChanged（）
     * 调用时刻：焦点发生变化时
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDeleteIconVisible(focused && length() > 0,focused);
        // focused = 是否获得焦点
        // 同样根据setDeleteIconVisible（）判断是否要显示删除图标->>关注1
    }


    /**
     * 作用：对删除图标区域设置为"点击 即 清空搜索框内容"
     * 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
        switch (event.getAction()) {
            // 判断动作 = 手指抬起时
            case MotionEvent.ACTION_UP:
                Drawable drawable =  ic_delete;

                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {

                    // 判断条件说明
                    // event.getX() ：抬起时的位置坐标
                    // getWidth()：控件的宽度
                    // getPaddingRight():删除图标图标右边缘至EditText控件右边缘的距离
                    // 即：getWidth() - getPaddingRight() = 删除图标的右边缘坐标 = X1
                    // getWidth() - getPaddingRight() - drawable.getBounds().width() = 删除图标左边缘的坐标 = X2
                    // 所以X1与X2之间的区域 = 删除图标的区域
                    // 当手指抬起的位置在删除图标的区域（X2=<event.getX() <=X1），即视为点击了删除图标 = 清空搜索框内容
                    setText("");

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 关注1
     * 作用：判断是否显示删除图标 & 设置分割线颜色
     */
    private void setDeleteIconVisible(boolean deleteVisible,boolean leftVisible) {


        show_delete=deleteVisible;
        color = leftVisible ? lineColor_click : lineColor_unclick;

        invalidate();
    }

    /**
     * 作用：绘制分割线
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);

        // 绘制分割线
        // 需要考虑：当输入长度超过输入框时，所画的线需要跟随着延伸
        // 解决方案：线的长度 = 控件长度 + 延伸后的长度
        int x=this.getScrollX(); // 获取延伸后的长度
        int w=this.getMeasuredWidth(); // 获取控件长度

        // 传入参数时，线的长度 = 控件长度 + 延伸后的长度
        canvas.drawLine(0, this.getMeasuredHeight()- linePosition, w+x,
                this.getMeasuredHeight() - linePosition, mPaint);

        if(show_delete){
            int measuredHeight = getMeasuredHeight();
            ic_delete.setBounds(w-delete_size, (measuredHeight-delete_size)/2, w, measuredHeight/2+delete_size/2);

            ic_delete.draw(canvas);
        }
    }

}

