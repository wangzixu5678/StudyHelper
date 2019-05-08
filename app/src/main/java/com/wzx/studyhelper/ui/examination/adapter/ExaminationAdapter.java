package com.wzx.studyhelper.ui.examination.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.examination.bean.ExaminationListBean;
import com.wzx.studyhelper.utils.DateUtils;

import java.util.List;

public class ExaminationAdapter extends BaseQuickAdapter<ExaminationListBean.DataBean,BaseViewHolder> {
    public ExaminationAdapter(@Nullable List<ExaminationListBean.DataBean> data) {
        super(R.layout.examination_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,ExaminationListBean.DataBean item) {
        helper.setText(R.id.tv_exam_name,"考试课程名称:"+item.getCourseName());
        helper.setText(R.id.tv_time,"考试开始时间:" +DateUtils.getFormatTime2(item.getStartTimeDto()));
        helper.setText(R.id.tv_end_time,"考试结束时间:" +DateUtils.getFormatTime2(item.getEndTimeDto()));
        if (item.getStartTimeDto()>System.currentTimeMillis()){
            helper.setText(R.id.tv_exam_status,"考试未开始");
            helper.setTextColor(R.id.tv_exam_status,Color.GRAY);
        }else if (item.getStartTimeDto()<System.currentTimeMillis()&&item.getEndTimeDto()>System.currentTimeMillis()){
            helper.setText(R.id.tv_exam_status,"考试进行中");
            helper.setTextColor(R.id.tv_exam_status,Color.GREEN);
        }else {
            helper.setText(R.id.tv_exam_status,"考试已结束");
            helper.setTextColor(R.id.tv_exam_status,Color.RED);
        }




    }
}
