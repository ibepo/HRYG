package com.hryg.model;

import java.util.List;


public class SearchData {
    private int code;

    private String description;

    private List<Goods> goods;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<Goods> getGoods() {
        return this.goods;
    }

    public class Goods {
        private String goods_id;

        private String goods_name;

        private String default_image;

        private String gold;

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_id() {
            return this.goods_id;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_name() {
            return this.goods_name;
        }

        public void setDefault_image(String default_image) {
            this.default_image = default_image;
        }

        public String getDefault_image() {
            return this.default_image;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getGold() {
            return this.gold;
        }

    }


}
