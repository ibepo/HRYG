package com.hryg.model;

import java.util.List;

/**
 * Created by kefanbufan on 16/6/10.
 */
public class IdListBean {


    /**
     * code : 1
     * description : 获取成功
     * data : [{"userId":"37579","user_name":"wangcb123","real_name":null,"phone_tel":null},{"userId":"37580","user_name":"wangcb1234","real_name":"","phone_tel":null},{"userId":"44989","user_name":"wangcb3210","real_name":null,"phone_tel":null}]
     */

    private int code;
    private String description;
    /**
     * userId : 37579
     * user_name : wangcb123
     * real_name : null
     * phone_tel : null
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String userId;
        private String user_name;
        private Object real_name;
        private Object phone_tel;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Object getReal_name() {
            return real_name;
        }

        public void setReal_name(Object real_name) {
            this.real_name = real_name;
        }

        public Object getPhone_tel() {
            return phone_tel;
        }

        public void setPhone_tel(Object phone_tel) {
            this.phone_tel = phone_tel;
        }
    }
}
