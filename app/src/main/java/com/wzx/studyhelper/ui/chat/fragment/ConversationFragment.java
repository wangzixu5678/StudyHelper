package com.wzx.studyhelper.ui.chat.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hyphenate.chat.EMConversation;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.ui.chat.adapter.ConversationAdapter;
import com.wzx.studyhelper.utils.ConversationUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 会话列表
 */
public class ConversationFragment extends BaseFragment {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;
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
    protected void initCircle() {
        initRv();
    }


    private void initRv() {
        mDatas = ConversationUtils.loadConversationList();
        mConversationAdapter = new ConversationAdapter(mDatas);
        mRecyclerview.setAdapter(mConversationAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
