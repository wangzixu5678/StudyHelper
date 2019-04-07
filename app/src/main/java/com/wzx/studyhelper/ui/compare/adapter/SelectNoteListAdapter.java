package com.wzx.studyhelper.ui.compare.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.note.bean.NoteBean;
import com.wzx.studyhelper.ui.note.bean.NoteListBean;

import java.util.List;

public class SelectNoteListAdapter extends BaseQuickAdapter<NoteBean,BaseViewHolder> {
    public SelectNoteListAdapter(@Nullable List<NoteBean> data) {
        super(R.layout.sel_note_item_layout,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final NoteBean item) {
        helper.setText(R.id.tv_course_name,"课程名称: " + item.getCourseName());
        helper.setText(R.id.tv_note_name,"笔记名称: " + item.getKnowledgeName());


        CheckBox checkBox = (CheckBox) helper.getView(R.id.cb);

        if (item.getIsSelected()==1){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    item.setIsSelected(1);
                }else {
                    item.setIsSelected(0);
                }
            }
        });
    }
}
