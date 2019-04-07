package com.wzx.studyhelper.ui.compare.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.compare.bean.CompareListBean;
import com.wzx.studyhelper.ui.note.bean.NoteBean;

import java.util.List;

public class CompareDetailNoteAdapter extends BaseQuickAdapter<NoteBean,BaseViewHolder> {
    public CompareDetailNoteAdapter(@Nullable List<NoteBean> data) {
        super(R.layout.compare_note_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoteBean item) {
        helper.setText(R.id.tv_hint_name,"笔记" + (helper.getLayoutPosition()==0?"一":"二"))
                .setText(R.id.tv_course_name,"课程名称: " + item.getCourseName())
                .setText(R.id.tv_knowledge_name,"知识点: " + item.getKnowledgeName())
                .setText(R.id.tv_knowledge_answer,"知识答案: " + item.getKnowledgeAnswer())
                .setText(R.id.tv_note_mind,"个人心得:" + item.getPersonalExperience());
    }
}
