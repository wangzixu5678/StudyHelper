package com.wzx.studyhelper.http;

import com.wzx.studyhelper.base.BaseImpl;
import com.wzx.studyhelper.http.api.ApiService;
import com.wzx.studyhelper.http.subscriber.DialogObserver;

import io.reactivex.Observable;

public class HttpManager extends RetrofitManager {

    private static HttpManager mHttpManager;
    private HttpManager(){

    }
    public static HttpManager getInstance(){
        if (mHttpManager==null){
            synchronized (HttpManager.class){
                if (mHttpManager==null){
                    mHttpManager = new HttpManager();
                }
            }
        }
        return mHttpManager;
    }

    public void getDateFromNet(BaseImpl baseImp, final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().getHomeInfo("a");
        toSubscribeStr(observable,new DialogObserver<String>(baseImp,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }
}
