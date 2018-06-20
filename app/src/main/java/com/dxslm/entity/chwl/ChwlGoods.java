package com.dxslm.entity.chwl;

import java.util.List;


public class ChwlGoods {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * menu_price : 2
         * input_time : 1515032018000
         * menu_name : 甜不辣
         * input_user : 1
         * pic_url : upload/SellerPic/1515032018367.jpg
         * seller_id : 43df508f-071e-4c39-8fdf-3747eef209fe
         * menu_id : a3b14165-926d-4d11-b368-63f4768f2f48
         */

        private String menu_price;
        private long input_time;
        private String menu_name;
        private String input_user;
        private String pic_url;
        private String seller_id;
        private String menu_id;

        public String getMenu_price() {
            return menu_price;
        }

        public void setMenu_price(String menu_price) {
            this.menu_price = menu_price;
        }

        public long getInput_time() {
            return input_time;
        }

        public void setInput_time(long input_time) {
            this.input_time = input_time;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }

        public String getInput_user() {
            return input_user;
        }

        public void setInput_user(String input_user) {
            this.input_user = input_user;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }
    }
}
