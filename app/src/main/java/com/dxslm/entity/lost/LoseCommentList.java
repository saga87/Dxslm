package com.dxslm.entity.lost;

import java.util.List;

/**
 * Created by fxn on 2017/12/21.
 */

public class LoseCommentList {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * username : admin
         * swzl_id : cc669f69-6a26-4d83-b34f-669b18ce15c5
         * swzlr_id : 901561b1-a63a-46a9-8f4a-4693015de18b
         * is_display :
         * swzlr_content : 12
         * swzlr_time : 1521265428000
         * swzlr_beuser :
         * swzlr_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * busername : admin
         * headPic : upload/headpic/20180316211859.jpg
         */

        private String username;
        private String swzl_id;
        private String swzlr_id;
        private String is_display;
        private String swzlr_content;
        private long swzlr_time;
        private String swzlr_beuser;
        private String swzlr_user;
        private String busername;
        private String headPic;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSwzl_id() {
            return swzl_id;
        }

        public void setSwzl_id(String swzl_id) {
            this.swzl_id = swzl_id;
        }

        public String getSwzlr_id() {
            return swzlr_id;
        }

        public void setSwzlr_id(String swzlr_id) {
            this.swzlr_id = swzlr_id;
        }

        public String getIs_display() {
            return is_display;
        }

        public void setIs_display(String is_display) {
            this.is_display = is_display;
        }

        public String getSwzlr_content() {
            return swzlr_content;
        }

        public void setSwzlr_content(String swzlr_content) {
            this.swzlr_content = swzlr_content;
        }

        public long getSwzlr_time() {
            return swzlr_time;
        }

        public void setSwzlr_time(long swzlr_time) {
            this.swzlr_time = swzlr_time;
        }

        public String getSwzlr_beuser() {
            return swzlr_beuser;
        }

        public void setSwzlr_beuser(String swzlr_beuser) {
            this.swzlr_beuser = swzlr_beuser;
        }

        public String getSwzlr_user() {
            return swzlr_user;
        }

        public void setSwzlr_user(String swzlr_user) {
            this.swzlr_user = swzlr_user;
        }

        public String getBusername() {
            return busername;
        }

        public void setBusername(String busername) {
            this.busername = busername;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }
    }
}
