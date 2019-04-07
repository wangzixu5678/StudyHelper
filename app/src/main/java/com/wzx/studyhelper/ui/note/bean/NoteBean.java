package com.wzx.studyhelper.ui.note.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteBean implements Parcelable {
    private long id;
    private long userId;
    private String courseName;
    private int courseId;
    private String knowledgeName;
    private String knowledgeAnswer;
    private String personalExperience;
    private int marked;
    private String createTime;
    private String modifyTime;
    private int deleted;
    private int isSelected;

    protected NoteBean(Parcel in) {
        id = in.readLong();
        userId = in.readLong();
        courseName = in.readString();
        courseId = in.readInt();
        knowledgeName = in.readString();
        knowledgeAnswer = in.readString();
        personalExperience = in.readString();
        marked = in.readInt();
        createTime = in.readString();
        modifyTime = in.readString();
        deleted = in.readInt();
        isSelected = in.readInt();
    }

    public static final Creator<NoteBean> CREATOR = new Creator<NoteBean>() {
        @Override
        public NoteBean createFromParcel(Parcel in) {
            return new NoteBean(in);
        }

        @Override
        public NoteBean[] newArray(int size) {
            return new NoteBean[size];
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

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowledgeAnswer() {
        return knowledgeAnswer;
    }

    public void setKnowledgeAnswer(String knowledgeAnswer) {
        this.knowledgeAnswer = knowledgeAnswer;
    }

    public String getPersonalExperience() {
        return personalExperience;
    }

    public void setPersonalExperience(String personalExperience) {
        this.personalExperience = personalExperience;
    }

    public int getMarked() {
        return marked;
    }

    public void setMarked(int marked) {
        this.marked = marked;
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

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(userId);
        parcel.writeString(courseName);
        parcel.writeInt(courseId);
        parcel.writeString(knowledgeName);
        parcel.writeString(knowledgeAnswer);
        parcel.writeString(personalExperience);
        parcel.writeInt(marked);
        parcel.writeString(createTime);
        parcel.writeString(modifyTime);
        parcel.writeInt(deleted);
        parcel.writeInt(isSelected);
    }
}
