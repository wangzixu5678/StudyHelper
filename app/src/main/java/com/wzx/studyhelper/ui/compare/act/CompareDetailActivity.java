package com.wzx.studyhelper.ui.compare.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.ui.compare.adapter.CompareDetailNoteAdapter;
import com.wzx.studyhelper.ui.compare.bean.CompareListBean;
import com.wzx.studyhelper.ui.note.bean.NoteBean;
import com.wzx.studyhelper.ui.note.bean.NoteListBean;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompareDetailActivity extends BaseActivity {


    @BindView(R.id.et_compare_name)
    EditText mEtCompareName;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.btn_select_compare)
    Button mBtnSelectCompare;
    @BindView(R.id.btn_delete_compare)
    Button mBtnDeleteCompare;

    private CompareListBean.DataBean mDataBean;
    private CompareDetailNoteAdapter mCompareDetailNoteAdapter;
    private ArrayList<NoteBean> mDatas;
    private String mIds;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_compare_detail;
    }

    @Override
    protected void initIntent() {
        mDataBean = getIntent().getParcelableExtra(Constants.BEAN_KEY);
    }

    @Override
    protected void initCircle() {

        initRv();
        initUI();
        if (mDataBean!=null){
            setBackTitle("笔记对比详情",null);
            mEtCompareName.setText(mDataBean.getContrastName());
            mDatas.clear();
            mDatas.addAll(mDataBean.getNotes());
            mCompareDetailNoteAdapter.notifyDataSetChanged();
            mBtnSelectCompare.setVisibility(View.GONE);
        }else {
            setBackTitle("笔记对比详情","完成");
            mBtnSelectCompare.setVisibility(View.VISIBLE);
        }
    }

    private void initUI() {

    }

    @Override
    protected void onClickRight() {
        super.onClickRight();
        if (StringUtil.isEmpty(mEtCompareName.getText().toString()) || StringUtil.isEmpty(mIds)){
            ToastUtils.show("请填写对比名称并且选择两个对比笔记");
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("contrastName",mEtCompareName.getText().toString());
        jsonObject.addProperty("userId",SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        jsonObject.addProperty("noteIds",mIds);
        HttpManager.getInstance().insertContrast(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ToastUtils.show("新增对比成功");
                finish();
            }
        });
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mCompareDetailNoteAdapter = new CompareDetailNoteAdapter(mDatas);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mCompareDetailNoteAdapter);
    }


    @OnClick({R.id.btn_select_compare, R.id.btn_delete_compare})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_select_compare:
                Intent intent = new Intent(this,SelectNoteActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.btn_delete_compare:
                deleteWork();
                break;
        }
    }


    private void deleteWork() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleted", 1);
        jsonObject.addProperty("id", mDataBean.getId());
        jsonObject.addProperty("userId", SharedPreferencesUtil.getInstance().getString(Constants.USER_ID));
        HttpManager.getInstance().updateContrast(this, jsonObject, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ToastUtils.show("删除成功");
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    mIds = data.getStringExtra(Constants.ID_KEY);
                    ArrayList<NoteBean> list = data.getParcelableArrayListExtra(Constants.BEAN_KEY);
                    mDatas.clear();
                    mDatas.addAll(list);
                    mCompareDetailNoteAdapter.notifyDataSetChanged();
                    mBtnSelectCompare.setVisibility(View.GONE);
                    break;
            }
        }
    }
}
