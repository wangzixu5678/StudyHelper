package com.wzx.studyhelper.ui.diffcult.act;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.ui.diffcult.fragment.DifficultFragment;

public class DifficultActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_difficult;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        DifficultFragment difficultFragment = new DifficultFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fm_content,difficultFragment).commit();
    }
}
