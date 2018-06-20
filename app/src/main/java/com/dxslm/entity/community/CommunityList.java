package com.dxslm.entity.community;

import java.util.List;


public class CommunityList {

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
         * community_id : 125417f2-3e36-4134-8427-57da9c5442f5
         * community_instruction : 1111
         * contact_way : 1111
         * community_name : 11
         * community_address : 11
         * she_zhang : 11
         * pic_url : upload/AroundSchoolPic/1513669514638.png,upload/AroundSchoolPic/1513669518176.png
         * honoraward : 11
         * community_scope : 11
         */

        private String community_id;
        private String community_instruction;
        private String contact_way;
        private String community_name;
        private String community_address;
        private String she_zhang;
        private String pic_url;
        private String honoraward;
        private int community_scope;

        public String getCommunity_id() {
            return community_id;
        }

        public void setCommunity_id(String community_id) {
            this.community_id = community_id;
        }

        public String getCommunity_instruction() {
            return community_instruction;
        }

        public void setCommunity_instruction(String community_instruction) {
            this.community_instruction = community_instruction;
        }

        public String getContact_way() {
            return contact_way;
        }

        public void setContact_way(String contact_way) {
            this.contact_way = contact_way;
        }

        public String getCommunity_name() {
            return community_name;
        }

        public void setCommunity_name(String community_name) {
            this.community_name = community_name;
        }

        public String getCommunity_address() {
            return community_address;
        }

        public void setCommunity_address(String community_address) {
            this.community_address = community_address;
        }

        public String getShe_zhang() {
            return she_zhang;
        }

        public void setShe_zhang(String she_zhang) {
            this.she_zhang = she_zhang;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getHonoraward() {
            return honoraward;
        }

        public void setHonoraward(String honoraward) {
            this.honoraward = honoraward;
        }

        public int getCommunity_scope() {
            return community_scope;
        }

        public void setCommunity_scope(int community_scope) {
            this.community_scope = community_scope;
        }
    }
}
