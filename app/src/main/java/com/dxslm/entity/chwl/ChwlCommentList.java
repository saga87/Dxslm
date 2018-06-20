package com.dxslm.entity.chwl;

import java.util.List;


public class ChwlCommentList {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * busername :
         * ser_beuser :
         * ser_time : 1521618374000
         * ser_id : 12bea9fc-debb-4d87-aef7-28090319b8c7
         * ser_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * ser_content : [emoji_11][emoji_12]
         * headPic : upload/headpic/2018032256633.jpg
         * seller_id : 3ff1d592-0a6a-4283-be27-fda0b4a5b686
         * is_display :
         * username : ADM
         */

        private String busername;
        private String ser_beuser;
        private long ser_time;
        private String ser_id;
        private String ser_user;
        private String ser_content;
        private String headPic;
        private String seller_id;
        private String is_display;
        private String username;
        private String pic_url;
        private String dept_id;

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getBusername() {
            return busername;
        }

        public void setBusername(String busername) {
            this.busername = busername;
        }

        public String getSer_beuser() {
            return ser_beuser;
        }

        public void setSer_beuser(String ser_beuser) {
            this.ser_beuser = ser_beuser;
        }

        public long getSer_time() {
            return ser_time;
        }

        public void setSer_time(long ser_time) {
            this.ser_time = ser_time;
        }

        public String getSer_id() {
            return ser_id;
        }

        public void setSer_id(String ser_id) {
            this.ser_id = ser_id;
        }

        public String getSer_user() {
            return ser_user;
        }

        public void setSer_user(String ser_user) {
            this.ser_user = ser_user;
        }

        public String getSer_content() {
            return ser_content;
        }

        public void setSer_content(String ser_content) {
            this.ser_content = ser_content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getIs_display() {
            return is_display;
        }

        public void setIs_display(String is_display) {
            this.is_display = is_display;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
