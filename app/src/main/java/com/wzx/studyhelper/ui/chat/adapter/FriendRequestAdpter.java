package com.wzx.studyhelper.ui.chat.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.common.ui.Test2Activity;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.StringUtil;
import com.wzx.studyhelper.utils.glide.GlideDoMain;

import java.util.List;

public class FriendRequestAdpter extends BaseQuickAdapter<FriendsRequestDB, BaseViewHolder> {
    public FriendRequestAdpter(@Nullable List<FriendsRequestDB> data) {
        super(R.layout.friendrequest_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsRequestDB item) {

        helper.setText(R.id.tv_nickname, item.getNickname());
        helper.setText(R.id.tv_reason,"原因:" + (StringUtil.isEmpty(item.getReason())?"无":item.getReason()));

        TextView tvStatusHint = (TextView) helper.getView(R.id.tv_status_hint);

        Button btnAgree = (Button) helper.getView(R.id.btn_agree);

        Button btnRefuse = (Button) helper.getView(R.id.btn_refuse);

        switch (item.getStatus()) {
            case Constants.ALREADY_FRIEND:
                btnAgree.setVisibility(View.GONE);
                btnRefuse.setVisibility(View.GONE);
                tvStatusHint.setVisibility(View.VISIBLE);
                tvStatusHint.setText("好友已添加");
                tvStatusHint.setTextColor(Color.GREEN);
                break;
            case Constants.REFUSE_FRIEND:
                btnAgree.setVisibility(View.GONE);
                btnRefuse.setVisibility(View.GONE);
                tvStatusHint.setVisibility(View.VISIBLE);
                tvStatusHint.setText("好友已拒绝");
                tvStatusHint.setTextColor(Color.RED);
                break;
            case Constants.WAITING_FRIEND:
                btnAgree.setVisibility(View.VISIBLE);
                btnRefuse.setVisibility(View.VISIBLE);
                tvStatusHint.setVisibility(View.GONE);
                break;
        }

        helper.addOnClickListener(R.id.btn_agree)
                .addOnClickListener(R.id.btn_refuse);


    }


}
