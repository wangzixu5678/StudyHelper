package com.wzx.studyhelper.ui.compare.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.ui.compare.fragment.CompareFragment;
import com.wzx.studyhelper.ui.diffcult.fragment.DifficultFragment;

public class CompareActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_compare;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        CompareFragment compareFragment = new CompareFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fm_content,compareFragment).commit();
    }
}
