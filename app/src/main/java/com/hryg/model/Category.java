package com.hryg.model;

import java.util.List;

public class Category {


    /**
     * code : 1
     * description : 获取成功
     * data : [{"id":"1298","name":"服饰/内衣","img":"","children":[{"id":"1299","name":"女装","img":""},{"id":"1300","name":"男装","img":""},{"id":"1301","name":"女鞋","img":""},{"id":"1302","name":"男鞋","img":""},{"id":"1303","name":"运动","img":""},{"id":"1307","name":"服饰配件","img":""},{"id":"1434","name":"童装","img":""},{"id":"2100","name":"情侣装/情侣鞋","img":""},{"id":"7970","name":"泳具/泳衣","img":""},{"id":"10965","name":"女士内衣","img":""},{"id":"10966","name":"男士内衣","img":""},{"id":"11254","name":"睡衣","img":""},{"id":"11458","name":"工作服/员工制服","img":""},{"id":"13140","name":"婚纱/礼服","img":""},{"id":"13334","name":"亲子装","img":""}]},{"id":"13626","name":"食品饮料","img":"","children":[{"id":"13627","name":"牛奶乳品","img":""},{"id":"13628","name":"饼干糕点","img":""},{"id":"13845","name":"饮料/水","img":""},{"id":"13847","name":"坚果炒货","img":""},{"id":"13848","name":"冲调保健","img":""},{"id":"14175","name":"休闲零食","img":""}]}]
     */

    private int code;
    private String description;
    /**
     * id : 1298
     * name : 服饰/内衣
     * img :
     * children : [{"id":"1299","name":"女装","img":""},{"id":"1300","name":"男装","img":""},{"id":"1301","name":"女鞋","img":""},{"id":"1302","name":"男鞋","img":""},{"id":"1303","name":"运动","img":""},{"id":"1307","name":"服饰配件","img":""},{"id":"1434","name":"童装","img":""},{"id":"2100","name":"情侣装/情侣鞋","img":""},{"id":"7970","name":"泳具/泳衣","img":""},{"id":"10965","name":"女士内衣","img":""},{"id":"10966","name":"男士内衣","img":""},{"id":"11254","name":"睡衣","img":""},{"id":"11458","name":"工作服/员工制服","img":""},{"id":"13140","name":"婚纱/礼服","img":""},{"id":"13334","name":"亲子装","img":""}]
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
        private String id;
        private String name;
        private String img;
        /**
         * id : 1299
         * name : 女装
         * img :
         */

        private List<ChildrenBean> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            private String id;
            private String name;
            private String img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
