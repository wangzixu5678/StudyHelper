package com.wzx.studyhelper.ui;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;

public class HomeActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        HttpManager.getInstance().getDateFromNet(this, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {

            }
        });
    }


}
