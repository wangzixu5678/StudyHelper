package com.wzx.studyhelper.ui.note.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NoteListBean {

    private String code;
    private String msg;
    private List<NoteBean> data;

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

    public List<NoteBean> getData() {
        return data;
    }

    public void setData(List<NoteBean> data) {
        this.data = data;
    }


}
