package com.wzx.studyhelper.ui.note.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.note.bean.NoteListBean;

import java.util.List;

public class NoteListAdapter extends BaseQuickAdapter<NoteListBean.DataBean,BaseViewHolder> {
    public NoteListAdapter(@Nullable List<NoteListBean.DataBean> data) {
        super(R.layout.note_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoteListBean.DataBean item) {
        helper.setText(R.id.tv_course_name,"课程名称:" + item.getCourseName());
        helper.setText(R.id.tv_knowledge_name,"知识点名称:" + item.getKnowledgeName());
    }
}
