package com.wzx.studyhelper.ui.chat.utils;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.domain.EaseUser;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.db.impl.FriendsRequestDaoManager;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ChatHelper implements EMConnectionListener, EMContactListener, EMMessageListener {


    private static ChatHelper instance = null;
    public synchronized static ChatHelper getInstance() {
        if (instance == null) {
            instance = new ChatHelper();
        }
        return instance;
    }

    public void init(){
        initListener();
    }

    private void initListener() {
        //连接断开监听
        EMClient.getInstance().addConnectionListener(this);
        //好友状态监听
        EMClient.getInstance().contactManager().setContactListener(this);

        EMClient.getInstance().chatManager().addMessageListener(this);
    }



    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected(int i) {

    }

    @Override
    public void onContactAdded(String username) {
        //增加了联系人时回调此方法
    }

    @Override
    public void onContactDeleted(String username) {
        //被删除时回调此方法
    }

    @Override
    public void onContactInvited(String username, String reason) {
        //收到好友邀请
        EaseUser easeUser = new EaseUser(username);
        FriendsRequestDB friendsRequestDB = new FriendsRequestDB();
        friendsRequestDB.setNickname(easeUser.getNickname());
        friendsRequestDB.setReason(reason);
        friendsRequestDB.setStatus(Constants.WAITING_FRIEND);
        friendsRequestDB.setUsericon(easeUser.getAvatar());
        friendsRequestDB.setUsername(username);
        friendsRequestDB.setMasterId(SharedPreferencesUtil.getInstance().getString(Constants.USER_PHONE));
        FriendsRequestDaoManager.getInstance().update(friendsRequestDB);
        SharedPreferencesUtil.getInstance().putBoolean(Constants.HASREQUEST,true);
        SharedPreferencesUtil.getInstance().putBoolean(Constants.HASHOMEREQUEST,true);
    }

    @Override
    public void onFriendRequestAccepted(String username) {
    }

    @Override
    public void onFriendRequestDeclined(String username) {
    }

    @Override
    public void onMessageReceived(List<EMMessage> list) {
        SharedPreferencesUtil.getInstance().putBoolean(Constants.HASHOMEREQUEST,true);
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }
}
