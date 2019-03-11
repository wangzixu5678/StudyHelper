package com.wzx.studyhelper.http.api;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by hdxy on 2018/12/1.
 */

public interface ApiService {

    @GET("weixin/api/getHomeInfo")
    Observable<String> getHomeInfo(@Query("aa")String a);

}
