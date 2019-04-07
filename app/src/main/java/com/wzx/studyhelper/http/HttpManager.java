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


     public void queryByScheduleCard(BaseImpl base, String userId, String grade, String term, final ResponseCallback<String> responseCallback){
         JsonObject paramsMap = getParamsMap();
         paramsMap.addProperty("userId",userId);
         paramsMap.addProperty("grade",grade);
         paramsMap.addProperty("term",term);
         Observable<String> observable = getApiService().queryByScheduleCard(paramsMap);
         toSubscribeStr(observable, new DialogObserver<String>(base) {
             @Override
             protected void onBaseNext(String data) {
                 responseCallback.onSuccess(data);
             }
         });
     }

     //新增作业
    public void insertoperatio(BaseImpl base,JsonObject paramsMap, final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().insertoperatio(paramsMap);
        toSubscribeStr(observable, new DialogObserver<String>(base) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    public void updayeoperatio(BaseImpl base,JsonObject paramsMap, final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().updayeoperatio(paramsMap);
        toSubscribeStr(observable, new DialogObserver<String>(base) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    //查询作业
    public void selectByoperation(BaseImpl base,String id,String userId,final ResponseCallback<String> responseCallback){
        JsonObject paramsMap = getParamsMap();
        paramsMap.addProperty("userId",userId);
        paramsMap.addProperty("id",id);
        Observable<String> observable = getApiService().selectByoperation(paramsMap);
        toSubscribeStr(observable, new DialogObserver<String>(base) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    //查询笔记
    public void selectByNote(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().selectByNote(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    //新增笔记

    public void insertnote(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().insertnote(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }
    //修改笔记
    public void updatenote(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().updatenote(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    public void insertExamination(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().insertExamination(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    public void updateExamination(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().updateExamination(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    public void selectByExamination(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().selectByExamination(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    public void selectByDifficult(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().selectByDifficult(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    public void insertDifficult(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().insertDifficult(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    public void updateDifficult(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().updateDifficult(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    //计划表
    public void selectBySchedule(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().selectBySchedule(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    public void insertSchedule(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().insertSchedule(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }


    public void updateSchedule(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().updateSchedule(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    //对比功能
    public void selectByContrast(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().selectByContrast(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    public void updateContrast(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().updateContrast(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }

    public void insertContrast(BaseImpl base,JsonObject jsonObject,final ResponseCallback<String> responseCallback){
        Observable<String> observable = getApiService().insertContrast(jsonObject);
        toSubscribeStr(observable, new DialogObserver<String>(base,true) {
            @Override
            protected void onBaseNext(String data) {
                responseCallback.onSuccess(data);
            }
        });
    }
}
