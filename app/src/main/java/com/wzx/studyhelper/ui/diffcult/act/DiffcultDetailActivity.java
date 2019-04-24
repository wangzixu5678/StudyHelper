package com.wzx.studyhelper.ui.diffcult.act;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
import com.wzx.studyhelper.ui.diffcult.bean.DiffcultListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.StringUtil;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class DiffcultDetailActivity extends BaseActivity {



    @BindView(R.id.et_diffcult_answer)
    EditText mEtDiffcultAnswer;
    @BindView(R.id.btn_change_diffcult)
    Button mBtnChangeDiffcult;
    @BindView(R.id.btn_delete_diffcult)
    Button mBtnDeleteDiffcult;
    @BindView(R.id.et_course_name)
    TextView mEtCourseName;
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
    private RecognizerDialog mIatDialog;

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
        initYuyin();
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
                mEtDiffcultAnswer.setText(mEtDiffcultAnswer.getText() + recognizerResult.getResultString());
                mEtDiffcultAnswer.setSelection(mEtDiffcultAnswer.length());
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
            mTvDiffcultName.setText(mDataBean.getDifficultName());
            mEtCourseName.setText(mDataBean.getCourseName());
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
                mEtCourseName.setText(mCoureses[options1]);
                mCourseNumber = options1 + 1;
            }
        }).setTitleText("请选择学期")
                .build();
        mCoursePicker.setNPicker(Arrays.asList(mCoureses), null, null);
    }

    @OnClick({R.id.btn_change_diffcult, R.id.btn_delete_diffcult,R.id.ll_sel_course,R.id.ll_voice_answer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_diffcult:
                if (StringUtil.isEmpty(mEtCourseName.getText().toString())||StringUtil.isEmpty(mTvDiffcultName.getText().toString())){
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
            case R.id.ll_voice_answer:
                mIatDialog.show();
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
        jsonObject.addProperty("courseName", mEtCourseName.getText().toString().trim());
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
