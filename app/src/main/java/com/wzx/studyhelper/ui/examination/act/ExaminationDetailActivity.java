package com.wzx.studyhelper.ui.examination.act;

import android.app.Dialog;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.JsonObject;
import com.hjq.toast.ToastUtils;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.examination.bean.ExaminationListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.DateUtils;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class ExaminationDetailActivity extends BaseActivity {
    @BindView(R.id.et_course_name)
    TextView mEtCourseName;
    @BindView(R.id.et_place)
    EditText mEtPlace;
    @BindView(R.id.tv_star_time)
    TextView mTvStarTime;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.et_knowledg_detail)
    EditText mEtKnowledgDetail;
    @BindView(R.id.btn_delete_exam)
    Button mBtnDeleteExam;
    @BindView(R.id.btn_change_exam)
    Button mBtnChangeExam;
    private ExaminationListBean.DataBean mDataBean;
    private int mCourseNumber;
    private long mStarDate;
    private long mEndDate;
    private OptionsPickerView<String> mCoursePicker;
    private String[] mCoureses = {
            "统计学",
            "现代统计软件",
            "市场调查与研究学",
            "宏观经济学",
            "金融学",
            "人工智能导论",
            "计算机导论",
            "离散数学",
            "数据结构与算法分析",
            "计算机网络",
            "java程序设计",
            "移动应用程序开发",
            "软件开发工具",
            "信息组织与检索",
            "网络科学原理与应用",
            "数据挖掘技术",
            "电子支付与结算",
            "信息隐藏原理与技术",
            "中国近代史纲要",
            "线性代数"};
    private TimePickerView mTimePickerView;
    private RecognizerDialog mIatDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_examination_detail;
    }

    @Override
    protected void initIntent() {
        mDataBean = getIntent().getParcelableExtra(Constants.BEAN_KEY);
    }

    @Override
    protected void initCircle() {
        initTimePick();
        initCoursePick();
        initYuyin();
        initUI();
    }

    private void initYuyin() {
        mIatDialog = new RecognizerDialog(this, new InitListener() {
            @Override
            public void onInit(int i) {

            }
        });
        mIatDialog.setCanceledOnTouchOutside(false);
        mIatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        mIatDialog.setParameter( SpeechConstant.ENGINE_TYPE,"cloud");
        mIatDialog.setParameter(SpeechConstant.RESULT_TYPE, "plain");
        mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIatDialog.setParameter(SpeechConstant.VAD_BOS, "4000");
        mIatDialog.setParameter(SpeechConstant.ASR_PTT,"1");
        mIatDialog.setParameter(SpeechConstant.VAD_EOS, "1000");
        mIatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                mEtKnowledgDetail.setText(mEtKnowledgDetail.getText() + recognizerResult.getResultString());
                mEtKnowledgDetail.setSelection(mEtKnowledgDetail.length());
            }
            @Override
            public void onError(SpeechError speechError) {

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIatDialog.create();
        }
    }

    private void initUI() {
        if (mDataBean != null) {
            mTvStarTime.setText(DateUtils.getFormatTime2(mDataBean.getStartTimeDto()));
            mTvEndTime.setText(DateUtils.getFormatTime2(mDataBean.getEndTimeDto()));
            mEtPlace.setText(mDataBean.getPlace());
            mEtCourseName.setText(mDataBean.getCourseName());
            mBtnChangeExam.setText("修改考试信息");
            setBackTitle("编辑考试信息", null);
            mCourseNumber = mDataBean.getCourseId();
            mStarDate = mDataBean.getStartTimeDto();
            mEndDate = mDataBean.getEndTimeDto();
            mEtKnowledgDetail.setText(mDataBean.getKnowledgeDescription());
            mBtnDeleteExam.setVisibility(View.VISIBLE);
        } else {
            mBtnDeleteExam.setVisibility(View.GONE);
            mBtnChangeExam.setText("新增考试信息");
            setBackTitle("新增考试信息", null);
        }
    }

    private void initCoursePick() {
        mCoursePicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mEtCourseName.setText(mCoureses[options1]);
                mCourseNumber = options1 + 1;
            }
        }).setTitleText("请选择学期")
                .build();
        mCoursePicker.setNPicker(Arrays.asList(mCoureses), null, null);
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


    @OnClick({R.id.ll_sel_course, R.id.ll_sel_startime, R.id.ll_sel_endtime, R.id.btn_change_exam, R.id.btn_delete_exam,R.id.ll_voice_answer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.ll_sel_course:
//                mCoursePicker.show(view);
//                break;
            case R.id.ll_voice_answer:
                mIatDialog.show();
                break;
            case R.id.ll_sel_startime:
                mTimePickerView.show(view);
                break;
            case R.id.ll_sel_endtime:
                mTimePickerView.show(view);
                break;
            case R.id.btn_change_exam:
                if (mEtCourseName.length() == 0 || mEtPlace.length() == 0 || mTvEndTime.length() == 0 || mTvStarTime.length() == 0){
                    ToastUtils.show("请完成填写内容");
                    return;
                }
                if (mStarDate>mEndDate){
                    ToastUtils.show("开始时间不能大于结束时间");
                    return;
                }
                getDetailFromNet();
                break;
            case R.id.btn_delete_exam:
                deleteWork();
                break;
        }
    }

    private void deleteWork() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleted", 1);
        jsonObject.addProperty("id", mDataBean.getId());
        jsonObject.addProperty("userId", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        HttpManager.getInstance().updateExamination(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ToastUtils.show("删除成功");
                finish();
            }
        });
    }

    private void getDetailFromNet() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("courseName", mEtCourseName.getText().toString().trim());
        jsonObject.addProperty("courseId", String.valueOf(mCourseNumber));
        jsonObject.addProperty("startTimeDto", String.valueOf(mStarDate));
        jsonObject.addProperty("endTimeDto", String.valueOf(mEndDate));
        jsonObject.addProperty("place",mEtPlace.getText().toString().trim());
        jsonObject.addProperty("knowledgeDescription",mEtKnowledgDetail.getText().toString().trim());
        jsonObject.addProperty("userId", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        if (mDataBean!= null) {
            jsonObject.addProperty("id", mDataBean.getId());
        }
        if (mDataBean == null) {
            HttpManager.getInstance().insertExamination(this, jsonObject, new ResponseCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    if (mDataBean == null) {
                        ToastUtils.show("创建成功");
                    }
                    finish();
                }
            });
        } else {
            HttpManager.getInstance().updateExamination(this, jsonObject, new ResponseCallback<String>() {
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


}
