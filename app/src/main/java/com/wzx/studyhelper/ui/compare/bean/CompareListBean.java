package com.wzx.studyhelper.ui.compare.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.wzx.studyhelper.ui.note.bean.NoteBean;

import java.util.List;

public class CompareListBean {


    /**
     * code : 200
     * msg : 对比记录查询成功
     * data : [{"id":2,"userId":430590106769756160,"contrastName":"测试","noteIds":"430206677183234048,425214034339975168","createTime":"2019-04-03T13:56:36.000+0000","modifyTime":"2019-04-03T13:56:36.000+0000","deleted":0,"notes":[{"id":425214034339975168,"userId":422943498071375872,"courseName":"测试修改","courseId":1,"knowledgeName":"高数","knowledgeAnswer":"出师表","personalExperience":"先帝创业未半而中道崩殂，今天下三分，益州疲弊，此诚危急存亡之秋也。然侍卫之臣不懈于内，忠志之士忘身于外者，盖追先帝之殊遇，欲报之于陛下也。诚宜开张圣听，以光先帝遗德，恢弘志士之气，不宜妄自菲薄，引喻失义，以塞忠谏之路也。宫中府中，俱为一体，陟罚臧否，不宜异同。","marked":1,"createTime":"2019-03-19T08:48:46.000+0000","modifyTime":"2019-03-19T08:52:33.000+0000","deleted":0},{"id":430206677183234048,"userId":429189170500493312,"courseName":"市场调查与研究学","courseId":3,"knowledgeName":"啊KKK","knowledgeAnswer":"啦咯就来咯啦咯就来咯家","personalExperience":"啦咯啦咯啦咯啦","marked":null,"createTime":"2019-04-02T03:27:45.000+0000","modifyTime":null,"deleted":0}]}]
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
         * id : 2
         * userId : 430590106769756160
         * contrastName : 测试
         * noteIds : 430206677183234048,425214034339975168
         * createTime : 2019-04-03T13:56:36.000+0000
         * modifyTime : 2019-04-03T13:56:36.000+0000
         * deleted : 0
         * notes : [{"id":425214034339975168,"userId":422943498071375872,"courseName":"测试修改","courseId":1,"knowledgeName":"高数","knowledgeAnswer":"出师表","personalExperience":"先帝创业未半而中道崩殂，今天下三分，益州疲弊，此诚危急存亡之秋也。然侍卫之臣不懈于内，忠志之士忘身于外者，盖追先帝之殊遇，欲报之于陛下也。诚宜开张圣听，以光先帝遗德，恢弘志士之气，不宜妄自菲薄，引喻失义，以塞忠谏之路也。宫中府中，俱为一体，陟罚臧否，不宜异同。","marked":1,"createTime":"2019-03-19T08:48:46.000+0000","modifyTime":"2019-03-19T08:52:33.000+0000","deleted":0},{"id":430206677183234048,"userId":429189170500493312,"courseName":"市场调查与研究学","courseId":3,"knowledgeName":"啊KKK","knowledgeAnswer":"啦咯就来咯啦咯就来咯家","personalExperience":"啦咯啦咯啦咯啦","marked":null,"createTime":"2019-04-02T03:27:45.000+0000","modifyTime":null,"deleted":0}]
         */

        private int id;
        private long userId;
        private String contrastName;
        private String noteIds;
        private String createTime;
        private String modifyTime;
        private int deleted;
        private List<NoteBean> notes;

        protected DataBean(Parcel in) {
            id = in.readInt();
            userId = in.readLong();
            contrastName = in.readString();
            noteIds = in.readString();
            createTime = in.readString();
            modifyTime = in.readString();
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public List<NoteBean> getNotes() {
            return notes;
        }

        public void setNotes(List<NoteBean> notes) {
            this.notes = notes;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeLong(userId);
            parcel.writeString(contrastName);
            parcel.writeString(noteIds);
            parcel.writeString(createTime);
            parcel.writeString(modifyTime);
            parcel.writeInt(deleted);
        }


    }
}
