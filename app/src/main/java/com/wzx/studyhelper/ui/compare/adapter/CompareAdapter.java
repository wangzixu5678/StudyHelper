package com.wzx.studyhelper.ui.compare.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.compare.bean.CompareListBean;

import java.util.List;

public class CompareAdapter extends BaseQuickAdapter<CompareListBean.DataBean,BaseViewHolder> {

    public CompareAdapter(@Nullable List<CompareListBean.DataBean> data) {
        super(R.layout.compare_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CompareListBean.DataBean item) {
        helper.setText(R.id.tv_name,"对比名称:" + item.getContrastName());
        helper.setText(R.id.tv_note1_name,"笔记一名称:" + item.getNotes().get(0).getKnowledgeName());
        helper.setText(R.id.tv_note1_name,"笔记二名称:" + item.getNotes().get(1).getKnowledgeName());
        helper.setText(R.id.tv_create_time,"创建时间:" + item.getCreateTime());
    }
}
