package com.wzx.studyhelper.ui.chat.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.domain.EaseUser;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.utils.StringUtil;
import com.wzx.studyhelper.utils.glide.GlideDoMain;

import java.util.List;

public class ContactsAdapter extends BaseQuickAdapter<EaseUser, BaseViewHolder> {
    public ContactsAdapter(@Nullable List<EaseUser> data) {
        super(R.layout.contacts_item_layout, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, EaseUser item) {
        helper.setText(R.id.img_nickname, item.getNickname());
        ImageView imageView = (ImageView) helper.getView(R.id.img_icon);
        if (!StringUtil.isEmpty(item.getAvatar())) {
            GlideDoMain.getInstance().loadCircleImage(mContext, item.getAvatar(), imageView);
        }

        helper.addOnClickListener(R.id.tv_delete).addOnClickListener(R.id.ll_content);
    }
}
