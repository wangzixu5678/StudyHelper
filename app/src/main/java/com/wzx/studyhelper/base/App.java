package com.wzx.studyhelper.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.db.utils.DaoManager;
import com.wzx.studyhelper.ui.chat.utils.ChatHelper;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hdxy on 2018/11/30.
 */
public class App extends Application {
    public static Context mContext;


    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                return new ClassicsHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
        ClassicsFooter.REFRESH_FOOTER_ALLLOADED = "我是有底线的";
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        SharedPreferencesUtil.init(this,"studyhelper",MODE_PRIVATE);
        ToastUtils.init(this);
        initEaseUI();
        initXunFei();
        initGreenDao();
    }




    private void initXunFei() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5ca44f7c");
    }


    private void initGreenDao() {
        DaoManager.init(this);
    }

    private void initEaseUI() {
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        options.setAutoLogin(true);
        EaseUI.getInstance().init(this,options);
        EMClient.getInstance().setDebugMode(true);
        ChatHelper.getInstance().init();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }






    public static Context getContext(){
        return mContext;
    }
}
