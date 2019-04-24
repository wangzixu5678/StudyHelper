package com.wzx.studyhelper.ui.chat.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.DateUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.glide.GlideDoMain;

import java.util.Date;
import java.util.List;

public class ConversationAdapter extends BaseQuickAdapter<EMConversation,BaseViewHolder> {
    public ConversationAdapter(@Nullable List<EMConversation> data) {
        super(R.layout.conversation_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EMConversation item) {
        //设置未读消息
        TextView mTvMsgNumber = (TextView) helper.getView(R.id.tv_msg_number);
        if (item.getUnreadMsgCount()>0){
            mTvMsgNumber.setVisibility(View.VISIBLE);
            mTvMsgNumber.setText(String.valueOf(item.getUnreadMsgCount()));
        }else {
            mTvMsgNumber.setVisibility(View.GONE);
        }

        String timestampString = DateUtils.getTimestampString(new Date(item.getLastMessage().getMsgTime()));
        helper.setText(R.id.tv_time,timestampString);


        ImageView imgIcon = (ImageView) helper.getView(R.id.img_icon);
        if (item.getLastMessage().direct() == EMMessage.Direct.SEND){
            /**
             * 发送的消息 TO 设置自己的头像 昵称
             */
            try {
                helper.setText(R.id.tv_user_name,item.getLastMessage().getStringAttribute(Constants.OTHERNAME));
                GlideDoMain.getInstance().loadCircleImage(mContext,item.getLastMessage().getStringAttribute(Constants.OTHERICON),imgIcon);
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }else {
            /**
             * 接收的消息 设置别人的头像 昵称
             */
            try {
                helper.setText(R.id.tv_user_name,item.getLastMessage().getStringAttribute(Constants.YOUNAME));
                GlideDoMain.getInstance().loadCircleImage(mContext,item.getLastMessage().getStringAttribute(Constants.YOUICON),imgIcon);
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }

        helper.setText(R.id.tv_short_content, EaseSmileUtils.getSmiledText(mContext, EaseCommonUtils.getMessageDigest(item.getLastMessage(), (mContext))));



        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.rl_content);

    }


}
