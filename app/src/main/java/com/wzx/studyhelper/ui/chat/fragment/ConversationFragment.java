package com.wzx.studyhelper.ui.chat.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.db.bean.UserIconDB;
import com.wzx.studyhelper.db.impl.UserIconDaoManager;
import com.wzx.studyhelper.ui.chat.adapter.ConversationAdapter;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.ConversationUtils;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.SkipUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 会话列表
 */
public class ConversationFragment extends BaseFragment implements EMConnectionListener, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.ll_net_status)
    LinearLayout mLlNetStatus;
    private List<EMConversation> mDatas;
    private ConversationAdapter mConversationAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            mDatas.clear();
            mDatas.addAll(ConversationUtils.loadConversationList());
            mConversationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRequestRefresh){
            isRequestRefresh = false;
            mDatas.clear();
            mDatas.addAll(ConversationUtils.loadConversationList());
            mConversationAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void initCircle() {
        initRv();
        initListener();
    }

    private void initListener() {
        EMClient.getInstance().addConnectionListener(this);
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mDatas.addAll(ConversationUtils.loadConversationList());
        mConversationAdapter = new ConversationAdapter(mDatas);
        mConversationAdapter.setEmptyView(getEmptyView());
        mConversationAdapter.setOnItemChildClickListener(this);
        mRecyclerview.setAdapter(mConversationAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onConnected() {
        mLlNetStatus.setVisibility(View.GONE);
    }

    @Override
    public void onDisconnected(int i) {
        mLlNetStatus.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_delete:
                EMClient.getInstance().chatManager().deleteConversation(mDatas.get(position).conversationId(), true);
                mDatas.remove(position);
                mConversationAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_content:
                mDatas.get(position).markAllMessagesAsRead();
                isRequestRefresh = true;
                UserIconDB userIconDB = UserIconDaoManager.getInstance().queryByUserId(SharedPreferencesUtil.getInstance().getString(Constants.USER_PHONE));
                UserIconDB userIconDB2 = UserIconDaoManager.getInstance().queryByUserId(mDatas.get(position).conversationId());
                if (userIconDB2==null){
                    SkipUtils.goChat(getContext(),mDatas.get(position).conversationId(),
                            userIconDB.getNickname(),
                            userIconDB.getUserIcon(),
                            mDatas.get(position).conversationId(),
                            "");
                }else {
                    SkipUtils.goChat(getContext(),mDatas.get(position).conversationId(),
                            userIconDB.getNickname(),
                            userIconDB.getUserIcon(),
                            userIconDB2.getNickname(),
                            userIconDB2.getUserIcon());
                }
                break;
        }
    }


}
