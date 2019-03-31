package com.wzx.studyhelper.ui.coursetab.act;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.coursetab.bean.CourseUploadResultBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.StringUtil;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CourseDetailActivity extends BaseActivity {
    @BindView(R.id.photoView)
    PhotoView mPhotoView;
    @BindView(R.id.progressView)
    ProgressBar mProgressView;
    private String mImgUrl;
    private String mObjId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initIntent() {
        mImgUrl = getIntent().getStringExtra(Constants.URL_KEY);
        mObjId = getIntent().getStringExtra(Constants.ID_KEY);

        Log.d("AAA", "initIntent: " + mObjId);
    }
    @Override
    protected void initCircle() {
        setBackTitle("课程表详情",null);

        Glide.with(getContext())
                .load(mImgUrl)
                .skipMemoryCache(false)
                .placeholder(Color.parseColor("#666666"))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        mProgressView.setVisibility(View.GONE);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mProgressView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mPhotoView);

    }

    @OnClick(R.id.btn_delete)
    public void onDeleteClick(View view){
        deleteCourse();
    }


    public void deleteCourse() {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID))
                .addFormDataPart("id",StringUtil.judgeString(mObjId))
                .addFormDataPart("deleted","1")
                .build();
        HttpManager.getInstance().uploadFile(this, requestBody, new ResponseCallback<CourseUploadResultBean>() {
            @Override
            public void onSuccess(CourseUploadResultBean courseUploadResultBean) {
                ToastUtils.show("课程表删除成功");
                finish();
            }
        });
    }
}
