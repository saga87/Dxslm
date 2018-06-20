package com.dxslm.entity.forum;

import java.util.List;


public class ForumDetails {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * input_userschool : 0
         * input_time : 1513755231000
         * user_name : wkrj
         * input_user : 1
         * key_words :
         * dept_name : 学校名称
         * forum_title : 123
         * is_jinghua :
         * forum_id : 100d2e45-4c31-4688-92bc-73ee8ce66262
         * forum_content : 123
         * is_zhiding :
         * quan_zhong : 0
         * reply_num : 1
         * is_release :
         * pic_url : upload/AroundSchoolPic/1513755230424.jpg,upload/AroundSchoolPic/1513755230372.jpg
         * add_time : 2017-12-20 15:33
         */

        private String input_userschool;
        private long input_time;
        private String user_name;
        private String input_user;
        private String key_words;
        private String dept_name;
        private String forum_title;
        private String is_jinghua;
        private String forum_id;
        private String forum_content;
        private String is_zhiding;
        private int quan_zhong;
        private int reply_num;
        private String is_release;
        private String pic_url;
        private String add_time;
        private String headpic;

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
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

        public String getForum_title() {
            return forum_title;
        }

        public void setForum_title(String forum_title) {
            this.forum_title = forum_title;
        }

        public String getIs_jinghua() {
            return is_jinghua;
        }

        public void setIs_jinghua(String is_jinghua) {
            this.is_jinghua = is_jinghua;
        }

        public String getForum_id() {
            return forum_id;
        }

        public void setForum_id(String forum_id) {
            this.forum_id = forum_id;
        }

        public String getForum_content() {
            return forum_content;
        }

        public void setForum_content(String forum_content) {
            this.forum_content = forum_content;
        }

        public String getIs_zhiding() {
            return is_zhiding;
        }

        public void setIs_zhiding(String is_zhiding) {
            this.is_zhiding = is_zhiding;
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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
