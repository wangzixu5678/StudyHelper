package com.wzx.studyhelper.ui.coursetab.bean;

import java.util.List;

public class CourseQueryResultBean {

    /**
     * code : 200
     * msg : 课程表查询成功
     * data : [{"id":429141206625931264,"userId":422943498071375872,"grade":1,"term":2,"createTime":"2019-03-30T04:53:57.000+0000","modifyTime":null,"deleted":0,"url":"http://k4pnpr.natappfree.cc/images/20190330125356_455321329E334CF39B877AF105B4C836.jpg"}]
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

    public static class DataBean {
        /**
         * id : 429141206625931264
         * userId : 422943498071375872
         * grade : 1
         * term : 2
         * createTime : 2019-03-30T04:53:57.000+0000
         * modifyTime : null
         * deleted : 0
         * url : http://k4pnpr.natappfree.cc/images/20190330125356_455321329E334CF39B877AF105B4C836.jpg
         */

        private String id;
        private String userId;
        private int grade;
        private int term;
        private String createTime;
        private Object modifyTime;
        private int deleted;
        private String url;
        private long createTimeDto;

        public long getCreateTimeDto() {
            return createTimeDto;
        }

        public void setCreateTimeDto(long createTimeDto) {
            this.createTimeDto = createTimeDto;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
