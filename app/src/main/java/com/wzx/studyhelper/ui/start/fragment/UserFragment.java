package com.wzx.studyhelper.ui.start.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseFragment;
import com.wzx.studyhelper.db.bean.UserIconDB;
import com.wzx.studyhelper.db.impl.FriendsRequestDaoManager;
import com.wzx.studyhelper.db.impl.UserIconDaoManager;
import com.wzx.studyhelper.ui.start.act.LoginActivity;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.ImgSelectUtil;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.glide.GlideDoMain;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment {


    @BindView(R.id.img_user_icon)
    ImageView mImgUserIcon;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;


    public UserFragment() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCircle() {
        setCustomTitle(null,"我的",null);
        UserIconDB userIconDB = UserIconDaoManager.getInstance().queryByUserId(SharedPreferencesUtil.getInstance().getString(Constants.USER_PHONE));
        if (userIconDB!=null){
            GlideDoMain.getInstance().loadCircleImage(getContext(),userIconDB.getUserIcon(),mImgUserIcon);
        }
        mTvNickname.setText("用户昵称:" + SharedPreferencesUtil.getInstance().getString(Constants.USER_NAME));
        mTvPhone.setText("手机号:"+SharedPreferencesUtil.getInstance().getString(Constants.USER_PHONE));
    }


    @OnClick({R.id.btn_loginout, R.id.btn_change_icon,R.id.img_user_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_loginout:
                //清理缓存
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });

                SharedPreferencesUtil.getInstance().clear();
                //跳转登录页面
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.btn_change_icon:
            case R.id.img_user_icon:
                ImgSelectUtil.selectImg(getActivity(), Constants.REQUEST_IMGSEL);
                break;
        }
    }


    public void setImgIcon(final String path) {
        UserIconDB userIconDB = UserIconDaoManager.getInstance().queryByUserId(SharedPreferencesUtil.getInstance().getString(Constants.USER_PHONE));
        userIconDB.setUserIcon(path);
        UserIconDaoManager.getInstance().insertOrReplace(userIconDB);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show("头像上传成功");
                GlideDoMain.getInstance().loadCircleImage(getContext(),path,mImgUserIcon);
            }
        },500);
    }
}
