package com.dxslm.entity.chwl;

import java.util.List;


public class ChwlList {

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * seller_tel : 123123123
         * seller_zhaopai : 蜜雪冰城
         * distance :
         * input_time : 1514104122000
         * user_name : wkrj
         * input_user : 1
         * seller_perfee : 13
         * type : 1
         * pic_url : upload/SellerPic/1514104121931.jpg
         * add_time : 2017-12-24 16:28
         * seller_id : 6d1b046c-2fc6-4ef8-9e4a-6cfa40ceef9a
         * seller_address : 图书馆
         */

        private String seller_tel;
        private String seller_zhaopai;
        private String distance;
        private long input_time;
        private String user_name;
        private String input_user;
        private int seller_perfee;
        private String type;
        private String pic_url;
        private String add_time;
        private String seller_id;
        private String seller_address;

        public String getSeller_tel() {
            return seller_tel;
        }

        public void setSeller_tel(String seller_tel) {
            this.seller_tel = seller_tel;
        }

        public String getSeller_zhaopai() {
            return seller_zhaopai;
        }

        public void setSeller_zhaopai(String seller_zhaopai) {
            this.seller_zhaopai = seller_zhaopai;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public long getInput_time() {
            return input_time;
        }

        public void setInput_time(long input_time) {
            this.input_time = input_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getInput_user() {
            return input_user;
        }

        public void setInput_user(String input_user) {
            this.input_user = input_user;
        }

        public int getSeller_perfee() {
            return seller_perfee;
        }

        public void setSeller_perfee(int seller_perfee) {
            this.seller_perfee = seller_perfee;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getSeller_address() {
            return seller_address;
        }

        public void setSeller_address(String seller_address) {
            this.seller_address = seller_address;
        }
    }
}
