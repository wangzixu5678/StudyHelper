package com.wzx.studyhelper.ui.chat.fragment;

import android.util.Log;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.wzx.studyhelper.utils.Constants;

public class ChatFragment extends EaseChatFragment {

    @Override
    protected void initView() {
        super.initView();
        setChatFragmentHelper(new EaseChatFragmentHelper() {
            @Override
            public void onSetMessageAttributes(EMMessage message) {
                message.setAttribute(Constants.YOUNAME,getArguments().getString(Constants.YOUNAME));
                message.setAttribute(Constants.YOUICON, getArguments().getString(Constants.YOUICON));
                message.setAttribute(Constants.OTHERNAME, getArguments().getString(Constants.OTHERNAME));
                message.setAttribute(Constants.OTHERICON,getArguments().getString(Constants.OTHERICON));
            }

            @Override
            public void onEnterToChatDetails() {

            }

            @Override
            public void onAvatarClick(String username) {

            }

            @Override
            public void onAvatarLongClick(String username) {

            }

            @Override
            public boolean onMessageBubbleClick(EMMessage message) {
                return false;
            }

            @Override
            public void onMessageBubbleLongClick(EMMessage message) {

            }

            @Override
            public boolean onExtendMenuItemClick(int itemId, View view) {
                return false;
            }

            @Override
            public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
                return null;
            }
        });
    }

}
