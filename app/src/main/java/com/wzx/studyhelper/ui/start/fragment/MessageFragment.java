package com.wzx.studyhelper.ui.start.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SegmentTabLayout;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.common.adapter.ViewPagerAdapter;
import com.wzx.studyhelper.ui.chat.fragment.ContactsFragment;
import com.wzx.studyhelper.ui.chat.fragment.ConversationFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MessageFragment extends BaseFragment {
    @BindView(R.id.tl_1)
    SegmentTabLayout mTl1;
    @BindView(R.id.vp)
    ViewPager mVp;
    Unbinder unbinder;
    private String[] mTitles = {"会话","通讯录"};
    private ArrayList<Fragment> mFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {
        setCustomTitle(null,"消息",null);
        initViewPager();
    }

    private void initViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new ContactsFragment());
        mFragments.add(new ConversationFragment());
        mTl1.setTabData(mTitles);
        mVp.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager(),mFragments));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
