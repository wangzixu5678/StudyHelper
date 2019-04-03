package com.wzx.studyhelper.ui.note.act;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.wzx.studyhelper.ui.note.bean.NoteListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.InputMethodManagerUtils;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteDetailActivity extends BaseActivity {


    @BindView(R.id.et_knowledgename)
    EditText mEtKnowledgename;
    @BindView(R.id.et_knowledg_answer)
    EditText mEtKnowledgAnswer;
    @BindView(R.id.et_my_mind)
    EditText mEtMyMind;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.btn_change_work)
    Button mBtnChangeWork;
    @BindView(R.id.btn_delete_note)
    Button mBtnDeleteNote;
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
    private NoteListBean.DataBean mDataBean;
    private OptionsPickerView<String> mCoursePicker;
    private int mCourseNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_create;
    }

    @Override
    protected void initIntent() {
        mDataBean = getIntent().getParcelableExtra(Constants.BEAN_KEY);
    }

    @Override
    protected void initCircle() {
        initUI();
        initCoursePick();
        getDateFromNet();
    }

    private void initUI() {
        if (mDataBean != null) {
            setBackTitle("编辑笔记", null);
            mBtnChangeWork.setText("修改笔记内容");
            mEtKnowledgename.setText(mDataBean.getKnowledgeName());
            mEtKnowledgAnswer.setText(mDataBean.getKnowledgeAnswer());
            mTvCourseName.setText(mDataBean.getCourseName());
            mEtMyMind.setText(mDataBean.getPersonalExperience());
            mBtnDeleteNote.setVisibility(View.VISIBLE);
        } else {
            mBtnChangeWork.setText("新增笔记内容");
            setBackTitle("新增笔记", null);
            mBtnDeleteNote.setVisibility(View.GONE);
        }

    }

    private void getDateFromNet() {

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

    @OnClick({R.id.ll_sel_course, R.id.btn_change_work, R.id.btn_delete_note})
    public void onViewClicked(View view) {
        InputMethodManagerUtils.hideSoftInput(this,view);
        switch (view.getId()) {
            case R.id.ll_sel_course:
                mCoursePicker.show();
                break;
            case R.id.btn_change_work:
                if (mDataBean==null){
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
                    jsonObject.addProperty("courseId",mCourseNumber);
                    jsonObject.addProperty("courseName",mTvCourseName.getText().toString().trim());
                    jsonObject.addProperty("knowledgeName",mEtKnowledgename.getText().toString().trim());
                    jsonObject.addProperty("knowledgeAnswer",mEtKnowledgAnswer.getText().toString().trim());
                    jsonObject.addProperty("personalExperience",mEtMyMind.getText().toString().trim());
                    HttpManager.getInstance().insertnote(this, jsonObject, new ResponseCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            ToastUtils.show("创建笔记成功");
                            finish();
                        }
                    });
                }else {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id",mDataBean.getId());
                    jsonObject.addProperty("courseName",mTvCourseName.getText().toString().trim());
                    jsonObject.addProperty("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
                    jsonObject.addProperty("courseId",mCourseNumber);
                    jsonObject.addProperty("knowledgeName",mEtKnowledgename.getText().toString().trim());
                    jsonObject.addProperty("knowledgeAnswer",mEtKnowledgAnswer.getText().toString().trim());
                    jsonObject.addProperty("personalExperience",mEtMyMind.getText().toString().trim());
                    HttpManager.getInstance().updatenote(this, jsonObject, new ResponseCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            ToastUtils.show("修改笔记成功");
                            finish();
                        }
                    });
                }

                break;
            case R.id.btn_delete_note:
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id",mDataBean.getId());
                jsonObject.addProperty("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
                jsonObject.addProperty("deleted", 1);
                HttpManager.getInstance().updatenote(this, jsonObject, new ResponseCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ToastUtils.show("删除笔记成功");
                        finish();
                    }
                });
                break;
        }
    }
}
