package com.wzx.studyhelper.ui.coursetab.bean;

public class CourseUploadResultBean {


    /**
     * id : 429141206625931264
     * userId : 422943498071375872
     * grade : 1
     * term : 2
     * createTime : 2019-03-30T04:53:56.717+0000
     * modifyTime : null
     * deleted : 0
     * url : http://k4pnpr.natappfree.cc/images/20190330125356_455321329E334CF39B877AF105B4C836.jpg
     */

    private long id;
    private long userId;
    private int grade;
    private int term;
    private String createTime;
    private String modifyTime;
    private int deleted;
    private String url;

    @Override
    public String toString() {
        return "CourseUploadResultBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", grade=" + grade +
                ", term=" + term +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", deleted=" + deleted +
                ", url='" + url + '\'' +
                '}';
    }

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
