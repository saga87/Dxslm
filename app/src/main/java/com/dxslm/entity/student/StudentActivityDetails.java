package com.dxslm.entity.student;

import java.util.List;


public class StudentActivityDetails {

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
         * activity_endtime : 1513842420000
         * input_time : 1513842480000
         * user_name : wkrj
         * input_user : 1
         * dept_name : 学校名称
         * activity_site : 学校
         * is_jinghua :
         * activity_theme : 巡逻
         * is_zhiding :
         * activity_id : c459f6f2-31ca-4e76-b3c6-c42a65359b3a
         * quan_zhong : 0
         * activity_content : 巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻巡逻
         * activity_starttime : 1513237620000
         * pic_url : upload/AroundSchoolPic/1513842479858.jpg,upload/AroundSchoolPic/1513842480066.jpg
         */

        private String input_userschool;
        private long activity_endtime;
        private long input_time;
        private String user_name;
        private String input_user;
        private String dept_name;
        private String activity_site;
        private String is_jinghua;
        private String activity_theme;
        private String is_zhiding;
        private String activity_id;
        private int quan_zhong;
        private String activity_content;
        private long activity_starttime;
        private String pic_url;

        public String getInput_userschool() {
            return input_userschool;
        }

        public void setInput_userschool(String input_userschool) {
            this.input_userschool = input_userschool;
        }

        public long getActivity_endtime() {
            return activity_endtime;
        }

        public void setActivity_endtime(long activity_endtime) {
            this.activity_endtime = activity_endtime;
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

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getActivity_site() {
            return activity_site;
        }

        public void setActivity_site(String activity_site) {
            this.activity_site = activity_site;
        }

        public String getIs_jinghua() {
            return is_jinghua;
        }

        public void setIs_jinghua(String is_jinghua) {
            this.is_jinghua = is_jinghua;
        }

        public String getActivity_theme() {
            return activity_theme;
        }

        public void setActivity_theme(String activity_theme) {
            this.activity_theme = activity_theme;
        }

        public String getIs_zhiding() {
            return is_zhiding;
        }

        public void setIs_zhiding(String is_zhiding) {
            this.is_zhiding = is_zhiding;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public int getQuan_zhong() {
            return quan_zhong;
        }

        public void setQuan_zhong(int quan_zhong) {
            this.quan_zhong = quan_zhong;
        }

        public String getActivity_content() {
            return activity_content;
        }

        public void setActivity_content(String activity_content) {
            this.activity_content = activity_content;
        }

        public long getActivity_starttime() {
            return activity_starttime;
        }

        public void setActivity_starttime(long activity_starttime) {
            this.activity_starttime = activity_starttime;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
