package com.wzx.studyhelper.db.impl;

import android.util.Log;

import com.wzx.studyhelper.db.auto.FriendsRequestDBDao;
import com.wzx.studyhelper.db.auto.UserDBDao;
import com.wzx.studyhelper.db.auto.UserIconDBDao;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.db.bean.UserIconDB;
import com.wzx.studyhelper.db.utils.DaoManager;

import java.util.List;

public class UserIconDaoManager {

    private static UserIconDaoManager sUserIconDaoManager = new UserIconDaoManager();
    private final UserIconDBDao mUserIconDBDao;

    private UserIconDaoManager() {
        mUserIconDBDao = DaoManager.getDaoSession().getUserIconDBDao();
    }

    public static UserIconDaoManager getInstance() {
        return sUserIconDaoManager;
    }

    public boolean insertOrReplace(UserIconDB userIconDB) {
        try {
            mUserIconDBDao.insertOrReplace(userIconDB);
        } catch (Exception e) {
            Log.e("lxq", "更新失败");
            return false;
        }
        return true;
    }


    public UserIconDB queryByUserId(String userid) {
        return mUserIconDBDao
                .queryBuilder()
                .where(UserIconDBDao.Properties.UserId.eq(userid))
                .build()
                .unique();
    }


    public List<UserIconDB> queryAll() {
        return mUserIconDBDao.loadAll();
    }
}
