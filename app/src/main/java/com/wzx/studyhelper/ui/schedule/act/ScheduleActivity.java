package com.wzx.studyhelper.ui.schedule.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.ui.examination.fragment.ExaminaionFragment;
import com.wzx.studyhelper.ui.schedule.fragment.ScheduleFragment;

public class ScheduleActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fm_content,scheduleFragment).commit();
    }
}
