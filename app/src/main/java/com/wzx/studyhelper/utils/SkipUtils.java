package com.wzx.studyhelper.utils;

import android.content.Context;
import android.content.Intent;

import com.wzx.studyhelper.ui.start.act.HomeActivity;
import com.wzx.studyhelper.ui.start.act.LoginActivity;
import com.wzx.studyhelper.ui.start.act.RegisterActivity;

public class SkipUtils {

    public static void goHomeAct(Context context){
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }

    public static void goLoginAct(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    public static void goRegisterAct(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }
}
