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
    private int status;
    @Generated(hash = 1972685984)
    public FriendsRequestDB(String username, String nickname, String usericon,
            String reason, int status) {
        this.username = username;
        this.nickname = nickname;
        this.usericon = usericon;
        this.reason = reason;
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
    public String getReason() {
        return this.reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
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


}
