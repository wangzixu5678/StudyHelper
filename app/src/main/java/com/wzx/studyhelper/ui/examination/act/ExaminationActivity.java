package com.wzx.studyhelper.ui.examination.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.ui.examination.fragment.ExaminaionFragment;

public class ExaminationActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_examination;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        ExaminaionFragment examinaionFragment = new ExaminaionFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fm_content,examinaionFragment).commit();
    }
}
