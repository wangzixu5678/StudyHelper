package com.wzx.studyhelper.ui.chat.act;

import android.support.v4.app.Fragment;

import com.apkfuns.logutils.LogUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.chat.EMClient;

import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.ui.chat.fragment.ContactsFragment;
import com.wzx.studyhelper.ui.chat.fragment.ConversationFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MessageActivity extends BaseActivity{

    private String[] mTitles = {"会话","联系人"};
    @BindView(R.id.tablayout)
    SegmentTabLayout mTablayout;
    private ArrayList<Fragment> mFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        setBackTitle("我的消息",null);
        initTabLayout();
    }



    private void initTabLayout() {
        mFragments = new ArrayList<>();
        ConversationFragment conversationFragment = new ConversationFragment();
        ContactsFragment contactsFragment = new ContactsFragment();
        mFragments.add(conversationFragment);
        mFragments.add(contactsFragment);
        mTablayout.setTabData(mTitles,this,R.id.fl_fm_content,mFragments);
    }



}
