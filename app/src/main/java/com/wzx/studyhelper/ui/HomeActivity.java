package com.wzx.studyhelper.ui;

import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.common.bean.TabEntity;
import com.wzx.studyhelper.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.vp)
    NoScrollViewPager mVp;
    @BindView(R.id.tablayout)
    CommonTabLayout mTablayout;
    private ArrayList<CustomTabEntity> mTabEntitys;

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

    }

    private void initTab() {
        mTabEntitys = new ArrayList<>();
        mTabEntitys.add(new TabEntity("首页",R.drawable.bottom_home_sel,R.drawable.bottom_home_unsel));
        mTabEntitys.add(new TabEntity("搜索",R.drawable.bottom_search_sel,R.drawable.bottom_search_unsel));
        mTabEntitys.add(new TabEntity("笔记",R.drawable.bottom_find_sel,R.drawable.bottom_find_unsel));
        mTabEntitys.add(new TabEntity("我的",R.drawable.bottom_me_sel,R.drawable.bottom_me_unsel));
        mTablayout.setTabData(mTabEntitys);
    }

}
