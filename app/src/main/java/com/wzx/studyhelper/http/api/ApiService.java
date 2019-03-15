package com.wzx.studyhelper.http.api;


import com.google.gson.JsonObject;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by hdxy on 2018/12/1.
 */

public interface ApiService {


    /**
     * 注册
     */
    @POST("/study/usertab/insertUser")
    Observable<String> insertUser(@Body JsonObject value);

    /**
     *登录
     */
    @POST("/usertab/singin")
    Observable<String> loginUser(@Body JsonObject value);

}
