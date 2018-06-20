package com.dxslm.entity.community;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class ComunityList {

    /**
     * count : 6
     * list : [{"community_id":"35e440a0-1ff9-4c9b-abee-cea308717232","community_instruction":"大学社团是学生自愿组织的群众性团体，在校党委、团委的领导下，在学生会的指导下，组织开展健康、积极有益的课外文化、科技体育、艺术等活动，它组织开展我们同学们的各项活动，丰富了同学们的业余文化生活，培养学生的综合素质。它既不同于第一课堂的专业学习，又与自身的全面发展有着密切的联系。通过社团活动， 能拓展我们的视野，培养创新精神和实验能力，为我们实现自己的人生理想、从容的面对社会生活打下了坚实的基础。","input_userschool":"00101","contact_way":"123345657","input_time":1516690509000,"community_name":"心理社","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","community_address":"山东","she_zhang":"金馆长","pic_url":"upload/CommunityPic/1515117050127.jpg,upload/CommunityPic/1515117050156.jpg","honoraward":"无数","community_scope":100},{"community_id":"6b7eed5e-e507-49ae-bff0-146e72f829b3","community_instruction":"舞蹈社舞蹈社舞蹈社","input_userschool":"00101","contact_way":"123123","input_time":1521448454000,"community_name":"舞蹈社","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","community_address":"123","she_zhang":"舞蹈社","pic_url":"upload/CommunityPic/1521448453898.jpg","honoraward":"123","community_scope":123},{"community_id":"ba4ef6b2-c091-4043-8508-a1f542ba8c67","community_instruction":"1","input_userschool":"00101","contact_way":"1","input_time":1517799634000,"community_name":"1","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","community_address":"1","she_zhang":"1","honoraward":"1","community_scope":1},{"community_id":"d58bbac3-e809-4d01-922d-df1339e3a537","community_instruction":"社团名称社团名称","input_userschool":"00101","contact_way":"12312312313","input_time":1517791881000,"community_name":"社团名称","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","community_address":"社团名称社团名称","she_zhang":"社团名称","honoraward":"社团名称社团名称社团名称","community_scope":123},{"community_id":"d836d197-5321-40e5-9c61-8f0e19203e6e","community_instruction":"大学社团是学生自愿组织的群众性团体，在校党委、团委的领导下，在学生会的指导下，组织开展健康、积极有益的课外文化、科技体育、艺术等活动，它组织开展我们同学们的各项活动，丰富了同学们的业余文化生活，培养学生的综合素质。它既不同于第一课堂的专业学习，又与自身的全面发展有着密切的联系。通过社团活动， 能拓展我们的视野，培养创新精神和实验能力，为我们实现自己的人生理想、从容的面对社会生活打下了坚实的基础。","input_userschool":"00101","contact_way":"123456789","input_time":1516776914000,"community_name":"电影社","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","community_address":"潍坊科技","she_zhang":"金三胖","pic_url":"upload/CommunityPic/1515117091906.jpg,upload/CommunityPic/1515117091853.jpg","honoraward":"飒飒","community_scope":56},{"community_id":"fac5e2fd-20f4-42b8-a9ae-a6ed94d979f5","community_instruction":"社团社团社团社团社团社团社团社团社团社团社团","input_userschool":"00101","contact_way":"123345667","input_time":1517792229000,"community_name":"社团","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","community_address":"社团社团","she_zhang":"社团","honoraward":"社团社团","community_scope":12}]
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
         * community_id : 35e440a0-1ff9-4c9b-abee-cea308717232
         * community_instruction : 大学社团是学生自愿组织的群众性团体，在校党委、团委的领导下，在学生会的指导下，组织开展健康、积极有益的课外文化、科技体育、艺术等活动，它组织开展我们同学们的各项活动，丰富了同学们的业余文化生活，培养学生的综合素质。它既不同于第一课堂的专业学习，又与自身的全面发展有着密切的联系。通过社团活动， 能拓展我们的视野，培养创新精神和实验能力，为我们实现自己的人生理想、从容的面对社会生活打下了坚实的基础。
         * input_userschool : 00101
         * contact_way : 123345657
         * input_time : 1516690509000
         * community_name : 心理社
         * input_user : 4edd30a8-20a8-4f02-9636-457ef84aa62c
         * community_address : 山东
         * she_zhang : 金馆长
         * pic_url : upload/CommunityPic/1515117050127.jpg,upload/CommunityPic/1515117050156.jpg
         * honoraward : 无数
         * community_scope : 100
         */

        private String community_id;
        private String community_instruction;
        private String input_userschool;
        private String contact_way;
        private long input_time;
        private String community_name;
        private String input_user;
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

        public String getInput_userschool() {
            return input_userschool;
        }

        public void setInput_userschool(String input_userschool) {
            this.input_userschool = input_userschool;
        }

        public String getContact_way() {
            return contact_way;
        }

        public void setContact_way(String contact_way) {
            this.contact_way = contact_way;
        }

        public long getInput_time() {
            return input_time;
        }

        public void setInput_time(long input_time) {
            this.input_time = input_time;
        }

        public String getCommunity_name() {
            return community_name;
        }

        public void setCommunity_name(String community_name) {
            this.community_name = community_name;
        }

        public String getInput_user() {
            return input_user;
        }

        public void setInput_user(String input_user) {
            this.input_user = input_user;
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
