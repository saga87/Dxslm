package com.dxslm.entity.forum;

import java.util.List;


public class ForumCommentList {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * busername :
         * fr_content : 231
         * fr_time : 1521768580000
         * fr_beuser :
         * fr_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * fr_id : 01e1c966-5298-4470-a9ce-52af8621c835
         * headPic : upload/headpic/2018032256633.jpg
         * forum_id : f323d957-7357-464f-acc0-b420dae4ec50
         * is_display :
         * username : ADM
         */

        private String busername;
        private String fr_content;
        private long fr_time;
        private String fr_beuser;
        private String fr_user;
        private String fr_id;
        private String headPic;
        private String forum_id;
        private String is_display;
        private String username;
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

        public String getFr_content() {
            return fr_content;
        }

        public void setFr_content(String fr_content) {
            this.fr_content = fr_content;
        }

        public long getFr_time() {
            return fr_time;
        }

        public void setFr_time(long fr_time) {
            this.fr_time = fr_time;
        }

        public String getFr_beuser() {
            return fr_beuser;
        }

        public void setFr_beuser(String fr_beuser) {
            this.fr_beuser = fr_beuser;
        }

        public String getFr_user() {
            return fr_user;
        }

        public void setFr_user(String fr_user) {
            this.fr_user = fr_user;
        }

        public String getFr_id() {
            return fr_id;
        }

        public void setFr_id(String fr_id) {
            this.fr_id = fr_id;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getForum_id() {
            return forum_id;
        }

        public void setForum_id(String forum_id) {
            this.forum_id = forum_id;
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
