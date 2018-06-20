package com.dxslm.entity.school;

import java.util.List;

/**
 * Created by fxn on 2017/12/19.
 */

public class SchoolCommentList {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * crr_user : 430c0ccb-355e-4e27-adac-15dfee7d3802
         * busername :
         * crr_content : 121
         * info_id : 032b5f3e-e736-4d93-9ac0-22972382d4f4
         * crr_id : 707e60e7-aa9a-4efb-9576-a38dab8c5ca4
         * crr_time : 1521769154000
         * crr_beuser :
         * is_display :
         * username : 管理员01
         * headPic : upload/headpic/2018032256633.jpg
         */

        private String crr_user;
        private String busername;
        private String crr_content;
        private String info_id;
        private String crr_id;
        private long crr_time;
        private String crr_beuser;
        private String is_display;
        private String username;
        private String headPic;
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

        public String getCrr_user() {
            return crr_user;
        }

        public void setCrr_user(String crr_user) {
            this.crr_user = crr_user;
        }

        public String getBusername() {
            return busername;
        }

        public void setBusername(String busername) {
            this.busername = busername;
        }

        public String getCrr_content() {
            return crr_content;
        }

        public void setCrr_content(String crr_content) {
            this.crr_content = crr_content;
        }

        public String getInfo_id() {
            return info_id;
        }

        public void setInfo_id(String info_id) {
            this.info_id = info_id;
        }

        public String getCrr_id() {
            return crr_id;
        }

        public void setCrr_id(String crr_id) {
            this.crr_id = crr_id;
        }

        public long getCrr_time() {
            return crr_time;
        }

        public void setCrr_time(long crr_time) {
            this.crr_time = crr_time;
        }

        public String getCrr_beuser() {
            return crr_beuser;
        }

        public void setCrr_beuser(String crr_beuser) {
            this.crr_beuser = crr_beuser;
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

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }
    }
}
