package com.wzx.studyhelper.ui.schedule.act;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.JsonObject;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.schedule.bean.ScheduleListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.DateUtils;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScheduleDetailActivity extends BaseActivity {


    @BindView(R.id.tv_scheduletype)
    TextView mTvScheduletype;
    @BindView(R.id.tv_star_time)
    TextView mTvStarTime;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.btn_change_schedule)
    Button mBtnChangeSchedule;
    @BindView(R.id.btn_delete_schedule)
    Button mBtnDeleteSchedule;
    @BindView(R.id.et_schedulename)
    EditText mEtScheduleName;
    private ScheduleListBean.DataBean mDataBean;
    private long mStarDate;
    private long mEndDate;
    private TimePickerView mTimePickerView;
    private String[] mScheduleType = {"日常学习计划","考试复习计划"};
    private int mScheduleTypeNumber;
    private OptionsPickerView<String> mScheduleTypePicker;
    private int mCurrentStatus = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_schedule_detail;
    }

    @Override
    protected void initIntent() {
        mDataBean = getIntent().getParcelableExtra(Constants.BEAN_KEY);
    }

    @Override
    protected void initCircle() {
        initTimePick();
        initScheduleType();
        initUI();
    }

    private void initScheduleType() {
        mScheduleTypePicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvScheduletype.setText(mScheduleType[options1]);
                mScheduleTypeNumber = options1 + 1;
            }
        }).setTitleText("请选择计划类型")
                .build();
        mScheduleTypePicker.setNPicker(Arrays.asList(mScheduleType), null, null);
    }

    private void initUI() {
        if (mDataBean != null) {
            if (mDataBean.getScheduleType()==1){
                mTvScheduletype.setText("日常学习计划");
            }else {
                mTvScheduletype.setText("考试复习计划");
            }
            mScheduleTypeNumber = mDataBean.getScheduleType();
            mTvStarTime.setText(DateUtils.getFormatTime2(mDataBean.getStartTimeDto()));
            mTvEndTime.setText(DateUtils.getFormatTime2(mDataBean.getEndTimeDto()));
            mEtScheduleName.setText(mDataBean.getScheduleName());
            mBtnChangeSchedule.setText("修改我的计划");
            setBackTitle("编辑我的计划", null);
            mStarDate = mDataBean.getStartTimeDto();
            mEndDate = mDataBean.getEndTimeDto();
            mTvStarTime.setText(DateUtils.getFormatTime2(mDataBean.getStartTimeDto()));
            mTvEndTime.setText(DateUtils.getFormatTime2(mDataBean.getEndTimeDto()));
            mBtnDeleteSchedule.setVisibility(View.VISIBLE);
            mCurrentStatus = mDataBean.getCompletionSchedule();
            switch (mCurrentStatus) {
                case 1:
                    mRg.check(R.id.rb_ready);
                    break;
                case 2:
                    mRg.check(R.id.rb_ok);
                    break;
                case 3:
                    mRg.check(R.id.rb_undate);
                    break;
                case 4:
                    mRg.check(R.id.rb_laji);
                    break;

            }
        } else {
            mCurrentStatus = 1;
            mRg.check(R.id.rb_ready);
            mBtnDeleteSchedule.setVisibility(View.GONE);
            mBtnChangeSchedule.setText("新增我的计划");
            setBackTitle("新增我的计划", null);
        }
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.rb_ready:
                        mCurrentStatus = 1;
                        break;
                    case R.id.rb_ok:
                        mCurrentStatus = 2;
                        break;
                    case R.id.rb_undate:
                        mCurrentStatus = 3;
                        break;
                    case R.id.rb_laji:
                        mCurrentStatus = 4;
                        break;
                }
            }
        });

    }



    private void initTimePick() {
        mTimePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                switch (v.getId()) {
                    case R.id.ll_sel_startime:
                        mStarDate = date.getTime();
                        mTvStarTime.setText(DateUtils.getFormatTime2(date.getTime()));
                        break;
                    case R.id.ll_sel_endtime:
                        mEndDate = date.getTime();
                        mTvEndTime.setText(DateUtils.getFormatTime2(date.getTime()));
                        break;
                }
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("请选择时间")
                .isDialog(true)
                .build();
        Dialog mDialog = mTimePickerView.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            mTimePickerView.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }


    @OnClick({R.id.ll_sel_scheduletype, R.id.ll_sel_startime, R.id.ll_sel_endtime, R.id.btn_change_schedule, R.id.btn_delete_schedule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sel_scheduletype:
                mScheduleTypePicker.show();
                break;
            case R.id.ll_sel_startime:
                mTimePickerView.show(view);
                break;
            case R.id.ll_sel_endtime:
                mTimePickerView.show(view);
                break;
            case R.id.btn_change_schedule:

                if (mEtScheduleName.length() == 0 || mTvScheduletype.length() == 0 || mTvEndTime.length() == 0 || mTvStarTime.length() == 0){
                    ToastUtils.show("请完成填写内容");
                    return;
                }
                if (mStarDate>mEndDate){
                    ToastUtils.show("开始时间不能大于结束时间");
                    return;
                }

                getDetailFromNet();
                break;
            case R.id.btn_delete_schedule:
                deleteWork();
                break;
        }
    }

    private void getDetailFromNet() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("scheduleName",mEtScheduleName.getText().toString().trim());
        jsonObject.addProperty("scheduleType",mScheduleTypeNumber);
        jsonObject.addProperty("completionSchedule",mCurrentStatus);
        jsonObject.addProperty("startTimeDto", String.valueOf(mStarDate));
        jsonObject.addProperty("endTimeDto", String.valueOf(mEndDate));
        jsonObject.addProperty("userId", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        if (mDataBean!= null) {
            jsonObject.addProperty("id", mDataBean.getId());
        }
        if (mDataBean == null) {
            HttpManager.getInstance().insertSchedule(this, jsonObject, new ResponseCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    if (mDataBean == null) {
                        ToastUtils.show("创建成功");
                    }
                    finish();
                }
            });
        } else {
            HttpManager.getInstance().updateSchedule(this, jsonObject, new ResponseCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    if (mDataBean == null) {
                        ToastUtils.show("修改成功");
                    }
                    finish();
                }
            });
        }
    }
    private void deleteWork() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleted", 1);
        jsonObject.addProperty("id", mDataBean.getId());
        jsonObject.addProperty("userId", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        HttpManager.getInstance().updateSchedule(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ToastUtils.show("删除成功");
                finish();
            }
        });
    }
}
