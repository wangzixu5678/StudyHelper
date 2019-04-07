package com.wzx.studyhelper.ui.compare.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.wzx.studyhelper.ui.note.bean.NoteBean;

import java.util.ArrayList;
import java.util.List;

public class CompareListBean {


    /**
     * code : 200
     * msg : 对比记录查询成功
     * data : [{"id":432142672183533568,"userId":429189170500493312,"contrastName":"还好","noteIds":"432140504550449152,430206677183234048","createTime":"2019-04-07T11:40:42.000+0000","modifyTime":null,"deleted":0,"notes":[{"id":430206677183234048,"userId":429189170500493312,"courseName":"市场调查与研究学","courseId":3,"knowledgeName":"啊KKK","knowledgeAnswer":"啦咯就来咯啦咯就来咯家","personalExperience":"啦咯啦咯啦咯啦","marked":null,"createTime":"2019-04-02T03:27:45.000+0000","modifyTime":null,"deleted":0},{"id":432140504550449152,"userId":429189170500493312,"courseName":"宏观经济学","courseId":4,"knowledgeName":"还干","knowledgeAnswer":"明后","personalExperience":"民工漫","marked":null,"createTime":"2019-04-07T11:32:05.000+0000","modifyTime":null,"deleted":0}]}]
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
         * id : 432142672183533568
         * userId : 429189170500493312
         * contrastName : 还好
         * noteIds : 432140504550449152,430206677183234048
         * createTime : 2019-04-07T11:40:42.000+0000
         * modifyTime : null
         * deleted : 0
         * notes : [{"id":430206677183234048,"userId":429189170500493312,"courseName":"市场调查与研究学","courseId":3,"knowledgeName":"啊KKK","knowledgeAnswer":"啦咯就来咯啦咯就来咯家","personalExperience":"啦咯啦咯啦咯啦","marked":null,"createTime":"2019-04-02T03:27:45.000+0000","modifyTime":null,"deleted":0},{"id":432140504550449152,"userId":429189170500493312,"courseName":"宏观经济学","courseId":4,"knowledgeName":"还干","knowledgeAnswer":"明后","personalExperience":"民工漫","marked":null,"createTime":"2019-04-07T11:32:05.000+0000","modifyTime":null,"deleted":0}]
         */

        private long id;
        private long userId;
        private String contrastName;
        private String noteIds;
        private String createTime;
        private Object modifyTime;
        private int deleted;
        private ArrayList<NoteBean> notes;


        protected DataBean(Parcel in) {
            id = in.readLong();
            userId = in.readLong();
            contrastName = in.readString();
            noteIds = in.readString();
            createTime = in.readString();
            deleted = in.readInt();
            notes = in.createTypedArrayList(NoteBean.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeLong(userId);
            dest.writeString(contrastName);
            dest.writeString(noteIds);
            dest.writeString(createTime);
            dest.writeInt(deleted);
            dest.writeTypedList(notes);
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

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getContrastName() {
            return contrastName;
        }

        public void setContrastName(String contrastName) {
            this.contrastName = contrastName;
        }

        public String getNoteIds() {
            return noteIds;
        }

        public void setNoteIds(String noteIds) {
            this.noteIds = noteIds;
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

        public ArrayList<NoteBean> getNotes() {
            return notes;
        }

        public void setNotes(ArrayList<NoteBean> notes) {
            this.notes = notes;
        }




    }
}
