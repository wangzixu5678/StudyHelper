package com.wzx.studyhelper.http;

public interface ResponseCallback<T> {
    void onSuccess(T t);
}
