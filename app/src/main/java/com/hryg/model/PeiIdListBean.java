package com.hryg.model;


import java.util.List;

public class PeiIdListBean {


    /**
     * code : 1
     * description : 获取成功
     * data : [{"id":"72","rand_id":"4949","name":"200红包组"},{"id":"73","rand_id":"6509","name":"100红包组"},{"id":"74","rand_id":"1418","name":"200红包组"},{"id":"75","rand_id":"8153","name":"100红包组"}]
     * pei_id : 37579
     */

    private int code;
    private String description;
    private String pei_id;
    /**
     * id : 72
     * rand_id : 4949
     * name : 200红包组
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

    public String getPei_id() {
        return pei_id;
    }

    public void setPei_id(String pei_id) {
        this.pei_id = pei_id;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String rand_id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRand_id() {
            return rand_id;
        }

        public void setRand_id(String rand_id) {
            this.rand_id = rand_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
