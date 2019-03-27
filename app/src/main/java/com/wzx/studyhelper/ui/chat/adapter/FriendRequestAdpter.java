package com.wzx.studyhelper.ui.chat.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;

import java.util.List;

public class FriendRequestAdpter extends BaseQuickAdapter<FriendsRequestDB,BaseViewHolder> {
    public FriendRequestAdpter(@Nullable List<FriendsRequestDB> data) {
        super(R.layout.friendrequest_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsRequestDB item) {

    }
}
