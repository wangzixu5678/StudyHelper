package com.wzx.studyhelper.ui.note.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.note.act.NoteDetailActivity;
import com.wzx.studyhelper.ui.note.adapter.NoteListAdapter;
import com.wzx.studyhelper.ui.note.bean.NoteListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 笔记Fragment
 */
public class NoteFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private NoteListAdapter mNoteListAdapter;
    private ArrayList<NoteListBean.DataBean> mDatas;

    public NoteFragment() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_note;
    }

    @Override
    protected void initArguments(Bundle arguments) {

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
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        HttpManager.getInstance().selectByNote(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                NoteListBean noteListBean = new Gson().fromJson(s,NoteListBean.class);
                mDatas.clear();
                mDatas.addAll(noteListBean.getData());
                mNoteListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initCircle() {
        setCustomTitle(null,"我的笔记",null);
        initRv();
        getDateFromNet();
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mNoteListAdapter = new NoteListAdapter(mDatas);
        mNoteListAdapter.setOnItemClickListener(this);
        mNoteListAdapter.setEmptyView(getEmptyView());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),OrientationHelper.VERTICAL));
        mRecyclerview.setAdapter(mNoteListAdapter);
    }


    @OnClick(R.id.btn_create_note)
    public void onViewClicked() {
        isRequestRefresh = true;
        Intent intent = new Intent(getContext(),NoteDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        isRequestRefresh = true;
        Intent intent = new Intent(getContext(),NoteDetailActivity.class);
        intent.putExtra(Constants.BEAN_KEY,mDatas.get(position));
        startActivity(intent);
    }
}
