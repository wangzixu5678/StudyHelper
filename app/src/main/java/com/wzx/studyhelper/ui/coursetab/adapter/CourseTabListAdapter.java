package com.wzx.studyhelper.ui.coursetab.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.ui.coursetab.bean.CourseQueryResultBean;
import com.wzx.studyhelper.utils.DateUtils;
import com.wzx.studyhelper.utils.TimeUtils;
import com.wzx.studyhelper.utils.glide.GlideDoMain;

import java.util.List;

public class CourseTabListAdapter extends BaseQuickAdapter<CourseQueryResultBean.DataBean, BaseViewHolder> {
    public CourseTabListAdapter(@Nullable List<CourseQueryResultBean.DataBean> data) {
        super(R.layout.coursetab_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseQueryResultBean.DataBean item) {

        ImageView imgCover = (ImageView) helper.getView(R.id.img_cover);
        GlideDoMain.getInstance().loadRoundImage(mContext,item.getUrl(),imgCover);
        helper.setText(R.id.tv_time,"创建时间:" + DateUtils.getFormatTime(item.getCreateTimeDto()));

        if (item.getTerm() == 1) {
            helper.setText(R.id.tv_term, "学期:上学期");
        } else {
            helper.setText(R.id.tv_term, "学期:下学期");
        }

        switch (item.getGrade()) {
            case 1:
                helper.setText(R.id.tv_grade, "年级:大学一年级");
                break;
            case 2:
                helper.setText(R.id.tv_grade, "年级:大学二年级");
                break;
            case 3:
                helper.setText(R.id.tv_grade, "年级:大学三年级");
                break;
            case 4:
                helper.setText(R.id.tv_grade, "年级:大学四年级");
                break;
            default:
                helper.setText(R.id.tv_grade, "年级:未知");
                break;
        }


    }
}
