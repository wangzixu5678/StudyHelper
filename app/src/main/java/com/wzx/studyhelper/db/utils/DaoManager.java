package com.wzx.studyhelper.db.utils;

import android.content.Context;


import com.wzx.studyhelper.db.auto.DaoMaster;
import com.wzx.studyhelper.db.auto.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by wzx on 2018/4/24.
 */

public class DaoManager {
    private static final String DB_NAME = "studyhelper.db";
    private static DaoSession mDaoSession;
    private DaoManager() {}

    public static void init(Context context){
        MySqlLiteOpenHelper mySqlLiteOpenHelper = new MySqlLiteOpenHelper(context,DB_NAME,null);
        Database mDatabase = mySqlLiteOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(mDatabase).newSession();
    }
    public static DaoSession getDaoSession(){
        return mDaoSession;
    }
}
