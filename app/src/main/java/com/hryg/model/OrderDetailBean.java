package com.hryg.model;

import java.util.List;

/**
 * Created by kefanbufan on 16/6/2.
 */
public class OrderDetailBean {


    /**
     * code : 1
     * description : 获取成功
     * data : {"order_sn":"1608800326","order_id":"21628","add_time":"2016-03-29 12:15:51","status":"40","store_id":"19077","store_name":"红包专区","tel":"0315-8025728","gold":"30.00","order_amount":"8.00","payment_code":"sft","payment_name":"余额支付"}
     * goods : [{"goods_id":"136254","goods_name":"大宝SOD蜜90ml清爽保湿/滋润/温和/润肤乳液 紧致肌肤","goods_image":"data/files/store_19077/goods_189/small_201603241326292895.png","quantity":"1","fu_gold":"30.00"}]
     * extm : {"consignee":"王春标","phone_tel":"15004043523","region_name":"中国\t天津市\t和平区111","shipping_name":"快递"}
     */

    private int code;
    private String description;
    /**
     * order_sn : 1608800326
     * order_id : 21628
     * add_time : 2016-03-29 12:15:51
     * status : 40
     * store_id : 19077
     * store_name : 红包专区
     * tel : 0315-8025728
     * gold : 30.00
     * order_amount : 8.00
     * payment_code : sft
     * payment_name : 余额支付
     */

    private DataBean data;
    /**
     * consignee : 王春标
     * phone_tel : 15004043523
     * region_name : 中国	天津市	和平区111
     * shipping_name : 快递
     */

    private ExtmBean extm;
    /**
     * goods_id : 136254
     * goods_name : 大宝SOD蜜90ml清爽保湿/滋润/温和/润肤乳液 紧致肌肤
     * goods_image : data/files/store_19077/goods_189/small_201603241326292895.png
     * quantity : 1
     * fu_gold : 30.00
     */

    private List<GoodsBean> goods;

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

    public ExtmBean getExtm() {
        return extm;
    }

    public void setExtm(ExtmBean extm) {
        this.extm = extm;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class DataBean {
        private String order_sn;
        private String order_id;
        private String add_time;
        private String status;
        private String store_id;
        private String store_name;
        private String tel;
        private String gold;
        private String order_amount;
        private String payment_code;
        private String payment_name;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
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

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }
    }

    public static class ExtmBean {
        private String consignee;
        private String phone_tel;
        private String region_name;
        private String shipping_name;

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone_tel() {
            return phone_tel;
        }

        public void setPhone_tel(String phone_tel) {
            this.phone_tel = phone_tel;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }
    }

    public static class GoodsBean {
        private String goods_id;
        private String goods_name;
        private String goods_image;
        private String quantity;
        private String fu_gold;

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

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getFu_gold() {
            return fu_gold;
        }

        public void setFu_gold(String fu_gold) {
            this.fu_gold = fu_gold;
        }
    }
}
