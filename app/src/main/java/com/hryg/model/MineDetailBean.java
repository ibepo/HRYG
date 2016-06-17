package com.hryg.model;

/**
 * Created by kefanbufan on 16/6/6.
 */
public class MineDetailBean {


    /**
     * code : 1
     * description : 获取成功
     * data : {"user_id":"20446","user_name":"wangcb","real_name":"王春标","portrait":"http://www.hr1g.net/ucenter/avatar.php?uid=20446&size=middle","gender":"1","birthday":"1992-02-17"}
     */

    private int code;
    private String description;
    /**
     * user_id : 20446
     * user_name : wangcb
     * real_name : 王春标
     * portrait : http://www.hr1g.net/ucenter/avatar.php?uid=20446&size=middle
     * gender : 1
     * birthday : 1992-02-17
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String user_id;
        private String user_name;
        private String real_name;
        private String portrait;
        private String gender;
        private String birthday;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
