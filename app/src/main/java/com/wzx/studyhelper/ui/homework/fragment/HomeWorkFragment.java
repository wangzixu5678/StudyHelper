package com.wzx.studyhelper.ui.homework.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.homework.act.HomeWorkDetailActivity;
import com.wzx.studyhelper.ui.homework.adapter.HomeWorkListAdapter;
import com.wzx.studyhelper.ui.homework.bean.HomeWorkListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeWorkFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private ArrayList<HomeWorkListBean.DataBean> mDatas;
    private HomeWorkListAdapter mHomeWorkListAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_work;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {
        setCustomTitle(null,"我的作业",null);
        initRv();
        getDateFromNet();
    }

    @Override
    public void onResume() {
        super.onResume();
       if (isRequestRefresh){
           getDateFromNet();
           isRequestRefresh = false;
       }
    }

    private void getDateFromNet() {
        HttpManager.getInstance().selectByoperation(this, "", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HomeWorkListBean homeWorkListBean = new Gson().fromJson(s, HomeWorkListBean.class);
                if (homeWorkListBean!=null){
                    mDatas.clear();
                    mDatas.addAll(homeWorkListBean.getData());
                    mHomeWorkListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),OrientationHelper.VERTICAL));
        mHomeWorkListAdapter = new HomeWorkListAdapter(mDatas);
        mHomeWorkListAdapter.setOnItemClickListener(this);
        mHomeWorkListAdapter.setEmptyView(getEmptyView());
        mRecyclerview.setAdapter(mHomeWorkListAdapter);
    }

    @OnClick(R.id.btn_create_homework)
    public void onViewClicked() {
        isRequestRefresh = true;
        Intent intent = new Intent(getContext(),HomeWorkDetailActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        isRequestRefresh = true;
        Intent intent = new Intent(getContext(),HomeWorkDetailActivity.class);
        intent.putExtra(Constants.BEAN_KEY,mDatas.get(position));
        startActivity(intent);
    }
}
