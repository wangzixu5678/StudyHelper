package com.wzx.studyhelper.ui.chat.act;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.utils.StringUtil;
import com.wzx.studyhelper.utils.ThreadPoolManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFriendsActivity extends BaseActivity {

    @BindView(R.id.et_friends_account)
    EditText mEtFriendsAccount;
    @BindView(R.id.et_friends_reason)
    EditText mEtFriendsReason;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_friends;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        setBackTitle("添加好友", null);
    }


    @OnClick({R.id.btn_add_friends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_friends:
                //添加好友
                if (StringUtil.isEmpty(mEtFriendsAccount.getText().toString())) {
                    ToastUtils.show("请输入好友账号");
                    return;
                }
                if (StringUtil.isEmpty(mEtFriendsReason.getText().toString())) {
                    ToastUtils.show("请输入添加原因");
                    return;
                }
                ThreadPoolManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().contactManager().addContact(mEtFriendsAccount.getText().toString().trim(), mEtFriendsReason.getText().toString().trim());
                            ToastUtils.show("添加好友成功");
                            finish();
                        } catch (HyphenateException e) {
                            Log.d("AAA", "run: " + e.getMessage());
                            ToastUtils.show("没有搜索到该用户");
                        }
                    }
                });
                break;
        }
    }


}
