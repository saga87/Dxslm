package com.dxslm.entity.issue;


import java.util.List;

/**
 * Created by fxn on 2017/12/27.
 */

public class IssueDetails {

    private List<ListBean> list;

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
         * nickname : admin
         * id : 5d79b55f-ff72-430e-8167-2d2b0252b93f
         * time : 1515655166000
         * pic_url : upload/BbPic/20180111705970.jpg,upload/BbPic/20180111874777.jpg
         * headpic : upload/headpic/20180111798729.jpg
         * content : erfgg
         */

        private String tltle;
        private String user_id;
        private String nickname;
        private String id;
        private long time;
        private String pic_url;
        private String headpic;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
