package com.dxslm.entity.collect;

import java.util.List;


public class CollectList {


    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * ct_id : 2719d178-0023-4fbe-a0be-71567ab73109
         * collect_content : 我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生我是大学生
         * collect_id : 0702754f-3acb-44ff-bc68-5d11eb781b10
         * collect_type : 2
         * days : 4
         * collect_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * collect_time : 1514516176000
         * usernc : 管理员
         * headpic : upload/headpic/2017122731520.jpg
         */

        private String ct_id;
        private String collect_content;
        private String collect_id;
        private String collect_type;
        private int days;
        private String collect_user;
        private long collect_time;
        private String usernc;
        private String headpic;

        public String getCt_id() {
            return ct_id;
        }

        public void setCt_id(String ct_id) {
            this.ct_id = ct_id;
        }

        public String getCollect_content() {
            return collect_content;
        }

        public void setCollect_content(String collect_content) {
            this.collect_content = collect_content;
        }

        public String getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }

        public String getCollect_type() {
            return collect_type;
        }

        public void setCollect_type(String collect_type) {
            this.collect_type = collect_type;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getCollect_user() {
            return collect_user;
        }

        public void setCollect_user(String collect_user) {
            this.collect_user = collect_user;
        }

        public long getCollect_time() {
            return collect_time;
        }

        public void setCollect_time(long collect_time) {
            this.collect_time = collect_time;
        }

        public String getUsernc() {
            return usernc;
        }

        public void setUsernc(String usernc) {
            this.usernc = usernc;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }
    }
}
