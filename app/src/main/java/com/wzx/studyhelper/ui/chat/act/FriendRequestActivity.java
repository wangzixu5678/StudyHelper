package com.wzx.studyhelper.ui.chat.act;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.db.impl.FriendsRequestDaoManager;
import com.wzx.studyhelper.db.impl.UserIconDaoManager;
import com.wzx.studyhelper.ui.chat.adapter.FriendRequestAdpter;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.ThreadPoolManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 好友请求记录
 */
public class FriendRequestActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {


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
                mDatas.addAll(UserIconDaoManager.getInstance().getMyFriendsRequest(SharedPreferencesUtil.getInstance().getString(Constants.USER_PHONE)));
                mWeakHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mFriendRequestAdpter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),OrientationHelper.VERTICAL));
        mFriendRequestAdpter = new FriendRequestAdpter(mDatas);
        mFriendRequestAdpter.setEmptyView(getEmptyView());
        mFriendRequestAdpter.setOnItemChildClickListener(this);
        mRecyclerview.setAdapter(mFriendRequestAdpter);
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int position) {
        switch (view.getId()) {
            case R.id.btn_agree:
                ThreadPoolManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FriendsRequestDB friendsRequestDB = mDatas.get(position);
                            EMClient.getInstance().contactManager().acceptInvitation(friendsRequestDB.getNickname());
                            friendsRequestDB.setStatus(Constants.ALREADY_FRIEND);
                            FriendsRequestDaoManager.getInstance().update(friendsRequestDB);
                            mWeakHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mFriendRequestAdpter.notifyDataSetChanged();
                                }
                            });

                        } catch (HyphenateException e) {
                            ToastUtils.show("未知异常");
                        }
                    }
                });

                break;
            case R.id.btn_refuse:
                ThreadPoolManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FriendsRequestDB friendsRequestDB = mDatas.get(position);
                            EMClient.getInstance().contactManager().declineInvitation(friendsRequestDB.getNickname());
                            friendsRequestDB.setStatus(Constants.REFUSE_FRIEND);
                            FriendsRequestDaoManager.getInstance().update(friendsRequestDB);
                            mWeakHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mFriendRequestAdpter.notifyDataSetChanged();
                                }
                            });
                        } catch (HyphenateException e) {
                            ToastUtils.show("未知异常");
                        }
                    }
                });
                break;
        }
    }
}
