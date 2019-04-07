package com.wzx.studyhelper.ui.schedule.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ScheduleListBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":430633220582490112,"scheduleName":"测试新增111","scheduleType":2,"completionSchedule":1,"startTime":"2019-04-03T14:35:48.000+0000","startTimeDto":1554302148000,"endTime":"2019-04-04T14:35:51.000+0000","endTimeDto":1554388551000,"createTime":"2019-04-03T15:45:13.000+0000","createTimeDto":1554306313000,"modifyTime":"2019-04-03T07:45:13.000+0000","deleted":0,"userId":430590106769756160}]
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
         * id : 430633220582490112
         * scheduleName : 测试新增111
         * scheduleType : 2
         * completionSchedule : 1
         * startTime : 2019-04-03T14:35:48.000+0000
         * startTimeDto : 1554302148000
         * endTime : 2019-04-04T14:35:51.000+0000
         * endTimeDto : 1554388551000
         * createTime : 2019-04-03T15:45:13.000+0000
         * createTimeDto : 1554306313000
         * modifyTime : 2019-04-03T07:45:13.000+0000
         * deleted : 0
         * userId : 430590106769756160
         */

        private long id;
        private String scheduleName;
        private int scheduleType;
        private int completionSchedule;
        private String startTime;
        private long startTimeDto;
        private String endTime;
        private long endTimeDto;
        private String createTime;
        private long createTimeDto;
        private String modifyTime;
        private int deleted;
        private long userId;

        protected DataBean(Parcel in) {
            id = in.readLong();
            scheduleName = in.readString();
            scheduleType = in.readInt();
            completionSchedule = in.readInt();
            startTime = in.readString();
            startTimeDto = in.readLong();
            endTime = in.readString();
            endTimeDto = in.readLong();
            createTime = in.readString();
            createTimeDto = in.readLong();
            modifyTime = in.readString();
            deleted = in.readInt();
            userId = in.readLong();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(scheduleName);
            dest.writeInt(scheduleType);
            dest.writeInt(completionSchedule);
            dest.writeString(startTime);
            dest.writeLong(startTimeDto);
            dest.writeString(endTime);
            dest.writeLong(endTimeDto);
            dest.writeString(createTime);
            dest.writeLong(createTimeDto);
            dest.writeString(modifyTime);
            dest.writeInt(deleted);
            dest.writeLong(userId);
        }

        @Override
        public int describeContents() {
            return 0;
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

        public String getScheduleName() {
            return scheduleName;
        }

        public void setScheduleName(String scheduleName) {
            this.scheduleName = scheduleName;
        }

        public int getScheduleType() {
            return scheduleType;
        }

        public void setScheduleType(int scheduleType) {
            this.scheduleType = scheduleType;
        }

        public int getCompletionSchedule() {
            return completionSchedule;
        }

        public void setCompletionSchedule(int completionSchedule) {
            this.completionSchedule = completionSchedule;
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

        public long getCreateTimeDto() {
            return createTimeDto;
        }

        public void setCreateTimeDto(long createTimeDto) {
            this.createTimeDto = createTimeDto;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }
    }
}
