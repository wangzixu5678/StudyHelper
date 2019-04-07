package com.wzx.studyhelper.ui.schedule.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.wzx.studyhelper.ui.examination.act.ExaminationDetailActivity;
import com.wzx.studyhelper.ui.examination.adapter.ExaminationAdapter;
import com.wzx.studyhelper.ui.schedule.act.ScheduleDetailActivity;
import com.wzx.studyhelper.ui.schedule.adapter.ScheduleAdapter;
import com.wzx.studyhelper.ui.schedule.bean.ScheduleListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ScheduleFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    public ScheduleFragment() {

    }

    @BindView(R.id.floatbutton)
    FloatingActionButton mFab;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private ArrayList<ScheduleListBean.DataBean> mDatas;
    private ScheduleAdapter mScheduleAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_schedule;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {
        setBackTitle("我的计划",null);
        initRv();
        initUI();
        getDateFormNet();
    }

    private void getDateFormNet() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        HttpManager.getInstance().selectBySchedule(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ScheduleListBean scheduleListBean = new Gson().fromJson(s, ScheduleListBean.class);
                mDatas.clear();
                mDatas.addAll(scheduleListBean.getData());
                mScheduleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRequestRefresh){
            getDateFormNet();
            isRequestRefresh = false;
        }
    }

    private void initUI() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(mFab,"点击蓝色按钮可添加我的计划哦~", Snackbar.LENGTH_SHORT).show();
            }
        },500);
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),OrientationHelper.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mScheduleAdapter = new ScheduleAdapter(mDatas);
        mScheduleAdapter.setOnItemClickListener(this);
        mScheduleAdapter.setEmptyView(getEmptyView());
        mRecyclerView.setAdapter(mScheduleAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    mFab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 ||dy<0 && mFab.isShown())
                {
                    mFab.hide();
                }
            }
        });
    }

    @OnClick({R.id.floatbutton})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.floatbutton:
                isRequestRefresh = true;
                Intent intent = new Intent(getContext(),ScheduleDetailActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        isRequestRefresh = true;
        Intent intent = new Intent(getContext(),ScheduleDetailActivity.class);
        intent.putExtra(Constants.BEAN_KEY,mDatas.get(position));
        startActivity(intent);
    }
}
