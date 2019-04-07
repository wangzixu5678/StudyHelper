package com.wzx.studyhelper.http.api;


import com.google.gson.JsonObject;
import com.wzx.studyhelper.ui.coursetab.bean.CourseUploadResultBean;
import com.wzx.studyhelper.ui.start.bean.LoginResultBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
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
    @POST("/study/usertab/singin")
    Observable<ApiResponse<LoginResultBean>> loginUser(@Body JsonObject value);

    /**
     * 上传课程表
     */
    @POST("/study/file/uploadFile")
    Observable<ApiResponse<CourseUploadResultBean>> uploadFile(@Body RequestBody body);

    /**
     * 查询课程表
     */
    @POST("/study/file/queryByScheduleCard")
    Observable<String> queryByScheduleCard(@Body JsonObject value);

    /**
     * 新增笔记
     */
    @POST("/study/note/insertnote")
    Observable<String> insertnote(@Body JsonObject value);

    /**
     * 修改笔记
     */
    @POST("/study/note/updatenote")
    Observable<String> updatenote(@Body JsonObject value);

    /**
     * 笔记查询
     */
    @POST("/study/note/selectByNote")
    Observable<String> selectByNote(@Body JsonObject value);


    /**
     * 作业新增
     */
    @POST("/study/operatio/insertoperatio")
    Observable<String> insertoperatio(@Body JsonObject value);


    /**
     * 作业修改
     */
    @POST("/study/operatio/updayeoperatio")
    Observable<String> updayeoperatio(@Body JsonObject value);

    /**
     * 作业查询
     */
    @POST("/study/operatio/selectByoperation")
    Observable<String> selectByoperation(@Body JsonObject value);

    /**
     * 新增考试记录
     */
    @POST("/study/examination/insertExamination")
    Observable<String> insertExamination(@Body JsonObject value);
    /**
     * 修改考试记录
     */
    @POST("/study/examination/updateExamination")
    Observable<String> updateExamination(@Body JsonObject value);
    /**
     * 查询考试记录
     */
    @POST("/study/examination/selectByExamination")
    Observable<String> selectByExamination(@Body JsonObject value);

    /**
     * 新增难点记录
     */
    @POST("/study/difficult/insertDifficult")
    Observable<String> insertDifficult(@Body JsonObject value);

    /**
     * 修改难点记录
     */
    @POST("/study/difficult/updateDifficult")
    Observable<String> updateDifficult(@Body JsonObject value);

    /**
     * 难点查询
     */
    @POST("/study/difficult/selectByDifficult")
    Observable<String> selectByDifficult(@Body JsonObject value);

    /**
     * 新增计划
     */
    @POST("/study/difficult/insertSchedule")
    Observable<String> insertSchedule(@Body JsonObject value);
    /**
     * 修改计划
     */
    @POST("/study/difficult/updateSchedule")
    Observable<String> updateSchedule(@Body JsonObject value);
    /**
     * 查询计划
     */
    @POST("/study/difficult/selectBySchedule")
    Observable<String> selectBySchedule(@Body JsonObject value);
}
