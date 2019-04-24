package com.wzx.studyhelper.utils;

import android.content.Context;
import android.content.Intent;

import com.hyphenate.easeui.EaseConstant;
import com.wzx.studyhelper.ui.chat.act.ChatActivity;
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

    public static void goChat(Context context,String username,String youname,String youicon,String othername,String othericon){
        Intent intent = new Intent(context,ChatActivity.class);
        intent.putExtra(EaseConstant.EXTRA_USER_ID,username);
        intent.putExtra(Constants.YOUNAME,youname);
        intent.putExtra(Constants.YOUICON,youicon);
        intent.putExtra(Constants.OTHERNAME,othername);
        intent.putExtra(Constants.OTHERICON,othericon);
        context.startActivity(intent);
    }
}
