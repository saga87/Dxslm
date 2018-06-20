package com.dxslm.entity.issue;

import java.util.List;

/**
 * Created by fxn on 2017/12/27.
 */

public class IssueList {


    /**
     * count : 16
     */

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
         * tltle :
         * user_id : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * id : 03a74c6f-5813-40ae-968b-ee8aa755a2ea
         * time : 1514344434000
         * pic_url :
         * type : 1
         * content : 表白表白
         */

        private String tltle;
        private String user_id;
        private String id;
        private long time;
        private String pic_url;
        private String type;
        private String content;

        public String getTltle() {
            return tltle;
        }

        public void setTltle(String tltle) {
            this.tltle = tltle;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
