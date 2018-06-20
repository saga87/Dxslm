package com.dxslm.entity.used;

import java.util.List;


public class UsedCommentList {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * shr_id : 8f5a1f3f-da83-44a1-86af-12470102aabd
         * shr_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * shr_content : 我有我有
         * shr_time : 1514019277000
         * shi_id : 194c5d9b-deb4-4a08-afcc-fe100846d997
         * is_display :
         * username : admin
         * shr_beuser : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * busername : admin
         */

        private String shr_id;
        private String shr_user;
        private String shr_content;
        private long shr_time;
        private String shi_id;
        private String is_display;
        private String username;
        private String shr_beuser;
        private String busername;
        private String headPic;
        public String getShr_id() {
            return shr_id;
        }

        public void setShr_id(String shr_id) {
            this.shr_id = shr_id;
        }

        public String getShr_user() {
            return shr_user;
        }

        public void setShr_user(String shr_user) {
            this.shr_user = shr_user;
        }

        public String getShr_content() {
            return shr_content;
        }

        public void setShr_content(String shr_content) {
            this.shr_content = shr_content;
        }

        public long getShr_time() {
            return shr_time;
        }

        public void setShr_time(long shr_time) {
            this.shr_time = shr_time;
        }

        public String getShi_id() {
            return shi_id;
        }

        public void setShi_id(String shi_id) {
            this.shi_id = shi_id;
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

        public String getShr_beuser() {
            return shr_beuser;
        }

        public void setShr_beuser(String shr_beuser) {
            this.shr_beuser = shr_beuser;
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
