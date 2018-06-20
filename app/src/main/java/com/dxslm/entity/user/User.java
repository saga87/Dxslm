package com.dxslm.entity.user;

import java.util.List;


public class User {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * user_email : aa
         * background_pic : upload/headpic/20180329489538.jpg
         * user_tel : 17814253652
         * user_realname : ADM
         * headpic : upload/headpic/20180329923463.jpg
         */

        private String user_email;
        private String background_pic;
        private String user_tel;
        private String user_realname;
        private String headpic;

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getBackground_pic() {
            return background_pic;
        }

        public void setBackground_pic(String background_pic) {
            this.background_pic = background_pic;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getUser_realname() {
            return user_realname;
        }

        public void setUser_realname(String user_realname) {
            this.user_realname = user_realname;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }
    }
}
