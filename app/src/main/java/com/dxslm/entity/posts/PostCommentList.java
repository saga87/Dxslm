package com.dxslm.entity.posts;

import java.util.List;


public class PostCommentList {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * pr_time : 1521682020000
         * pr_content : 好
         * pr_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * username : admin
         * pr_beuser :
         * pr_id : 86966329-c935-4078-b32c-15a5672a0283
         * headPic : upload/headpic/20180316211859.jpg
         * post_id : 1dbe0d9b-77a8-40fe-a61b-4d7e77d9fabe
         */

        private long pr_time;
        private String pr_content;
        private String pr_user;
        private String username;
        private String pr_beuser;
        private String pr_id;
        private String headPic;
        private String post_id;
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

        public long getPr_time() {
            return pr_time;
        }

        public void setPr_time(long pr_time) {
            this.pr_time = pr_time;
        }

        public String getPr_content() {
            return pr_content;
        }

        public void setPr_content(String pr_content) {
            this.pr_content = pr_content;
        }

        public String getPr_user() {
            return pr_user;
        }

        public void setPr_user(String pr_user) {
            this.pr_user = pr_user;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPr_beuser() {
            return pr_beuser;
        }

        public void setPr_beuser(String pr_beuser) {
            this.pr_beuser = pr_beuser;
        }

        public String getPr_id() {
            return pr_id;
        }

        public void setPr_id(String pr_id) {
            this.pr_id = pr_id;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }
    }
}
