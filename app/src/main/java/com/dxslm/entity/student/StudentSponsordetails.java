package com.dxslm.entity.student;

import java.util.List;


public class StudentSponsordetails {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * support_money : 3000
         * activity_name : 潍科校卫队
         * support_id : 06599590-f129-4d69-9830-64d8cce9fa8c
         * input_userschool : 0
         * support_name : 维科软件
         * input_time : 1513994542000
         * user_name : wkrj
         * input_user : 1
         * dept_name : 学校名称
         * pic_url : upload/SupportPic/1513994483049.jpg,upload/SupportPic/1513994483101.jpg
         * add_time : 2017-12-23 10:02
         */

        private int support_money;
        private String activity_name;
        private String support_id;
        private String input_userschool;
        private String support_name;
        private long input_time;
        private String user_name;
        private String input_user;
        private String dept_name;
        private String pic_url;
        private String add_time;

        public int getSupport_money() {
            return support_money;
        }

        public void setSupport_money(int support_money) {
            this.support_money = support_money;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getSupport_id() {
            return support_id;
        }

        public void setSupport_id(String support_id) {
            this.support_id = support_id;
        }

        public String getInput_userschool() {
            return input_userschool;
        }

        public void setInput_userschool(String input_userschool) {
            this.input_userschool = input_userschool;
        }

        public String getSupport_name() {
            return support_name;
        }

        public void setSupport_name(String support_name) {
            this.support_name = support_name;
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
