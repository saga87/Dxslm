package com.dxslm.entity.news;

import java.io.Serializable;
import java.util.List;


public class NewDetails implements Serializable{


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * input_userschool : 001
         * input_time : 1513241557000
         * user_name : admin
         * input_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * key_words :
         * dept_name : 潍坊科技学院
         * is_jinghua :
         * news_id : abb91fb3-e6e3-406f-9334-1118d5b7a9df
         * news_other :
         * is_zhiding :
         * release_user :
         * quan_zhong : 0
         * news_content : 222
         * reply_num : 0
         * news_title : 22
         * is_release :
         * pic_url : upload/HotNewsPic/1513241555230.png,upload/HotNewsPic/1513241556674.png,upload/HotNewsPic/1513241553474.png
         * release_time : 1513241557000
         */

        private String input_userschool;
        private long input_time;
        private String user_name;
        private String input_user;
        private String key_words;
        private String dept_name;
        private String is_jinghua;
        private String news_id;
        private String news_other;
        private String is_zhiding;
        private String release_user;
        private int quan_zhong;
        private String news_content;
        private int reply_num;
        private String news_title;
        private String is_release;
        private String pic_url;
        private long release_time;

        public String getInput_userschool() {
            return input_userschool;
        }

        public void setInput_userschool(String input_userschool) {
            this.input_userschool = input_userschool;
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

        public String getKey_words() {
            return key_words;
        }

        public void setKey_words(String key_words) {
            this.key_words = key_words;
        }

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getIs_jinghua() {
            return is_jinghua;
        }

        public void setIs_jinghua(String is_jinghua) {
            this.is_jinghua = is_jinghua;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getNews_other() {
            return news_other;
        }

        public void setNews_other(String news_other) {
            this.news_other = news_other;
        }

        public String getIs_zhiding() {
            return is_zhiding;
        }

        public void setIs_zhiding(String is_zhiding) {
            this.is_zhiding = is_zhiding;
        }

        public String getRelease_user() {
            return release_user;
        }

        public void setRelease_user(String release_user) {
            this.release_user = release_user;
        }

        public int getQuan_zhong() {
            return quan_zhong;
        }

        public void setQuan_zhong(int quan_zhong) {
            this.quan_zhong = quan_zhong;
        }

        public String getNews_content() {
            return news_content;
        }

        public void setNews_content(String news_content) {
            this.news_content = news_content;
        }

        public int getReply_num() {
            return reply_num;
        }

        public void setReply_num(int reply_num) {
            this.reply_num = reply_num;
        }

        public String getNews_title() {
            return news_title;
        }

        public void setNews_title(String news_title) {
            this.news_title = news_title;
        }

        public String getIs_release() {
            return is_release;
        }

        public void setIs_release(String is_release) {
            this.is_release = is_release;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public long getRelease_time() {
            return release_time;
        }

        public void setRelease_time(long release_time) {
            this.release_time = release_time;
        }
    }
}
