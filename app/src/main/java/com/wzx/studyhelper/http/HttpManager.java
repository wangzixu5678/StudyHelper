package com.wzx.studyhelper.http;

import com.google.gson.JsonObject;
import com.wzx.studyhelper.base.BaseImpl;
import com.wzx.studyhelper.http.api.ApiResponse;
import com.wzx.studyhelper.http.api.ApiService;
import com.wzx.studyhelper.http.subscriber.DialogObserver;
import com.wzx.studyhelper.ui.coursetab.bean.CourseUploadResultBean;
import com.wzx.studyhelper.ui.start.bean.LoginResultBean;

import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    public void login(BaseImpl baseImp,String mobile,String password,final ResponseCallback<LoginResultBean> responseCallback){
        JsonObject paramsMap = getParamsMap();
        paramsMap.addProperty("mobile",mobile);
        paramsMap.addProperty("password",password);
        Observable<ApiResponse<LoginResultBean>> observable = getApiService().loginUser(paramsMap);
        toSubscribe(observable,new DialogObserver<LoginResultBean>(baseImp,true) {
            @Override
            protected void onBaseNext(LoginResultBean data) {
                responseCallback.onSuccess(data);
            }
        });
    }
    public void register(BaseImpl baseImp,String mobile,String password,String nickname,final ResponseCallback<String> responseCallback){
        JsonObject paramsMap = getParamsMap();
        paramsMap.addProperty("mobile",mobile);
        paramsMap.addProperty("password",password);
        paramsMap.addProperty("nickname",nickname);
        Observable<String> observable = getApiService().insertUser(paramsMap);
        toSubscribeStr(observable, new DialogObserver<String>(baseImp,true) {
            @Override
            protected void onBaseNext(String data){
                responseCallback.onSuccess(data);
            }
        });
    }

    public void uploadFile(BaseImpl baseImp,
                           RequestBody requestBody,
                           final ResponseCallback<CourseUploadResultBean> responseCallback){
        Observable<ApiResponse<CourseUploadResultBean>> observable = getApiService().uploadFile(requestBody);
        toSubscribe(observable, new DialogObserver<CourseUploadResultBean>(baseImp,true) {
            @Override
            protected void onBaseNext(CourseUploadResultBean data) {
                responseCallback.onSuccess(data);
            }
        });

    }
}
