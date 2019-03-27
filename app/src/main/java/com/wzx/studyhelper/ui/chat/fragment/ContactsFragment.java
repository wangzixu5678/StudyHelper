package com.wzx.studyhelper.ui.chat.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.exceptions.HyphenateException;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.common.bus.FriendsRequestBus;
import com.wzx.studyhelper.ui.chat.act.FriendRequestActivity;
import com.wzx.studyhelper.ui.chat.adapter.ContactsAdapter;
import com.wzx.studyhelper.utils.ThreadPoolManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 联系人列表
 */
public class ContactsFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_red_point)
    TextView mTvRedPoint;
    private ArrayList<EaseUser> mDatas;
    private ContactsAdapter mContactsAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {
        EventBus.getDefault().register(this);
        initRv();
        getDataFromService();
    }

    private void getDataFromService() {
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> allContactsFromServer = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    for (String name : allContactsFromServer) {
                        EaseUser easeUser = new EaseUser(name);
                        mDatas.add(easeUser);
                    }
                    mContactsAdapter.notifyDataSetChanged();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mContactsAdapter = new ContactsAdapter(mDatas);
        mRecyclerView.setAdapter(mContactsAdapter);
    }





    @OnClick(R.id.rl_content_apply_friends)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击好友申请
            case R.id.rl_content_apply_friends:
                mTvRedPoint.setVisibility(View.GONE);

                Intent intent = new Intent(getContext(),FriendRequestActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onFriendNotifyRequst(FriendsRequestBus friendsRequestBus) {
        mTvRedPoint.setVisibility(View.VISIBLE);
    }
}
