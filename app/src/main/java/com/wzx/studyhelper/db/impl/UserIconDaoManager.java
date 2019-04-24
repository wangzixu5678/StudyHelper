package com.wzx.studyhelper.db.impl;

import android.util.Log;

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




    public UserIconDB queryByUserId(String phone) {
        return mUserIconDBDao
                .queryBuilder()
                .where(UserIconDBDao.Properties.Phone.eq(phone))
                .build()
                .unique();
    }

    public List<FriendsRequestDB> getMyFriendsRequest(String phone){
       return mUserIconDBDao
                .queryBuilder()
                .where(UserIconDBDao.Properties.Phone.eq(phone))
                .build()
                .unique()
                .getMFriendsRequestDBList();
    }



}
