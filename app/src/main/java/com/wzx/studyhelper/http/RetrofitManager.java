package com.wzx.studyhelper.http;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.JsonObject;
import com.wzx.studyhelper.http.api.ApiResponse;
import com.wzx.studyhelper.http.api.ApiService;
import com.wzx.studyhelper.http.exception.ApiException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hdxy on 2018/12/1.
 */

public abstract class RetrofitManager {

    private static final int DEFAULT_TIME = 20;
    private static Retrofit sRetrofit;
    private static ApiService sApiService;


    protected synchronized static ApiService getApiService() {
        if (sApiService == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                    .addInterceptor(new StethoInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(NetAddressConstants.BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            sApiService = sRetrofit.create(ApiService.class);
        }
        return sApiService;
    }


    protected HashMap<String, Object> getHeaderMap() {
        HashMap<String, Object> headerMap = new HashMap<>();
        return headerMap;
    }

    protected  JsonObject getParamsMap() {

        return  new JsonObject();

    }


    protected <T> void toSubscribe(Observable<ApiResponse<T>> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .map(new Function<ApiResponse<T>, T>() {
                    @Override
                    public T apply(@NonNull ApiResponse<T> response) throws Exception {
                        if (response.getCode() != ConstantCode.SUCCESSCODE) {
                            throw new ApiException(response.getCode(), response.getMessage());
                        } else {
                            return response.getResponse();
                        }
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }






    protected <T> void toSubscribeStr(Observable<String> o, Observer<String> s) {
        o.subscribeOn(Schedulers.io()).map(new Function<String, String>() {
            @Override
            public String apply(String t) throws Exception {
                JSONObject responseStr = new JSONObject(t);
                String message = responseStr.getString("msg");
                int code = responseStr.getInt("code");
                if (code != ConstantCode.SUCCESSCODE) {
                    throw new ApiException(code, message);
                } else {
                    return t;
                }
            }
        }).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    protected <T> void toSubscribeStrWithoutFilter(Observable<String> o, Observer<String> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
