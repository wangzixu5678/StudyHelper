package com.wzx.studyhelper.ui.start.act;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.common.bean.TabEntity;
import com.wzx.studyhelper.ui.compare.act.CompareActivity;
import com.wzx.studyhelper.ui.diffcult.act.DifficultActivity;
import com.wzx.studyhelper.ui.examination.act.ExaminationActivity;
import com.wzx.studyhelper.ui.homework.fragment.HomeWorkFragment;
import com.wzx.studyhelper.ui.note.fragment.NoteFragment;
import com.wzx.studyhelper.ui.schedule.act.ScheduleActivity;
import com.wzx.studyhelper.ui.start.adapter.LeftMenuAdapter;
import com.wzx.studyhelper.ui.start.adapter.VpAdapter;
import com.wzx.studyhelper.ui.coursetab.fragment.CourseTabFragment;
import com.wzx.studyhelper.ui.start.bean.LeftMenuBean;
import com.wzx.studyhelper.ui.start.fragment.UserFragment;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.PhotoUtils;
import com.wzx.studyhelper.widget.NoScrollViewPager;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.vp)
    NoScrollViewPager mVp;
    @BindView(R.id.tablayout)
    CommonTabLayout mTablayout;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private ArrayList<CustomTabEntity> mTabEntitys;
    private ArrayList<Fragment> mFragments;
    private CourseTabFragment mCourseTabFragment;
    private UserFragment mUserFragment;
    private HomeWorkFragment mHomeWorkFragment;
    private NoteFragment mNoteFragment;
    private ArrayList<LeftMenuBean> mDatas;
    private LeftMenuAdapter mLeftMenuAdapter;
    private long clickTime;

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
        initRv();
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mDatas.add( new LeftMenuBean(R.drawable.examicon,"考场记录"));
        mDatas.add( new LeftMenuBean(R.drawable.difficuteicon,"难点记录"));
        mDatas.add( new LeftMenuBean(R.drawable.scheduleicon,"我的计划"));
        mDatas.add( new LeftMenuBean(R.drawable.compareicon,"对比功能"));
        mDatas.add(new LeftMenuBean(R.drawable.verson_icon,"版本号 "+PhotoUtils.getAppVersionName(this)));
        mDatas.add(new LeftMenuBean(R.drawable.work_icon,"作者 XXX"));
        mLeftMenuAdapter = new LeftMenuAdapter(mDatas);
        mLeftMenuAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,OrientationHelper.VERTICAL));
        mRecyclerView.setAdapter(mLeftMenuAdapter);
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
                mVp.setCurrentItem(position,false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    private void initVp() {
        mFragments = new ArrayList<>();
        mCourseTabFragment = new CourseTabFragment();
        mHomeWorkFragment = new HomeWorkFragment();
        mNoteFragment = new NoteFragment();
        mUserFragment = new UserFragment();
        mFragments.add(mCourseTabFragment);
        mFragments.add(mHomeWorkFragment);
        mFragments.add(mNoteFragment);
        mFragments.add(mUserFragment);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(),mFragments);
        mVp.setAdapter(vpAdapter);
    }

    public DrawerLayout getDrawerLayout(){
        return mDrawerLayout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == Constants.REQUEST_IMGSEL) {
                mUserFragment.setImgIcon(Matisse.obtainPathResult(data).get(0));
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mDrawerLayout.closeDrawers();
        Intent intent;
        switch (position){
            case 0:
                //考场记录
               intent = new Intent(this,ExaminationActivity.class);
                startActivity(intent);
                break;
            case 1:
                //难点记录
                intent = new Intent(this,DifficultActivity.class);
                startActivity(intent);
                break;
            case 2:
                //我的计划
                intent = new Intent(this,ScheduleActivity.class);
                startActivity(intent);
                break;
            case 3:
                //对比功能
                intent = new Intent(this,CompareActivity.class);
                startActivity(intent);
                break;
        }

    }



    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }
}
