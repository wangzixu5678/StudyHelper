package com.wzx.studyhelper.db.impl;

import android.util.Log;

import com.wzx.studyhelper.db.auto.FriendsRequestDBDao;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.db.imp.IDao;
import com.wzx.studyhelper.db.utils.DaoManager;

import java.util.List;

public class FriendsRequestDaoManager implements IDao<FriendsRequestDB> {
    private static FriendsRequestDaoManager sFriendsRequestDaoManager = new FriendsRequestDaoManager();
    private final FriendsRequestDBDao mFriendsRequestDBDao;

    private FriendsRequestDaoManager() {
        mFriendsRequestDBDao = DaoManager.getDaoSession().getFriendsRequestDBDao();
    }

    public static FriendsRequestDaoManager getInstance() {
        return sFriendsRequestDaoManager;
    }

    @Override
    public boolean insert(FriendsRequestDB friendsRequestDB) {
        return mFriendsRequestDBDao.insert(friendsRequestDB) > 0;
    }

    @Override
    public boolean delete(FriendsRequestDB friendsRequestDB) {
        try {
            mFriendsRequestDBDao.delete(friendsRequestDB);
        } catch (Exception e) {
            Log.e("lxq", "删除失败");
            return false;
        }

        return true;
    }

    public void deleteAll(){
        mFriendsRequestDBDao.deleteAll();
    }

    @Override
    public boolean update(FriendsRequestDB friendsRequestD) {
        try {
            mFriendsRequestDBDao.insertOrReplace(friendsRequestD);
        } catch (Exception e) {
            Log.e("lxq", "更新失败");
            return false;
        }
        return true;
    }

    @Override
    public List<FriendsRequestDB> queryAll() {
        return mFriendsRequestDBDao.loadAll();
    }

    @Override
    public List<FriendsRequestDB> queryByObj(String where, String... params) {
        return mFriendsRequestDBDao.queryRaw(where, params);
    }

    @Override
    public FriendsRequestDB queryById(long id) {
        return mFriendsRequestDBDao.loadByRowId(id);
    }

    @Override
    public FriendsRequestDB queryByName(String name) {
        return mFriendsRequestDBDao
                .queryBuilder()
                .where(FriendsRequestDBDao.Properties.Username.eq(name))
                .build()
                .unique();
    }
}
