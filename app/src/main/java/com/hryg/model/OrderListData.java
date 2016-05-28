package com.hryg.model;

import java.util.List;

/**
 * Created by kefanbufan on 16/5/26.
 */
public class OrderListData {


    /**
     * code : 1
     * description : 获取成功
     * data : [{"goods_id":"133701","goods_name":"皓齿健 芨效全优120g牙膏","quantity":"1","specification":"","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_172/small_201601121546122244.jpg"},{"goods_id":"134687","goods_name":"长白山山参酒","quantity":"1","specification":"","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_138/small_201601281045384140.jpg"},{"goods_id":"135808","goods_name":"云涛纯棉毛巾柔软亲肤洗脸面巾 彩色擦脸巾洗脸毛巾正品32*70cm","quantity":"1","specification":"","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_167/small_201603071532473740.png"},{"goods_id":"137418","goods_name":"飘柔洗发水家庭护理芦荟长效止痒滋润洗发露190ml","quantity":"1","specification":"","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_8/small_201605041423283658.png"},{"goods_id":"137422","goods_name":"Gillette吉列威锋旋转双层剃须刀片男士手动刮胡刀头3片装无刀架","quantity":"1","specification":"","goods_image":"http://www.hr1g.net/data/files/store_19077/goods_79/small_201605041517596501.png"}]
     */

    private int code;
    private String description;
    /**
     * goods_id : 133701
     * goods_name : 皓齿健 芨效全优120g牙膏
     * quantity : 1
     * specification :
     * goods_image : http://www.hr1g.net/data/files/store_19077/goods_172/small_201601121546122244.jpg
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
        private String goods_id;
        private String goods_name;
        private String quantity;
        private String specification;
        private String goods_image;

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

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }
    }
}
