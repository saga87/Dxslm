package com.dxslm.entity.otherwork;

import java.util.List;

/**
 * Created by fxn on 2017/12/26.
 */

public class OtherWorkCommentList {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * ptr_id : 82da6445-f4bd-4559-9144-5c1a88a252a4
         * username : admin
         * ptr_time : 1520403487000
         * ptr_content : 12
         * is_display :
         * ptj_id : 796c593a-5f67-4487-8bb2-ede2d57a194b
         * ptr_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * ptr_beuser :
         * busername : admin
         * headPic : upload/headpic/20180316211859.jpg
         */

        private String ptr_id;
        private String username;
        private long ptr_time;
        private String ptr_content;
        private String is_display;
        private String ptj_id;
        private String ptr_user;
        private String ptr_beuser;
        private String busername;
        private String headPic;

        public String getPtr_id() {
            return ptr_id;
        }

        public void setPtr_id(String ptr_id) {
            this.ptr_id = ptr_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public long getPtr_time() {
            return ptr_time;
        }

        public void setPtr_time(long ptr_time) {
            this.ptr_time = ptr_time;
        }

        public String getPtr_content() {
            return ptr_content;
        }

        public void setPtr_content(String ptr_content) {
            this.ptr_content = ptr_content;
        }

        public String getIs_display() {
            return is_display;
        }

        public void setIs_display(String is_display) {
            this.is_display = is_display;
        }

        public String getPtj_id() {
            return ptj_id;
        }

        public void setPtj_id(String ptj_id) {
            this.ptj_id = ptj_id;
        }

        public String getPtr_user() {
            return ptr_user;
        }

        public void setPtr_user(String ptr_user) {
            this.ptr_user = ptr_user;
        }

        public String getPtr_beuser() {
            return ptr_beuser;
        }

        public void setPtr_beuser(String ptr_beuser) {
            this.ptr_beuser = ptr_beuser;
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
