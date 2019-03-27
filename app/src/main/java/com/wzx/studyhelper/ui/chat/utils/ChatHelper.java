package com.wzx.studyhelper.ui.chat.utils;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.db.impl.FriendsRequestDaoManager;
import com.wzx.studyhelper.utils.Constants;

public class ChatHelper implements EMConnectionListener, EMContactListener {


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
        friendsRequestDB.setReason("");
        friendsRequestDB.setStatus(Constants.WAITING_FRIEND);
        friendsRequestDB.setUsericon(easeUser.getAvatar());
        friendsRequestDB.setUsername(username);
        FriendsRequestDaoManager.getInstance().update(friendsRequestDB);
    }

    @Override
    public void onFriendRequestAccepted(String username) {
        //好友请求被同意
        EaseUser easeUser = new EaseUser(username);
        FriendsRequestDB friendsRequestDB = new FriendsRequestDB();
        friendsRequestDB.setNickname(easeUser.getNickname());
        friendsRequestDB.setReason("");
        friendsRequestDB.setStatus(Constants.ALREADY_FRIEND);
        friendsRequestDB.setUsericon(easeUser.getAvatar());
        friendsRequestDB.setUsername(username);
        FriendsRequestDaoManager.getInstance().update(friendsRequestDB);
    }

    @Override
    public void onFriendRequestDeclined(String username) {
        //好友请求被拒绝
        EaseUser easeUser = new EaseUser(username);
        FriendsRequestDB friendsRequestDB = new FriendsRequestDB();
        friendsRequestDB.setNickname(easeUser.getNickname());
        friendsRequestDB.setReason("");
        friendsRequestDB.setStatus(Constants.REFUSE_FRIEND);
        friendsRequestDB.setUsericon(easeUser.getAvatar());
        friendsRequestDB.setUsername(username);
        FriendsRequestDaoManager.getInstance().update(friendsRequestDB);
    }
}
