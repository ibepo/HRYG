package com.hryg.model;

/**
 * Created by kefanbufan on 16/5/20.
 */
public class GoodsData {

    private int goods_id;

    private String gold;

    private String images;

    private String goods_name;

    private String stock;

    private String spec_id;

    public String getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(String spec_id) {
        this.spec_id = spec_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getGoods_id() {
        return this.goods_id;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getGold() {
        return this.gold;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return this.images;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_name() {
        return this.goods_name;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStock() {
        return this.stock;
    }


}
