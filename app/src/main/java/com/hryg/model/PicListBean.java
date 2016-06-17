package com.hryg.model;

/**
 * Created by kefanbufan on 16/6/7.
 */
public class PicListBean {


    /**
     * code : 1
     * description : 获取成功
     * img1 : {"id1":"13","img1":"http://www.hr1g.net/images/advert/img1.jpeg"}
     * img2 : {"id2":"14","img2":"http://www.hr1g.net/images/advert/img2.png"}
     * img3 : {"id3":"15","img3":"http://www.hr1g.net/images/advert/img3.jpeg"}
     * img4 : {"id4":"16","img4":"http://www.hr1g.net/images/advert/img4.jpeg"}
     */

    private String code;
    private String description;
    /**
     * id1 : 13
     * img1 : http://www.hr1g.net/images/advert/img1.jpeg
     */

    private Img1Bean img1;
    /**
     * id2 : 14
     * img2 : http://www.hr1g.net/images/advert/img2.png
     */

    private Img2Bean img2;
    /**
     * id3 : 15
     * img3 : http://www.hr1g.net/images/advert/img3.jpeg
     */

    private Img3Bean img3;
    /**
     * id4 : 16
     * img4 : http://www.hr1g.net/images/advert/img4.jpeg
     */

    private Img4Bean img4;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Img1Bean getImg1() {
        return img1;
    }

    public void setImg1(Img1Bean img1) {
        this.img1 = img1;
    }

    public Img2Bean getImg2() {
        return img2;
    }

    public void setImg2(Img2Bean img2) {
        this.img2 = img2;
    }

    public Img3Bean getImg3() {
        return img3;
    }

    public void setImg3(Img3Bean img3) {
        this.img3 = img3;
    }

    public Img4Bean getImg4() {
        return img4;
    }

    public void setImg4(Img4Bean img4) {
        this.img4 = img4;
    }

    public static class Img1Bean {
        private String id1;
        private String img1;

        public String getId1() {
            return id1;
        }

        public void setId1(String id1) {
            this.id1 = id1;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }
    }

    public static class Img2Bean {
        private String id2;
        private String img2;

        public String getId2() {
            return id2;
        }

        public void setId2(String id2) {
            this.id2 = id2;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }
    }

    public static class Img3Bean {
        private String id3;
        private String img3;

        public String getId3() {
            return id3;
        }

        public void setId3(String id3) {
            this.id3 = id3;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }
    }

    public static class Img4Bean {
        private String id4;
        private String img4;

        public String getId4() {
            return id4;
        }

        public void setId4(String id4) {
            this.id4 = id4;
        }

        public String getImg4() {
            return img4;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }
    }
}
