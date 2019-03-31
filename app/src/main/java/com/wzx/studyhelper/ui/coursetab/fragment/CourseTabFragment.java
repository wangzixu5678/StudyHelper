package com.wzx.studyhelper.ui.coursetab.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.coursetab.act.CourseCreateActivity;
import com.wzx.studyhelper.ui.coursetab.act.CourseDetailActivity;
import com.wzx.studyhelper.ui.coursetab.adapter.CourseTabListAdapter;
import com.wzx.studyhelper.ui.coursetab.bean.CourseQueryResultBean;
import com.wzx.studyhelper.ui.coursetab.bean.CourseUploadResultBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.ImgSelectUtil;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseTabFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private ArrayList<CourseQueryResultBean.DataBean> mDatas;
    private CourseTabListAdapter mCourseTabListAdapter;

    public CourseTabFragment() {

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
        setCustomTitle(null,"我的课程表",null);
        initRv();

    }
    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),OrientationHelper.VERTICAL));
        mCourseTabListAdapter = new CourseTabListAdapter(mDatas);
        mCourseTabListAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mCourseTabListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
    }

    private void getDataFromServer() {
        HttpManager.getInstance().queryByScheduleCard(this, SharedPreferencesUtil.getInstance().getString(Constants.USER_ID), "", "", new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                CourseQueryResultBean courseQueryResultBean = new Gson().fromJson(s,CourseQueryResultBean.class);
                mDatas.clear();
                mDatas.addAll(courseQueryResultBean.getData());
                mCourseTabListAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.btn_create_course)
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.btn_create_course:
                Intent intent = new Intent(getContext(),CourseCreateActivity.class);
                startActivity(intent);
                break;
        }
    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position){
        if (mDatas.get(position)!=null){
            Intent intent = new Intent(getContext(),CourseDetailActivity.class);
            intent.putExtra(Constants.URL_KEY,mDatas.get(position).getUrl());
            intent.putExtra(Constants.ID_KEY,mDatas.get(position).getId());
            startActivity(intent);
        }
    }
}
