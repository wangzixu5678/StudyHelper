package com.wzx.studyhelper.http.subscriber;



import com.google.gson.stream.MalformedJsonException;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.base.BaseImpl;
import com.wzx.studyhelper.http.exception.ApiException;
import com.wzx.studyhelper.utils.StringUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;


public abstract class BaseObserver<T> implements Observer<T> {

    private BaseImpl mBaseImpl;

    protected abstract void onBaseError(int code, String msg);
    protected abstract void onBaseNext(T data);



    public BaseObserver(BaseImpl baseImpl) {
        mBaseImpl = baseImpl;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mBaseImpl !=null){
            if (d!=null){
                mBaseImpl.addDisposable(d);
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (t!=null){
            onBaseNext(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof CompositeException) {
            CompositeException compositeE=(CompositeException)e;
            for (Throwable throwable : compositeE.getExceptions()) {
                if (throwable instanceof SocketTimeoutException) {
                    onBaseError(ApiException.Code_TimeOut,ApiException.SOCKET_TIMEOUT_EXCEPTION);
                } else if (throwable instanceof ConnectException) {
                    onBaseError(ApiException.Code_UnConnected,ApiException.CONNECT_EXCEPTION);
                } else if (throwable instanceof UnknownHostException) {
                    onBaseError(ApiException.Code_UnConnected,ApiException.CONNECT_EXCEPTION);
                }  else if (throwable instanceof MalformedJsonException) {
                    onBaseError(ApiException.Code_MalformedJson,ApiException.MALFORMED_JSON_EXCEPTION);
                }
            }
        }else {
            String message = e.getMessage();
            onBaseError(ApiException.Code_Default, StringUtil.judgeString(message));
        }

    }

    @Override
    public void onComplete() {

    }


}
