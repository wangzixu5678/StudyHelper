package com.wzx.studyhelper.ui.compare.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.compare.adapter.SelectNoteListAdapter;
import com.wzx.studyhelper.ui.note.bean.NoteBean;
import com.wzx.studyhelper.ui.note.bean.NoteListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.StringUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectNoteActivity extends BaseActivity {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private ArrayList<NoteBean> mDatas;
    private SelectNoteListAdapter mSelectNoteListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_note;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        setBackTitle("选择笔记","完成");
        initRv();
        getDateFromNet();
    }

    @Override
    protected void onClickRight() {
        super.onClickRight();
        ArrayList<NoteBean> selList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (NoteBean noteBean : mDatas) {
            if (noteBean.getIsSelected()==1){
                sb.append(noteBean.getId()).append(",");
                selList.add(noteBean);
            }
        }
        String str = sb.toString();
        if (!StringUtil.isEmpty(str)){
            str = str.substring(0,str.length()-1);
        }else {
            ToastUtils.show("请选择您要对比的笔记~");
            return;
        }
        if (str.split(",").length!=2){
            ToastUtils.show("请选择两个笔记进行对比~");
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra(Constants.ID_KEY,str);
        resultIntent.putParcelableArrayListExtra(Constants.BEAN_KEY,selList);
        setResult(RESULT_OK,resultIntent);
        finish();
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
                mSelectNoteListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this,OrientationHelper.VERTICAL));
        mSelectNoteListAdapter = new SelectNoteListAdapter(mDatas);
        mRecyclerview.setAdapter(mSelectNoteListAdapter);
    }


}
