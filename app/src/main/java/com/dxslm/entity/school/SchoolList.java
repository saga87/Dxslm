package com.dxslm.entity.school;

import java.util.List;

/**
 * Created by fxn on 2017/12/16.
 */

public class SchoolList {
    /**
     * count : 2
     * list : [{"info_title":"123","input_userschool":"0","input_time":1513390921000,"user_name":"wkrj","input_user":"1","key_words":"","dept_name":"学校名称","is_jinghua":"","is_zhiding":"","info_id":"b0d2fab7-a754-4d9d-94c0-60b42f1d6dfb","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/AroundSchoolPic/1513390917140.png,upload/AroundSchoolPic/1513390918719.png,upload/AroundSchoolPic/1513390920411.png","info_content":"123123","release_time":1513390921000},{"info_title":"QQQ","input_userschool":"001","input_time":1513391274000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","is_zhiding":"","info_id":"fd09de21-6b04-43dc-8e47-c67c311987c5","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/AroundSchoolPic/1513391273516.png,upload/AroundSchoolPic/1513391270609.png","info_content":"QQQQQQ","release_time":1513391274000}]
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
         * info_title : 123
         * input_userschool : 0
         * input_time : 1513390921000
         * user_name : wkrj
         * input_user : 1
         * key_words :
         * dept_name : 学校名称
         * is_jinghua :
         * is_zhiding :
         * info_id : b0d2fab7-a754-4d9d-94c0-60b42f1d6dfb
         * release_user :
         * quan_zhong : 0
         * reply_num : 0
         * is_release :
         * pic_url : upload/AroundSchoolPic/1513390917140.png,upload/AroundSchoolPic/1513390918719.png,upload/AroundSchoolPic/1513390920411.png
         * info_content : 123123
         * release_time : 1513390921000
         */

        private String info_title;
        private String input_userschool;
        private long input_time;
        private String user_name;
        private String input_user;
        private String key_words;
        private String dept_name;
        private String is_jinghua;
        private String is_zhiding;
        private String info_id;
        private String release_user;
        private int quan_zhong;
        private int reply_num;
        private String is_release;
        private String pic_url;
        private String info_content;
        private long release_time;

        public String getInfo_title() {
            return info_title;
        }

        public void setInfo_title(String info_title) {
            this.info_title = info_title;
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

        public String getIs_zhiding() {
            return is_zhiding;
        }

        public void setIs_zhiding(String is_zhiding) {
            this.is_zhiding = is_zhiding;
        }

        public String getInfo_id() {
            return info_id;
        }

        public void setInfo_id(String info_id) {
            this.info_id = info_id;
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

        public String getInfo_content() {
            return info_content;
        }

        public void setInfo_content(String info_content) {
            this.info_content = info_content;
        }

        public long getRelease_time() {
            return release_time;
        }

        public void setRelease_time(long release_time) {
            this.release_time = release_time;
        }
    }
}
