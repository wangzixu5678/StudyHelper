package com.wzx.studyhelper.ui.homework.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.homework.bean.HomeWorkListBean;
import com.wzx.studyhelper.utils.DateUtils;
import com.wzx.studyhelper.utils.StringUtil;

import java.util.List;

public class HomeWorkListAdapter extends BaseQuickAdapter<HomeWorkListBean.DataBean,BaseViewHolder> {
    public HomeWorkListAdapter(@Nullable List<HomeWorkListBean.DataBean> data) {
        super(R.layout.homework_item_layout, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeWorkListBean.DataBean item) {
        helper.setText(R.id.tv_course_name,"课程名称:" + item.getCourseName());
        helper.setText(R.id.tv_time,"开始时间:" + DateUtils.getFormatTime(item.getStartTimeDto()));
        helper.setText(R.id.tv_end_time,"结束时间:" + DateUtils.getFormatTime(item.getEndTimeDto()));
        if (item.getEndTimeDto()>System.currentTimeMillis()) {
            helper.setText(R.id.tv_homework_status,"未完成");
            helper.setTextColor(R.id.tv_homework_status,Color.RED);
        }else {
            helper.setText(R.id.tv_homework_status,"已完成");
            helper.setTextColor(R.id.tv_homework_status,Color.GREEN);
        }

    }
}
