package com.wzx.studyhelper.ui.start.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.start.bean.LeftMenuBean;

import java.util.List;

public class LeftMenuAdapter extends BaseQuickAdapter<LeftMenuBean,BaseViewHolder> {
    public LeftMenuAdapter(@Nullable List<LeftMenuBean> data) {
        super(R.layout.leftmeun_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeftMenuBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setImageResource(R.id.img_icon,item.getIcon());
    }
}
