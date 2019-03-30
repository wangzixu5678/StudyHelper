package com.wzx.studyhelper.ui.start.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.coursetab.bean.CourseUploadResultBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.ImgSelectUtil;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.TimeUtils;
import com.zhihu.matisse.Matisse;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    Unbinder unbinder;

    public HomeFragment() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {

    }




    @OnClick(R.id.btn_sel_img)
    public void onViewClicked() {
        ImgSelectUtil.selectImg(getActivity(),Constants.REQUEST_IMGSEL);
    }

    //上传图片
    public void upLoadImg(String filepath) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),new File(filepath));
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID))
                .addFormDataPart("grade", "1年级")
                .addFormDataPart("term", "下学期")
                .addFormDataPart("id","")
                .addFormDataPart("file",System.currentTimeMillis()+".jpg",requestFile)
                .build();

        HttpManager.getInstance().uploadFile(this,requestBody, new ResponseCallback<CourseUploadResultBean>() {
                    @Override
                    public void onSuccess(CourseUploadResultBean courseUploadResultBean) {
                        ToastUtils.show("课程表创建成功" +courseUploadResultBean.toString());
                    }
                });
    }
}
