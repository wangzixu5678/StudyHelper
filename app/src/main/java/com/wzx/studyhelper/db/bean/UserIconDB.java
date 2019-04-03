package com.wzx.studyhelper.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserIconDB {
    @Id
    private String userId;
    private String userIconUrl;
    @Generated(hash = 1442259376)
    public UserIconDB(String userId, String userIconUrl) {
        this.userId = userId;
        this.userIconUrl = userIconUrl;
    }
    @Generated(hash = 770992765)
    public UserIconDB() {
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserIconUrl() {
        return this.userIconUrl;
    }
    public void setUserIconUrl(String userIconUrl) {
        this.userIconUrl = userIconUrl;
    }

}
