package com.hryg.model;

/**
 * Created by kefanbufan on 16/5/26.
 */
public class PostOrderData {


    /**
     * code : 1
     * description : 提交成功
     * data : {"store_id":19077,"order_id":22219,"order_sn":"1613681396"}
     */

    private int code;
    private String description;
    /**
     * store_id : 19077
     * order_id : 22219
     * order_sn : 1613681396
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
        private int store_id;
        private int order_id;
        private String order_sn;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }
    }
}
