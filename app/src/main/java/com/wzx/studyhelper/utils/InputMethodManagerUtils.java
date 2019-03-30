package com.wzx.studyhelper.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Administrator on 2016/6/25.
 */
public class InputMethodManagerUtils {
    /**
     * 方法二（view为接受软键盘输入的视图，SHOW_FORCED表示强制显示）
     */
    public static void showSoftInput(Context context, View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 方法二（view为接受软键盘输入的视图，强制隐藏键盘）
     */
    public static void hideSoftInput(Context context, View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
    }
    /**
     * 方法三(如果输入法在窗口上已经显示，则隐藏，反之则显示)
     */
    public static void changeSoftInput(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 3、调用隐藏系统默认的输入法
     */
    public static void systemhideSoftInput(Activity activity) {

        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); // (WidgetSearchActivity是当前的Activity)

    }

    /**
     * 获取输入法打开的状态
     */
    public static boolean isOpen(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
        return isOpen;
    }

    public static void showSoftInputByFocus(Activity activity){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}
