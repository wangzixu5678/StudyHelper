package com.wzx.studyhelper.ui.chat.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.exceptions.HyphenateException;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.common.bus.FriendsRequestBus;
import com.wzx.studyhelper.db.bean.UserIconDB;
import com.wzx.studyhelper.db.impl.UserIconDaoManager;
import com.wzx.studyhelper.ui.chat.act.AddFriendsActivity;
import com.wzx.studyhelper.ui.chat.act.FriendRequestActivity;
import com.wzx.studyhelper.ui.chat.adapter.ContactsAdapter;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.SkipUtils;
import com.wzx.studyhelper.utils.ThreadPoolManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 联系人列表
 */
public class ContactsFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_red_point)
    TextView mTvRedPoint;
    private ArrayList<EaseUser> mDatas;
    private ContactsAdapter mContactsAdapter;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {
        initRv();
        initUI();
        getDataFromService();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRequestRefresh){
            getDataFromService();
            isRequestRefresh = false;
        }
    }

    private void initUI() {
        if (SharedPreferencesUtil.getInstance().getBoolean(Constants.HASREQUEST,false)) {
            mTvRedPoint.setVisibility(View.VISIBLE);
        }else {
            mTvRedPoint.setVisibility(View.GONE);
        }
    }


    private void getDataFromService() {
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mDatas.clear();
                    List<String> allContactsFromServer = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    for (String name : allContactsFromServer) {
                        EaseUser easeUser = new EaseUser(name);
                        UserIconDB userIconDB = UserIconDaoManager.getInstance().queryByUserId(easeUser.getUsername());
                        if (userIconDB!=null){
                            easeUser.setAvatar(userIconDB.getUserIcon());
                            easeUser.setNickname(userIconDB.getNickname());
                        }
                        mDatas.add(easeUser);
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mContactsAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),OrientationHelper.VERTICAL));
        mContactsAdapter = new ContactsAdapter(mDatas);
        mContactsAdapter.setOnItemChildClickListener(this);
        mContactsAdapter.setEmptyView(getEmptyView());
        mRecyclerView.setAdapter(mContactsAdapter);
    }





    @OnClick({R.id.rl_content_apply_friends,R.id.rl_content_addfriends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
               //点击好友申请
            case R.id.rl_content_apply_friends:
                isRequestRefresh = true;
                mTvRedPoint.setVisibility(View.GONE);
                SharedPreferencesUtil.getInstance().putBoolean(Constants.HASREQUEST,false);
                Intent intent = new Intent(getContext(),FriendRequestActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_content_addfriends:
                //点击添加好友
                Intent intent1 = new Intent(getContext(),AddFriendsActivity.class);
                startActivity(intent1);
                break;
        }
    }






    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int position) {
        switch (view.getId()) {
            case R.id.tv_delete:
                new AlertDialog.Builder(getContext())
                        .setTitle("确定要删除该好友吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ThreadPoolManager.getInstance().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            EMClient.getInstance().contactManager().deleteContact(mDatas.get(position).getUsername());
                                            ToastUtils.show("删除好友失败");
                                        } catch (HyphenateException e) {
                                            ToastUtils.show("删除好友成功");
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                break;
            case R.id.ll_content:
                UserIconDB userIconDB = UserIconDaoManager.getInstance().queryByUserId(SharedPreferencesUtil.getInstance().getString(Constants.USER_PHONE));
                UserIconDB userIconDB2 = UserIconDaoManager.getInstance().queryByUserId(mDatas.get(position).getUsername());
                if (userIconDB2==null){
                    SkipUtils.goChat(getContext(),mDatas.get(position).getUsername(),userIconDB.getNickname(),userIconDB.getUserIcon(),mDatas.get(position).getUsername(),"");
                }else {
                    SkipUtils.goChat(getContext(),mDatas.get(position).getUsername(),userIconDB.getNickname(),userIconDB.getUserIcon(),userIconDB2.getNickname(),userIconDB2.getUserIcon());
                }
                break;
        }
    }
}
