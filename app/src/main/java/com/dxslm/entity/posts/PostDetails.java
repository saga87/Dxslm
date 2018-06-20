package com.dxslm.entity.posts;

import java.util.List;

/**
 * Created by fxn on 2017/12/15.
 */

public class PostDetails {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * post_title : 123
         * input_userschool : 001
         * input_time : 1513059448000
         * user_name : admin
         * input_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * key_words :
         * dept_name : 潍坊科技学院
         * is_jinghua :
         * post_content : 123
         * post_id : bf8313fa-2f98-4153-97a9-8e4bad50f7d0
         * is_zhiding :
         * release_user :
         * quan_zhong : 0
         * reply_num : 0
         * is_release :
         * pic_url : upload/InteractivepostPic/1513059394225.png
         * release_time : 1513059448000
         */

        private String post_title;
        private String input_userschool;
        private long input_time;
        private String user_name;
        private String input_user;
        private String key_words;
        private String dept_name;
        private String is_jinghua;
        private String post_content;
        private String post_id;
        private String is_zhiding;
        private String release_user;
        private int quan_zhong;
        private int reply_num;
        private String is_release;
        private String pic_url;
        private long release_time;

        private String headpic;

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

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

        public String getPost_content() {
            return post_content;
        }

        public void setPost_content(String post_content) {
            this.post_content = post_content;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
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

        public int getReply_num() {
            return reply_num;
        }

        public void setReply_num(int reply_num) {
            this.reply_num = reply_num;
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
