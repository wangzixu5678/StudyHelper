package com.wzx.studyhelper.db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.wzx.studyhelper.db.auto.DaoMaster;
import com.wzx.studyhelper.db.auto.FriendsRequestDBDao;
import com.wzx.studyhelper.db.auto.UserIconDBDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by FTKJ on 2017/8/25.
 */

public class MySqlLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySqlLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.getInstance().migrate(db,FriendsRequestDBDao.class);
        MigrationHelper.getInstance().migrate(db,UserIconDBDao.class);
    }
}
