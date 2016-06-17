package com.hryg.model;

/**
 * Created by kefanbufan on 16/6/6.
 */
public class MineData {


    /**
     * code : 1
     * description : 获取成功
     * data : {"user_id":"20446","real_name":"王春标","portrait":"http://www.hr1g.net/ucenter/avatar.php?uid=20446&size=middle","money":"442.82","gold":"517.00","puid":"1"}
     */

    private int code;
    private String description;
    /**
     * user_id : 20446
     * real_name : 王春标
     * portrait : http://www.hr1g.net/ucenter/avatar.php?uid=20446&size=middle
     * money : 442.82
     * gold : 517.00
     * puid : 1
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
        private String real_name;
        private String portrait;
        private String money;
        private String gold;
        private String puid;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getPuid() {
            return puid;
        }

        public void setPuid(String puid) {
            this.puid = puid;
        }
    }
}
