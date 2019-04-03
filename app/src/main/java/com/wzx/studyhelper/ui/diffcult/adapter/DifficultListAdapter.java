package com.wzx.studyhelper.ui.diffcult.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.diffcult.bean.DiffcultListBean;

import java.util.List;

public class DifficultListAdapter extends BaseQuickAdapter<DiffcultListBean.DataBean,BaseViewHolder> {
    public DifficultListAdapter(@Nullable List<DiffcultListBean.DataBean> data) {
        super(R.layout.difficult_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiffcultListBean.DataBean item) {
        helper.setText(R.id.tv_course_name,"课程名称:" + item.getCourseName())
                .setText(R.id.tv_diffcult_name,"难点名称:" +item.getDifficultName());
        TextView tvStatus = (TextView) helper.getView(R.id.tv_diffcult_status);
        if (item.getDealStatus()==1){
            tvStatus.setText("已解决");
            tvStatus.setTextColor(Color.GREEN);
        }else {
            tvStatus.setText("未解决");
            tvStatus.setTextColor(Color.RED);
        }
    }
}
