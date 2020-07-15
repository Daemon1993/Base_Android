package com.das.god_base.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

class DrawableUtils {
    public static StateListDrawable createDrawableSelector(Drawable checked, Drawable unchecked) {
        StateListDrawable stateList = new StateListDrawable();
        int state_selected = android.R.attr.state_selected;
        stateList.addState(new int[]{state_selected}, checked);
        stateList.addState(new int[]{-state_selected}, unchecked);
        return stateList;
    }

    public ColorStateList createColorSelector(int checkedColor, int uncheckedColor) {

        return new ColorStateList(
                new int[][]{new int[]{android.R.attr.state_selected},
                        new int[]{-android.R.attr.state_selected}},
                new int[]{checkedColor, uncheckedColor});
    }

    // 将文件转换成Drawable
    // pathName就是图片存放的绝对路径
    public  Drawable getDrawableByFile(String pathName) {
        return Drawable.createFromPath(pathName);
    }

}
