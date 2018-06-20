package com.dxslm.entity.news;

import java.util.List;

/**
 * Created by fxn on 2017/12/18.
 */

public class NewCommentList {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * hnr_time : 1521618107000
         * hnr_content : [emoji_03]
         * busername :
         * hnr_id : 3dadabaa-3672-400b-96bd-c4ac9d72c06a
         * hnr_beuser :
         * pic_url :
         * headPic : upload/headpic/2018032633139.jpg
         * news_id : 923ef115-f1e5-4e68-bf56-f0c52ef6e62a
         * hnr_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * is_display :
         * username : ADM
         */

        private long hnr_time;
        private String hnr_content;
        private String busername;
        private String hnr_id;
        private String hnr_beuser;
        private String pic_url;
        private String headPic;
        private String news_id;
        private String hnr_user;
        private String is_display;
        private String username;
        private String dept_id;

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }


        public long getHnr_time() {
            return hnr_time;
        }

        public void setHnr_time(long hnr_time) {
            this.hnr_time = hnr_time;
        }

        public String getHnr_content() {
            return hnr_content;
        }

        public void setHnr_content(String hnr_content) {
            this.hnr_content = hnr_content;
        }

        public String getBusername() {
            return busername;
        }

        public void setBusername(String busername) {
            this.busername = busername;
        }

        public String getHnr_id() {
            return hnr_id;
        }

        public void setHnr_id(String hnr_id) {
            this.hnr_id = hnr_id;
        }

        public String getHnr_beuser() {
            return hnr_beuser;
        }

        public void setHnr_beuser(String hnr_beuser) {
            this.hnr_beuser = hnr_beuser;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getHnr_user() {
            return hnr_user;
        }

        public void setHnr_user(String hnr_user) {
            this.hnr_user = hnr_user;
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
