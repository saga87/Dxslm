package com.dxslm.entity.love;

import java.util.List;

/**
 * Created by fxn on 2017/12/21.
 */

public class LoveCommentList {
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
         * bb_id : 0ad81a0d-d4b0-4108-9e95-9e44e9cffec5
         * bbr_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * bbr_id : f54dde8a-16d8-4097-846f-5b5746a8082a
         * bbr_content : [emoji_03]
         * is_display :
         * bbr_beuser :
         * bbr_time : 1521619148000
         * busername : admin
         * headPic : upload/headpic/20180316211859.jpg
         */

        private String username;
        private String bb_id;
        private String bbr_user;
        private String bbr_id;
        private String bbr_content;
        private String is_display;
        private String bbr_beuser;
        private long bbr_time;
        private String busername;
        private String headPic;
        private String dept_id;

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBb_id() {
            return bb_id;
        }

        public void setBb_id(String bb_id) {
            this.bb_id = bb_id;
        }

        public String getBbr_user() {
            return bbr_user;
        }

        public void setBbr_user(String bbr_user) {
            this.bbr_user = bbr_user;
        }

        public String getBbr_id() {
            return bbr_id;
        }

        public void setBbr_id(String bbr_id) {
            this.bbr_id = bbr_id;
        }

        public String getBbr_content() {
            return bbr_content;
        }

        public void setBbr_content(String bbr_content) {
            this.bbr_content = bbr_content;
        }

        public String getIs_display() {
            return is_display;
        }

        public void setIs_display(String is_display) {
            this.is_display = is_display;
        }

        public String getBbr_beuser() {
            return bbr_beuser;
        }

        public void setBbr_beuser(String bbr_beuser) {
            this.bbr_beuser = bbr_beuser;
        }

        public long getBbr_time() {
            return bbr_time;
        }

        public void setBbr_time(long bbr_time) {
            this.bbr_time = bbr_time;
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

//    private List<ListBean> list;
//
//    public List<ListBean> getList() {
//        return list;
//    }
//
//    public void setList(List<ListBean> list) {
//        this.list = list;
//    }
//
//    public static class ListBean {
//        /**
//         * busername : admin
//         * bbr_time : 1513838156000
//         * bbr_content : 美啊
//         * bbr_user : 1
//         * bb_id : 6c89e454-4eec-4200-98ce-35b68f7770ee
//         * bbr_id : 09bcc48e-814e-4c4b-9ff0-e97c3a6463ea
//         * bbr_beuser : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
//         * is_display :
//         * username : wkrj
//         */
//
//        private String busername;
//        private long bbr_time;
//        private String bbr_content;
//        private String bbr_user;
//        private String bb_id;
//        private String bbr_id;
//        private String bbr_beuser;
//        private String is_display;
//        private String username;
//
//        public String getBusername() {
//            return busername;
//        }
//
//        public void setBusername(String busername) {
//            this.busername = busername;
//        }
//
//        public long getBbr_time() {
//            return bbr_time;
//        }
//
//        public void setBbr_time(long bbr_time) {
//            this.bbr_time = bbr_time;
//        }
//
//        public String getBbr_content() {
//            return bbr_content;
//        }
//
//        public void setBbr_content(String bbr_content) {
//            this.bbr_content = bbr_content;
//        }
//
//        public String getBbr_user() {
//            return bbr_user;
//        }
//
//        public void setBbr_user(String bbr_user) {
//            this.bbr_user = bbr_user;
//        }
//
//        public String getBb_id() {
//            return bb_id;
//        }
//
//        public void setBb_id(String bb_id) {
//            this.bb_id = bb_id;
//        }
//
//        public String getBbr_id() {
//            return bbr_id;
//        }
//
//        public void setBbr_id(String bbr_id) {
//            this.bbr_id = bbr_id;
//        }
//
//        public String getBbr_beuser() {
//            return bbr_beuser;
//        }
//
//        public void setBbr_beuser(String bbr_beuser) {
//            this.bbr_beuser = bbr_beuser;
//        }
//
//        public String getIs_display() {
//            return is_display;
//        }
//
//        public void setIs_display(String is_display) {
//            this.is_display = is_display;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//    }
}
