package com.wzx.studyhelper.ui.chat.act;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.githang.statusbar.StatusBarCompat;
import com.hyphenate.easeui.EaseConstant;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.ui.chat.fragment.ChatFragment;
import com.wzx.studyhelper.utils.Constants;

public class ChatActivity extends BaseActivity {


    private int mChatType;
    private String mChatId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initIntent() {
        mChatType = getIntent().getIntExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        mChatId = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
    }
    @Override
    protected void initCircle() {
        ChatFragment chatFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE,mChatType);
        bundle.putString(EaseConstant.EXTRA_USER_ID,mChatId);
        bundle.putString(Constants.YOUNAME,getIntent().getStringExtra(Constants.YOUNAME));
        bundle.putString(Constants.YOUICON,getIntent().getStringExtra(Constants.YOUICON));
        bundle.putString(Constants.OTHERNAME,getIntent().getStringExtra(Constants.OTHERNAME));
        bundle.putString(Constants.OTHERICON,getIntent().getStringExtra(Constants.OTHERICON));

        chatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fm_content,chatFragment).commit();
    }

    @Override
    protected void setStatusBar() {
        StatusBarCompat.setStatusBarColor(this,Color.parseColor("#00acff"));
    }
}
