package com.wzx.studyhelper.ui.diffcult.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DiffcultListBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":430571601538256896,"courseName":"金融学","courseId":5,"userId":429189170500493312,"difficultName":"KKK","dealStatus":0,"noteId":null,"createTime":"2019-04-03T03:37:50.000+0000","modifyTime":null,"deleted":0,"difficultAnswers":"啦咯就来咯啦咯就来咯家"}]
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
         * id : 430571601538256896
         * courseName : 金融学
         * courseId : 5
         * userId : 429189170500493312
         * difficultName : KKK
         * dealStatus : 0
         * noteId : null
         * createTime : 2019-04-03T03:37:50.000+0000
         * modifyTime : null
         * deleted : 0
         * difficultAnswers : 啦咯就来咯啦咯就来咯家
         */

        private long id;
        private String courseName;
        private int courseId;
        private long userId;
        private String difficultName;
        private int dealStatus;
        private Object noteId;
        private String createTime;
        private Object modifyTime;
        private int deleted;
        private String difficultAnswers;

        protected DataBean(Parcel in) {
            id = in.readLong();
            courseName = in.readString();
            courseId = in.readInt();
            userId = in.readLong();
            difficultName = in.readString();
            dealStatus = in.readInt();
            createTime = in.readString();
            deleted = in.readInt();
            difficultAnswers = in.readString();
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

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getDifficultName() {
            return difficultName;
        }

        public void setDifficultName(String difficultName) {
            this.difficultName = difficultName;
        }

        public int getDealStatus() {
            return dealStatus;
        }

        public void setDealStatus(int dealStatus) {
            this.dealStatus = dealStatus;
        }

        public Object getNoteId() {
            return noteId;
        }

        public void setNoteId(Object noteId) {
            this.noteId = noteId;
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

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public String getDifficultAnswers() {
            return difficultAnswers;
        }

        public void setDifficultAnswers(String difficultAnswers) {
            this.difficultAnswers = difficultAnswers;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(id);
            parcel.writeString(courseName);
            parcel.writeInt(courseId);
            parcel.writeLong(userId);
            parcel.writeString(difficultName);
            parcel.writeInt(dealStatus);
            parcel.writeString(createTime);
            parcel.writeInt(deleted);
            parcel.writeString(difficultAnswers);
        }
    }
}
