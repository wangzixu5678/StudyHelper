package com.wzx.studyhelper.ui.chat.act;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.db.impl.FriendsRequestDaoManager;
import com.wzx.studyhelper.ui.chat.adapter.FriendRequestAdpter;
import com.wzx.studyhelper.utils.ThreadPoolManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 好友请求记录
 */
public class FriendRequestActivity extends BaseActivity {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private ArrayList<FriendsRequestDB> mDatas;
    private FriendRequestAdpter mFriendRequestAdpter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_request;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        setBackTitle("好友申请", null);
        initRv();

        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                mDatas.clear();
                mDatas.addAll(FriendsRequestDaoManager.getInstance().queryAll());
                mFriendRequestAdpter.notifyDataSetChanged();
            }
        });
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),OrientationHelper.VERTICAL));
        mFriendRequestAdpter = new FriendRequestAdpter(mDatas);
        mRecyclerview.setAdapter(mFriendRequestAdpter);
    }


}
