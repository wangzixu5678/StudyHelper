package com.wzx.studyhelper.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.wzx.studyhelper.db.auto.DaoSession;
import com.wzx.studyhelper.db.auto.FriendsRequestDBDao;
import com.wzx.studyhelper.db.auto.UserIconDBDao;

@Entity
public class UserIconDB {
    @Id
    private String phone;
    private String nickname;
    private String userIcon;
    private String imgCover;
    @ToMany(referencedJoinProperty = "masterId")
    private List<FriendsRequestDB> mFriendsRequestDBList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1935362748)
    private transient UserIconDBDao myDao;
    @Generated(hash = 1705259850)
    public UserIconDB(String phone, String nickname, String userIcon, String imgCover) {
        this.phone = phone;
        this.nickname = nickname;
        this.userIcon = userIcon;
        this.imgCover = imgCover;
    }
    @Generated(hash = 770992765)
    public UserIconDB() {
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUserIcon() {
        return this.userIcon;
    }
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2072419029)
    public List<FriendsRequestDB> getMFriendsRequestDBList() {
        if (mFriendsRequestDBList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FriendsRequestDBDao targetDao = daoSession.getFriendsRequestDBDao();
            List<FriendsRequestDB> mFriendsRequestDBListNew = targetDao
                    ._queryUserIconDB_MFriendsRequestDBList(phone);
            synchronized (this) {
                if (mFriendsRequestDBList == null) {
                    mFriendsRequestDBList = mFriendsRequestDBListNew;
                }
            }
        }
        return mFriendsRequestDBList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1377661262)
    public synchronized void resetMFriendsRequestDBList() {
        mFriendsRequestDBList = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1836200682)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserIconDBDao() : null;
    }
    public String getImgCover() {
        return this.imgCover;
    }
    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }


 
}
