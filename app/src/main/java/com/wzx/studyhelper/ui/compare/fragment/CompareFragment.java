package com.wzx.studyhelper.ui.compare.fragment;


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
import com.google.gson.JsonObject;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.compare.act.CompareDetailActivity;
import com.wzx.studyhelper.ui.compare.adapter.CompareAdapter;
import com.wzx.studyhelper.ui.compare.bean.CompareListBean;
import com.wzx.studyhelper.ui.homework.act.HomeWorkDetailActivity;
import com.wzx.studyhelper.ui.homework.adapter.HomeWorkListAdapter;
import com.wzx.studyhelper.ui.homework.bean.HomeWorkListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompareFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private ArrayList<CompareListBean.DataBean> mDatas;
    private CompareAdapter mCompareAdapter;

    public CompareFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_compare;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {
        setBackTitle("对比功能", null);
        initRv();
        getDateFormNet();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRequestRefresh) {
            getDateFormNet();
            isRequestRefresh = false;
        }
    }

    private void getDateFormNet() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        HttpManager.getInstance().selectByContrast(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                CompareListBean compareListBean = new Gson().fromJson(s, CompareListBean.class);
                mDatas.clear();
                mDatas.addAll(compareListBean.getData());
                mCompareAdapter.notifyDataSetChanged();
            }
        });
    }


    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), OrientationHelper.VERTICAL));
        mCompareAdapter = new CompareAdapter(mDatas);
        mCompareAdapter.setOnItemClickListener(this);
        mCompareAdapter.setEmptyView(getEmptyView());
        mRecyclerview.setAdapter(mCompareAdapter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        isRequestRefresh = true;
        Intent intent = new Intent(getContext(), CompareDetailActivity.class);
        intent.putExtra(Constants.BEAN_KEY, mDatas.get(position));
        startActivity(intent);
    }

    @OnClick({R.id.btn_create_compare})
    public void onBtnClick(View view){
        switch (view.getId()) {
            case R.id.btn_create_compare:
                isRequestRefresh = true;
                Intent intent = new Intent(getContext(), CompareDetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}
