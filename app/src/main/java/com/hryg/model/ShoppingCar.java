package com.hryg.model;


import java.util.List;

public class ShoppingCar {


    /**
     * code : 1
     * description : 获取成功
     * data : [{"rec_id":"60528","goods_id":"135151","goods_name":"韩琦8杯水 水漾极润深层补水霜","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_193/small_201602291429533830.png","gold":"22.00","specification":"","quantity":"1","subtotal":"22.00"},{"rec_id":"60588","goods_id":"135153","goods_name":"驰家楠竹雕花筷子碳化凤眼筷子10双竹筷装低碳环保工艺竹筷","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_13/small_201602291433332705.jpg","gold":"16.00","specification":"","quantity":"1","subtotal":"16.00"}]
     */

    private int code;
    private String description;
    /**
     * rec_id : 60528
     * goods_id : 135151
     * goods_name : 韩琦8杯水 水漾极润深层补水霜
     * goods_image : http://www.hr1g.net/data/files/store_19077/goods_193/small_201602291429533830.png
     * gold : 22.00
     * specification :
     * quantity : 1
     * subtotal : 22.00
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
        private String rec_id;
        private String goods_id;
        private String goods_name;
        private String goods_image;
        private String gold;
        private String specification;
        private String quantity;
        private String subtotal;

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
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

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(String subtotal) {
            this.subtotal = subtotal;
        }
    }
}
