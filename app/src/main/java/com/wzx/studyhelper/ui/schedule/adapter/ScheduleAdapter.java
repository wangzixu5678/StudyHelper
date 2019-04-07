package com.wzx.studyhelper.ui.schedule.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.schedule.bean.ScheduleListBean;
import com.wzx.studyhelper.utils.DateUtils;

import java.util.List;

public class ScheduleAdapter extends BaseQuickAdapter<ScheduleListBean.DataBean, BaseViewHolder> {
    public ScheduleAdapter(@Nullable List<ScheduleListBean.DataBean> data) {
        super(R.layout.schedule_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScheduleListBean.DataBean item) {
        helper.setText(R.id.tv_schedule_name, "计划名称: " + item.getScheduleName())
                .setText(R.id.tv_schedule_type, item.getScheduleType() == 1 ? "日常学习计划" : "考试复习计划")
                .setText(R.id.tv_star_time, "计划开始时间: " + DateUtils.getFormatTime2(item.getStartTimeDto()))
                .setText(R.id.tv_end_time, "计划结束时间: " + DateUtils.getFormatTime2(item.getEndTimeDto()));

        switch (item.getCompletionSchedule()) {
            case 1:
                helper.setText(R.id.tv_schedule_status,"计划状态:准备中");
                helper.setTextColor(R.id.tv_schedule_status,Color.parseColor("#ffb900"));
                break;
            case 2:
                helper.setTextColor(R.id.tv_schedule_status,Color.GREEN);
                helper.setText(R.id.tv_schedule_status,"计划状态:已完成");
                break;
            case 3:
                helper.setText(R.id.tv_schedule_status,"计划状态:已过期");
                helper.setTextColor(R.id.tv_schedule_status,Color.RED);
                break;
            case 4:
                helper.setText(R.id.tv_schedule_status,"计划状态:已废弃");
                helper.setTextColor(R.id.tv_schedule_status,Color.GRAY);
                break;
        }
    }
}
