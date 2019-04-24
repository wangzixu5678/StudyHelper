package com.wzx.studyhelper.ui.coursetab.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

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
import com.wzx.studyhelper.ui.start.act.HomeActivity;
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


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private ArrayList<CourseQueryResultBean.DataBean> mDatas;
    private CourseTabListAdapter mCourseTabListAdapter;
    private DrawerLayout mDrawerLayout;


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
        initDrawerLayout();
        initRv();
        getDataFromServer();
    }

    private void initDrawerLayout() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerLayout = ((HomeActivity) getActivity()).getDrawerLayout();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, 0, 0);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), OrientationHelper.VERTICAL));
        mCourseTabListAdapter = new CourseTabListAdapter(mDatas);
        mCourseTabListAdapter.setEmptyView(getEmptyView());
        mCourseTabListAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mCourseTabListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRequestRefresh) {
            getDataFromServer();
            isRequestRefresh = false;
        }
    }

    private void getDataFromServer() {
        HttpManager.getInstance().queryByScheduleCard(this, SharedPreferencesUtil.getInstance().getString(Constants.USER_ID), "", "", new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                CourseQueryResultBean courseQueryResultBean = new Gson().fromJson(s, CourseQueryResultBean.class);
                mDatas.clear();
                mDatas.addAll(courseQueryResultBean.getData());
                mCourseTabListAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.btn_create_course)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_course:
                isRequestRefresh = true;
                Intent intent = new Intent(getContext(), CourseCreateActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mDatas.get(position) != null) {
            isRequestRefresh = true;
            ImageView imgCover = (ImageView) view.findViewById(R.id.img_cover);
            Intent intent = new Intent(getContext(), CourseDetailActivity.class);
            intent.putExtra(Constants.URL_KEY, mDatas.get(position).getUrl());
            intent.putExtra(Constants.ID_KEY, mDatas.get(position).getId());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation
                                (getActivity(),imgCover,"animimg")
                                .toBundle());
            }else {
                startActivity(intent);
            }

        }
    }
}
