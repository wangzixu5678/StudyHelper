package com.wzx.studyhelper.ui.diffcult.act;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.JsonObject;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.diffcult.bean.DiffcultListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.DateUtils;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.StringUtil;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiffcultDetailActivity extends BaseActivity {



    @BindView(R.id.et_diffcult_answer)
    EditText mEtDiffcultAnswer;
    @BindView(R.id.btn_change_diffcult)
    Button mBtnChangeDiffcult;
    @BindView(R.id.btn_delete_diffcult)
    Button mBtnDeleteDiffcult;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.et_diffcult_name)
    TextView mTvDiffcultName;
    @BindView(R.id.rg)
    RadioGroup mRadioGroup;
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
    private DiffcultListBean.DataBean mDataBean;
    private int mCourseNumber;
    private OptionsPickerView<String> mCoursePicker;
    private int mCurrentDealStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diffcult_detail;
    }

    @Override
    protected void initIntent() {
        mDataBean = getIntent().getParcelableExtra(Constants.BEAN_KEY);
    }

    @Override
    protected void initCircle() {
        initCoursePick();
        initUI();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_ok:
                        mCurrentDealStatus = 1;
                        break;
                    case R.id.rb_unok:
                        mCurrentDealStatus = 0;
                        break;
                }
            }
        });
    }


    private void initUI() {
        if (mDataBean != null) {
            mTvDiffcultName.setText(mDataBean.getDifficultName());
            mTvCourseName.setText(mDataBean.getCourseName());
            mBtnChangeDiffcult.setText("修改难点信息");
            setBackTitle("编辑难点信息", null);
            mCourseNumber = mDataBean.getCourseId();
            mEtDiffcultAnswer.setText(mDataBean.getDifficultAnswers());
            mBtnDeleteDiffcult.setVisibility(View.VISIBLE);
            mCurrentDealStatus = mDataBean.getDealStatus();
            if (mCurrentDealStatus==0){
                mRadioGroup.check(R.id.rb_unok);
            }else {
                mRadioGroup.check(R.id.rb_ok);
            }
        } else {
            mCurrentDealStatus = 0;
            mRadioGroup.check(R.id.rb_unok);
            mBtnDeleteDiffcult.setVisibility(View.GONE);
            mBtnChangeDiffcult.setText("新增难点信息");
            setBackTitle("新增难点信息", null);
        }
    }


    private void initCoursePick() {
        mCoursePicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvCourseName.setText(mCoureses[options1]);
                mCourseNumber = options1 + 1;
            }
        }).setTitleText("请选择学期")
                .build();
        mCoursePicker.setNPicker(Arrays.asList(mCoureses), null, null);
    }

    @OnClick({R.id.btn_change_diffcult, R.id.btn_delete_diffcult,R.id.ll_sel_course})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_diffcult:
                if (StringUtil.isEmpty(mTvCourseName.getText().toString())||StringUtil.isEmpty(mTvDiffcultName.getText().toString())){
                    ToastUtils.show("请填写必要信息");
                    return;
                }
                postDetailToNet();
                break;
            case R.id.btn_delete_diffcult:
                deleteWork();
                break;
            case R.id.ll_sel_course:
                mCoursePicker.show();
                break;
        }
    }

    private void deleteWork() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleted", 1);
        jsonObject.addProperty("id", mDataBean.getId());
        jsonObject.addProperty("userId", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        HttpManager.getInstance().updateDifficult(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ToastUtils.show("删除成功");
                finish();
            }
        });
    }

    private void postDetailToNet() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("courseName", mTvCourseName.getText().toString().trim());
        jsonObject.addProperty("courseId", String.valueOf(mCourseNumber));
        jsonObject.addProperty("difficultName",mTvDiffcultName.getText().toString().trim());
        jsonObject.addProperty("difficultAnswers",mEtDiffcultAnswer.getText().toString().trim());
        jsonObject.addProperty("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        jsonObject.addProperty("dealStatus",mCurrentDealStatus);
        if (mDataBean!= null) {
            jsonObject.addProperty("id", mDataBean.getId());
        }
        if (mDataBean == null) {
            HttpManager.getInstance().insertDifficult(this, jsonObject, new ResponseCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    if (mDataBean == null) {
                        ToastUtils.show("创建成功");
                    }
                    finish();
                }
            });
        } else {
            HttpManager.getInstance().updateDifficult(this, jsonObject, new ResponseCallback<String>() {
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
