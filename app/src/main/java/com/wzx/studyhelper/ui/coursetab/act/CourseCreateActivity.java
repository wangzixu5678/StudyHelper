package com.wzx.studyhelper.ui.coursetab.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.coursetab.bean.CourseUploadResultBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.ImgSelectUtil;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.StringUtil;
import com.wzx.studyhelper.utils.glide.GlideDoMain;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CourseCreateActivity extends BaseActivity {

    private String[] mTerms = {"上学期", "下学期"};
    private String[] mGrade = {"大学一年级", "大学二年级", "大学三年级", "大学四年级"};
    private int mCurrentTerm;
    private int mCurrentGrade;
    @BindView(R.id.img_course)
    ImageView mImgCourse;
    @BindView(R.id.tv_grade)
    TextView mTvGrade;
    @BindView(R.id.tv_term)
    TextView mTvTerm;
    private String mObjId;
    private OptionsPickerView<String> mTermPick;
    private OptionsPickerView<String> mGradePick;
    private String mImgPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_create;
    }

    @Override
    protected void initIntent() {
        mObjId = getIntent().getStringExtra(Constants.ID_KEY);
    }

    @Override
    protected void initCircle() {
        setBackTitle("创建课程表", null);
        initGradePick();
        initTermPick();
    }

    private void initTermPick() {
        mTermPick = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvTerm.setText(mTerms[options1]);
                mCurrentTerm = options1 + 1;
            }
        }).setTitleText("请选择学期")
                .build();
        mTermPick.setNPicker(Arrays.asList(mTerms), null, null);
    }

    private void initGradePick() {
        mGradePick = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvGrade.setText(mGrade[options1]);
                mCurrentGrade = options1 + 1;
            }
        }).setTitleText("请选择年级")
                .build();
        mGradePick.setNPicker(Arrays.asList(mGrade), null, null);
    }

    @OnClick({R.id.ll_sel_grade, R.id.ll_sel_term, R.id.img_course,R.id.btn_create_coursetab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sel_grade:
                mGradePick.show();
                break;
            case R.id.ll_sel_term:
                mTermPick.show();
                break;
            case R.id.img_course:
                ImgSelectUtil.selectImg(getActivity(),Constants.REQUEST_IMGSEL);
                break;
            case R.id.btn_create_coursetab:
                if (StringUtil.isEmpty(mImgPath)
                        ||StringUtil.isEmpty(mTvGrade.getText().toString())
                        ||StringUtil.isEmpty(mTvTerm.getText().toString())){

                    ToastUtils.show("请检查填写信息");
                    return;
                }
                upLoadImg(mImgPath);
                break;
        }
    }

    //上传图片
    public void upLoadImg(String filepath) {
        try {
            File compressToFile = new Compressor(getContext()).compressToFile(new File(filepath));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), compressToFile);
            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID))
                    .addFormDataPart("grade", String.valueOf(mCurrentGrade))
                    .addFormDataPart("term", String.valueOf(mCurrentTerm))
                    .addFormDataPart("id",StringUtil.judgeString(mObjId))
                    .addFormDataPart("file", System.currentTimeMillis() + ".jpg", requestFile)
                    .build();
            HttpManager.getInstance().uploadFile(this, requestBody, new ResponseCallback<CourseUploadResultBean>() {
                @Override
                public void onSuccess(CourseUploadResultBean courseUploadResultBean) {
                    ToastUtils.show("课程表创建成功");
                    finish();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == Constants.REQUEST_IMGSEL) {
                mImgPath = Matisse.obtainPathResult(data).get(0);
                GlideDoMain.getInstance().loadImage(this,mImgPath,mImgCourse);
            }
        }
    }

}
