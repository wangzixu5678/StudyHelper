package com.wzx.studyhelper.ui.chat.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.domain.EaseUser;
import com.wzx.studyhelper.R;

import java.util.List;

public class ContactsAdapter extends BaseQuickAdapter<EaseUser,BaseViewHolder> {
    public ContactsAdapter(@Nullable List<EaseUser> data) {
        super(R.layout.contacts_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EaseUser item) {

    }
}
