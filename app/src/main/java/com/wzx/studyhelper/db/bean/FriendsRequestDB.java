package com.wzx.studyhelper.db.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FriendsRequestDB {
    @Id
    private String username;
    private String nickname;
    private String usericon;
    private String reason;
    @NotNull
    private String masterId;
    @NotNull
    private int status;
    @Generated(hash = 1352113134)
    public FriendsRequestDB(String username, String nickname, String usericon,
            String reason, @NotNull String masterId, int status) {
        this.username = username;
        this.nickname = nickname;
        this.usericon = usericon;
        this.reason = reason;
        this.masterId = masterId;
        this.status = status;
    }
    @Generated(hash = 1178192543)
    public FriendsRequestDB() {
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUsericon() {
        return this.usericon;
    }
    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }
    public String getReason() {
        return this.reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getMasterId() {
        return this.masterId;
    }
    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }


}
