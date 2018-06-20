package com.dxslm.entity.student;

import java.util.List;


public class StudentCommentList {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * article_id : 83ba90f6-58f8-4bdd-a790-b11a83d49816
         * sar_time : 1515653219000
         * sar_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * sar_beuser :
         * headPic : upload/headpic/20180320603589.jpg
         * sar_content : werr
         * sar_id : c907a0ad-7c4f-4139-bd2c-f4959f483df5
         * is_display :
         * username : admin
         */

        private String article_id;
        private long sar_time;
        private String sar_user;
        private String sar_beuser;
        private String headPic;
        private String sar_content;
        private String sar_id;
        private String is_display;
        private String username;
        private String busername;
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





        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public long getSar_time() {
            return sar_time;
        }

        public void setSar_time(long sar_time) {
            this.sar_time = sar_time;
        }

        public String getSar_user() {
            return sar_user;
        }

        public void setSar_user(String sar_user) {
            this.sar_user = sar_user;
        }

        public String getSar_beuser() {
            return sar_beuser;
        }

        public void setSar_beuser(String sar_beuser) {
            this.sar_beuser = sar_beuser;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getSar_content() {
            return sar_content;
        }

        public void setSar_content(String sar_content) {
            this.sar_content = sar_content;
        }

        public String getSar_id() {
            return sar_id;
        }

        public void setSar_id(String sar_id) {
            this.sar_id = sar_id;
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
