package com.dxslm.entity.student;

import java.util.List;

/**
 * Created by fxn on 2017/12/16.
 */

public class StudentList {
    /**
     * count : 2
     * list : [{"input_userschool":"001","input_time":1513409196000,"article_content":"我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生","user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","article_id":"2719d178-0023-4fbe-a0be-71567ab73109","article_title":"我是大学生","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"article_other":"我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生","is_release":"","pic_url":"upload/AroundSchoolPic/1513409195521.png,upload/AroundSchoolPic/1513409192874.png","add_time":"2017-12-16 15:26","release_time":1513409196000},{"input_userschool":"001","input_time":1513409212000,"article_content":"测试测试测试测试测试测试测试测试测试测试测试测试测试","user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","article_id":"a538a065-8f80-4956-92c1-60d367e3ad79","article_title":"测试","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"article_other":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","is_release":"","pic_url":"upload/AroundSchoolPic/1513409207959.png,upload/AroundSchoolPic/1513409211134.png","add_time":"2017-12-16 15:26","release_time":1513409212000}]
     */

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
         * input_userschool : 001
         * input_time : 1513409196000
         * article_content : 我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生
         * user_name : admin
         * input_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * key_words :
         * dept_name : 潍坊科技学院
         * is_jinghua :
         * article_id : 2719d178-0023-4fbe-a0be-71567ab73109
         * article_title : 我是大学生
         * is_zhiding :
         * release_user :
         * quan_zhong : 0
         * reply_num : 0
         * article_other : 我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生
         * is_release :
         * pic_url : upload/AroundSchoolPic/1513409195521.png,upload/AroundSchoolPic/1513409192874.png
         * add_time : 2017-12-16 15:26
         * release_time : 1513409196000
         */

        private String input_userschool;
        private long input_time;
        private String article_content;
        private String user_name;
        private String input_user;
        private String key_words;
        private String dept_name;
        private String is_jinghua;
        private String article_id;
        private String article_title;
        private String is_zhiding;
        private String release_user;
        private int quan_zhong;
        private int reply_num;
        private String article_other;
        private String is_release;
        private String pic_url;
        private String add_time;
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

        public String getArticle_content() {
            return article_content;
        }

        public void setArticle_content(String article_content) {
            this.article_content = article_content;
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

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
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

        public String getArticle_other() {
            return article_other;
        }

        public void setArticle_other(String article_other) {
            this.article_other = article_other;
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

        public long getRelease_time() {
            return release_time;
        }

        public void setRelease_time(long release_time) {
            this.release_time = release_time;
        }
    }
}
