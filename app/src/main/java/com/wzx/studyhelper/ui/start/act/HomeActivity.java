package com.wzx.studyhelper.ui.start.act;


import android.content.Intent;
import android.support.v4.app.Fragment;

import com.apkfuns.logutils.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.common.bean.TabEntity;
import com.wzx.studyhelper.ui.start.adapter.VpAdapter;
import com.wzx.studyhelper.ui.coursetab.fragment.CourseTabFragment;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.widget.NoScrollViewPager;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.vp)
    NoScrollViewPager mVp;
    @BindView(R.id.tablayout)
    CommonTabLayout mTablayout;
    private ArrayList<CustomTabEntity> mTabEntitys;
    private ArrayList<Fragment> mFragments;
    private CourseTabFragment mCourseTabFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        initTab();
        initVp();
    }


    private void initTab() {
        mTabEntitys = new ArrayList<>();
        mTabEntitys.add(new TabEntity("课程表",R.drawable.bottom_home_sel,R.drawable.bottom_home_unsel));
        mTabEntitys.add(new TabEntity("作业",R.drawable.bottom_search_sel,R.drawable.bottom_search_unsel));
        mTabEntitys.add(new TabEntity("笔记",R.drawable.bottom_find_sel,R.drawable.bottom_find_unsel));
        mTabEntitys.add(new TabEntity("我的",R.drawable.bottom_me_sel,R.drawable.bottom_me_unsel));
        mTablayout.setTabData(mTabEntitys);
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    private void initVp() {
        mFragments = new ArrayList<>();
        mCourseTabFragment = new CourseTabFragment();
        mFragments.add(mCourseTabFragment);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(),mFragments);
        mVp.setAdapter(vpAdapter);
    }


}
