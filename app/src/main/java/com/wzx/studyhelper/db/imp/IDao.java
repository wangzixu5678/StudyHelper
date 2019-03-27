package com.wzx.studyhelper.db.imp;

import java.util.List;

/**
 * Created by wzx on 2018/4/24.
 */

public interface IDao<T> {
    boolean insert(T t);
    boolean delete(T t);
    boolean update(T t);
    List<T> queryAll();
    List<T> queryByObj(String where, String... params);
    T queryById(long id);
    T queryByName(String name);
}
