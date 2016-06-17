package com.hryg.model;


import java.util.List;

public class BuyOrderListData {


    /**
     * code : 1
     * description : 获取成功
     * data : [{"order_id":"19315","order_sn":"1536203886","status":"11","apply_status":"0","store_id":"19077","store_name":"红包专区","gold":"22.00","order_amount":"8.00","goods_id":"133473","goods_name":"莎怡霖 花香洗手液 绿色洗手液380ml 84#","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_168/small_201512281032487016.jpg"},{"order_id":"19330","order_sn":"1536276053","status":"11","apply_status":"0","store_id":"19077","store_name":"红包专区","gold":"42.00","order_amount":"16.00","goods_id":"133473","goods_name":"莎怡霖 花香洗手液 绿色洗手液380ml 84#","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_168/small_201512281032487016.jpg"},{"order_id":"20001","order_sn":"1602059108","status":"20","apply_status":"0","store_id":"19077","store_name":"红包专区","gold":"188.00","order_amount":"8.00","goods_id":"134019","goods_name":"万河鹿鞭膏 正品高纯度 滋补膏","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_189/small_201601121043093501.jpg"},{"order_id":"20797","order_sn":"1605978128","status":"40","apply_status":"0","store_id":"19077","store_name":"红包专区","gold":"40.00","order_amount":"20.00","goods_id":"95504","goods_name":"韩国原装进口立奥芳洗洁精果蔬餐具洗涤剂柠檬味洗洁精厨房清洗剂","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_134/small_201409291508541179.png"}]
     */

    private int code;
    private String description;
    /**
     * order_id : 19315
     * order_sn : 1536203886
     * status : 11
     * apply_status : 0
     * store_id : 19077
     * store_name : 红包专区
     * gold : 22.00
     * order_amount : 8.00
     * goods_id : 133473
     * goods_name : 莎怡霖 花香洗手液 绿色洗手液380ml 84#
     * goods_image : http://www.hr1g.net/data/files/store_19077/goods_168/small_201512281032487016.jpg
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
        private String order_id;
        private String order_sn;
        private String status;
        private String apply_status;
        private String store_id;
        private String store_name;
        private String gold;
        private String order_amount;
        private String goods_id;
        private String goods_name;
        private String goods_image;
        private String evaluation_status;

        public String getEvaluation_status() {
            return evaluation_status;
        }

        public void setEvaluation_status(String evaluation_status) {
            this.evaluation_status = evaluation_status;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getApply_status() {
            return apply_status;
        }

        public void setApply_status(String apply_status) {
            this.apply_status = apply_status;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }
    }
}
