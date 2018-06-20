package com.dxslm.entity.user;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class PersonMessage {


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
         * user_tel : 17814253652
         * user_realname : ADM
         * headpic : upload/headpic/20180329269529.jpg
         * backgroundpic : upload/headpic/2018032967071.jpg
         */

        private String user_email;
        private String user_tel;
        private String user_realname;
        private String headpic;
        private String backgroundpic;

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
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

        public String getBackgroundpic() {
            return backgroundpic;
        }

        public void setBackgroundpic(String backgroundpic) {
            this.backgroundpic = backgroundpic;
        }
    }
}
