package com.wzx.studyhelper.ui.homework.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HomeWorkListBean {


    /**
     * code : 200
     * msg : 作业记录查询成功
     * data : [{"id":429948372549189632,"operatioName":"啦咯就来咯啦咯就来咯家","courseName":"统计学","courseId":1,"startTime":"2019-04-01T13:20:07.000+0000","startTimeDto":1554124807000,"endTime":"2019-04-07T13:20:07.000+0000","endTimeDto":1554124807000,"completionTime":null,"completionTimeDto":null,"userId":429189170500493312,"createTime":"2019-04-01T10:21:20.000+0000","deleted":0}]
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
         * id : 429948372549189632
         * operatioName : 啦咯就来咯啦咯就来咯家
         * courseName : 统计学
         * courseId : 1
         * startTime : 2019-04-01T13:20:07.000+0000
         * startTimeDto : 1554124807000
         * endTime : 2019-04-07T13:20:07.000+0000
         * endTimeDto : 1554124807000
         * completionTime : null
         * completionTimeDto : null
         * userId : 429189170500493312
         * createTime : 2019-04-01T10:21:20.000+0000
         * deleted : 0
         */

        private long id;
        private String operatioName;
        private String courseName;
        private int courseId;
        private String startTime;
        private long startTimeDto;
        private String endTime;
        private long endTimeDto;
        private String completionTime;
        private String completionTimeDto;
        private long userId;
        private String createTime;
        private int deleted;

        protected DataBean(Parcel in) {
            id = in.readLong();
            operatioName = in.readString();
            courseName = in.readString();
            courseId = in.readInt();
            startTime = in.readString();
            startTimeDto = in.readLong();
            endTime = in.readString();
            endTimeDto = in.readLong();
            completionTime = in.readString();
            completionTimeDto = in.readString();
            userId = in.readLong();
            createTime = in.readString();
            deleted = in.readInt();
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

        public String getOperatioName() {
            return operatioName;
        }

        public void setOperatioName(String operatioName) {
            this.operatioName = operatioName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
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

        public String getCompletionTime() {
            return completionTime;
        }

        public void setCompletionTime(String completionTime) {
            this.completionTime = completionTime;
        }

        public String getCompletionTimeDto() {
            return completionTimeDto;
        }

        public void setCompletionTimeDto(String completionTimeDto) {
            this.completionTimeDto = completionTimeDto;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(id);
            parcel.writeString(operatioName);
            parcel.writeString(courseName);
            parcel.writeInt(courseId);
            parcel.writeString(startTime);
            parcel.writeLong(startTimeDto);
            parcel.writeString(endTime);
            parcel.writeLong(endTimeDto);
            parcel.writeString(completionTime);
            parcel.writeString(completionTimeDto);
            parcel.writeLong(userId);
            parcel.writeString(createTime);
            parcel.writeInt(deleted);
        }
    }
}
