package com.wzx.studyhelper.base;

import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * Created by wzx on 2018/12/1.
 */
public interface BaseImpl {

    void addDisposable(Disposable disposable);

    Context getContext();
}
