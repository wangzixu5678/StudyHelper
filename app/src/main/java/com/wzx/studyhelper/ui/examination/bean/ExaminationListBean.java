package com.wzx.studyhelper.ui.examination.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ExaminationListBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":430564691967680512,"userId":429189170500493312,"startTime":"2019-04-03T06:10:03.000+0000","startTimeDto":1554271803000,"endTime":"2019-04-09T06:10:03.000+0000","endTimeDto":1554790203000,"createTime":"2019-04-03T03:10:22.000+0000","modifyTime":null,"courseName":"市场调查与研究学","place":"地点","deleted":0,"knowledgeDescription":"呵呵哈哈哈","courseId":3}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 430564691967680512
         * userId : 429189170500493312
         * startTime : 2019-04-03T06:10:03.000+0000
         * startTimeDto : 1554271803000
         * endTime : 2019-04-09T06:10:03.000+0000
         * endTimeDto : 1554790203000
         * createTime : 2019-04-03T03:10:22.000+0000
         * modifyTime : null
         * courseName : 市场调查与研究学
         * place : 地点
         * deleted : 0
         * knowledgeDescription : 呵呵哈哈哈
         * courseId : 3
         */

        private long id;
        private long userId;
        private String startTime;
        private long startTimeDto;
        private String endTime;
        private long endTimeDto;
        private String createTime;
        private Object modifyTime;
        private String courseName;
        private String place;
        private int deleted;
        private String knowledgeDescription;
        private int courseId;

        protected DataBean(Parcel in) {
            id = in.readLong();
            userId = in.readLong();
            startTime = in.readString();
            startTimeDto = in.readLong();
            endTime = in.readString();
            endTimeDto = in.readLong();
            createTime = in.readString();
            courseName = in.readString();
            place = in.readString();
            deleted = in.readInt();
            knowledgeDescription = in.readString();
            courseId = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public long getStartTimeDto() {
            return startTimeDto;
        }

        public void setStartTimeDto(long startTimeDto) {
            this.startTimeDto = startTimeDto;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public long getEndTimeDto() {
            return endTimeDto;
        }

        public void setEndTimeDto(long endTimeDto) {
            this.endTimeDto = endTimeDto;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public String getKnowledgeDescription() {
            return knowledgeDescription;
        }

        public void setKnowledgeDescription(String knowledgeDescription) {
            this.knowledgeDescription = knowledgeDescription;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(id);
            parcel.writeLong(userId);
            parcel.writeString(startTime);
            parcel.writeLong(startTimeDto);
            parcel.writeString(endTime);
            parcel.writeLong(endTimeDto);
            parcel.writeString(createTime);
            parcel.writeString(courseName);
            parcel.writeString(place);
            parcel.writeInt(deleted);
            parcel.writeString(knowledgeDescription);
            parcel.writeInt(courseId);
        }
    }
}
