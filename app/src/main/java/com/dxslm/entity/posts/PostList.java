package com.dxslm.entity.posts;

import java.util.List;


public class PostList {
    /**
     * count : 11
     * list : [{"post_title":"1231","input_userschool":"00101","input_time":1521629586000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"1313142142452353531251","post_id":"5023c479-d81f-4b1f-acbb-6e883b68606e","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":1,"is_release":"","pic_url":"upload/InteractivepostPic/20180321967904.jpg","add_time":"2018-03-21","release_time":1521629586000},{"post_title":"1231","input_userschool":"00101","input_time":1521629570000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"1313142142452353531251","post_id":"20052285-ab5b-4f8e-aa8c-a8524799ece0","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":2,"is_release":"","pic_url":"upload/InteractivepostPic/20180321920990.jpg","add_time":"2018-03-21","release_time":1521629570000},{"post_title":"放松的方式","input_userschool":"00101","input_time":1521528074000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"当时发生地方","post_id":"18cb7947-0041-452e-b32a-d6d48d915979","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/20180320872649.jpg","add_time":"2018-03-20","release_time":1521528074000},{"post_title":"123","input_userschool":"00101","input_time":1521527817000,"user_name":"wk001","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"12","post_id":"29b7be2f-3331-42e2-8309-968c6d69d0f5","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","add_time":"2018-03-20","release_time":1521527817000},{"post_title":"","input_userschool":"00101","input_time":1521527221000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"测试下高校圈","post_id":"759d37a0-1eb4-43f9-b9fe-263f40f398c9","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/20180320240709.jpg","add_time":"2018-03-20","release_time":1521527221000},{"post_title":"31313134","input_userschool":"00101","input_time":1521527051000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"121","post_id":"41d48c4c-6e01-4754-8fa2-400a816d9c24","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/20180320649701.jpg","add_time":"2018-03-20","release_time":1521527051000},{"input_userschool":"00101","input_time":1521524445000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"213124","post_id":"42c56191-ff88-45e5-9ad5-d4a8e3e4155d","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/20180320631345.jpg","add_time":"2018-03-20","release_time":1521524445000},{"post_title":"","input_userschool":"00101","input_time":1521514394000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"高校","post_id":"448e5af0-6dd9-4ef0-af38-2080d29f2d42","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/20180320608061.jpg","add_time":"2018-03-20","release_time":1521514394000},{"post_title":"","input_userschool":"00101","input_time":1521513391000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"1234567","post_id":"40727fa5-f1e4-4343-88d6-699689a9fe2f","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/2018032056808.jpg","add_time":"2018-03-20","release_time":1521513391000},{"post_title":"与其自怨自艾，不如夯实勤奋","input_userschool":"00101","input_time":1515116513000,"user_name":"wk001","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"生活不如意十之八九，因此，我们总是不满意的多。人的满意分为两种，一种对自身的，一种是对外界的。外界我们控制不了，外界常常令我们促不及防的产生不愉快，如下班时今天没赶上末班车，早上上班时堵车，这些外界的不愉快不可避免的会影响我们自己的不满意，是不是我天生的命不好，倒霉事全占了，是不是天生就不行，为什么那么多的不如意？\n\n　　这时，人就会产生自怨自艾的情绪，说到这个自怨自艾，能够暂时的让自己处于自我的境界，隔离与外界的不愉快事实，产生一种悲愤的情结。简单的说就是，让倒霉的事实转换成内在的一种情绪，不考虑事实本身，而是让自己与情绪感受在一起。","post_id":"6fb28bac-38dc-4852-8e64-28056548603a","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":4,"is_release":"","pic_url":"upload/InteractivepostPic/1515116512672.jpg,upload/InteractivepostPic/1515116512613.jpg","add_time":"2018-01-05","release_time":1515116513000},{"post_title":"抱怨，是对自己无能的愤慨","input_userschool":"00101","input_time":1515116473000,"user_name":"wk001","input_user":"4edd30a8-20a8-4f02-9636-457ef84aa62c","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"生活就如同时间一样，对每一个人都是一样的。但是却因为人与人思想、思维、心态等不同便出现了不同的生活局面，有的人过得贫苦心酸，有的人过得衣食无忧，有的人过得锦衣玉食。\n\n　　面对如此落差的生活，自然就会心生埋怨或牢骚满腹。但是，我们可有进行过深层次的分析和思考，为什么别人可以过得很好而你却过不好？很多时候，我们就是太在意自己家境或父母为我们积累的财富或给予我们的物质基础太过于薄弱，把借口和理由全部推到了父母的身上，如果只是这样那还不为过，要是把自己不努力才导致自己生活不如别人或者当成是父母的罪过，那么这样就太不应该了。","post_id":"797a3109-7cdd-4713-ae9b-2794d8367590","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":3,"is_release":"","pic_url":"upload/InteractivepostPic/1515116472731.jpg,upload/InteractivepostPic/1515116472824.jpg","add_time":"2018-01-05","release_time":1515116473000}]
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
         * post_title : 1231
         * input_userschool : 00101
         * input_time : 1521629586000
         * user_name : admin
         * input_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
         * key_words :
         * dept_name : 潍坊科技学院
         * is_jinghua :
         * post_content : 1313142142452353531251
         * post_id : 5023c479-d81f-4b1f-acbb-6e883b68606e
         * is_zhiding :
         * release_user :
         * quan_zhong : 0
         * reply_num : 1
         * is_release :
         * pic_url : upload/InteractivepostPic/20180321967904.jpg
         * add_time : 2018-03-21
         * release_time : 1521629586000
         */

        private String post_title;
        private String input_userschool;
        private long input_time;
        private String user_name;
        private String input_user;
        private String key_words;
        private String dept_name;
        private String is_jinghua;
        private String post_content;
        private String post_id;
        private String is_zhiding;
        private String release_user;
        private int quan_zhong;
        private int reply_num;
        private String is_release;
        private String pic_url;
        private String add_time;
        private long release_time;
        private String headpic;

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }


        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public String getInput_userschool() {
            return input_userschool;
        }

        public void setInput_userschool(String input_userschool) {
            this.input_userschool = input_userschool;
        }

        public long getInput_time() {
            return input_time;
        }

        public void setInput_time(long input_time) {
            this.input_time = input_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getInput_user() {
            return input_user;
        }

        public void setInput_user(String input_user) {
            this.input_user = input_user;
        }

        public String getKey_words() {
            return key_words;
        }

        public void setKey_words(String key_words) {
            this.key_words = key_words;
        }

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getIs_jinghua() {
            return is_jinghua;
        }

        public void setIs_jinghua(String is_jinghua) {
            this.is_jinghua = is_jinghua;
        }

        public String getPost_content() {
            return post_content;
        }

        public void setPost_content(String post_content) {
            this.post_content = post_content;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getIs_zhiding() {
            return is_zhiding;
        }

        public void setIs_zhiding(String is_zhiding) {
            this.is_zhiding = is_zhiding;
        }

        public String getRelease_user() {
            return release_user;
        }

        public void setRelease_user(String release_user) {
            this.release_user = release_user;
        }

        public int getQuan_zhong() {
            return quan_zhong;
        }

        public void setQuan_zhong(int quan_zhong) {
            this.quan_zhong = quan_zhong;
        }

        public int getReply_num() {
            return reply_num;
        }

        public void setReply_num(int reply_num) {
            this.reply_num = reply_num;
        }

        public String getIs_release() {
            return is_release;
        }

        public void setIs_release(String is_release) {
            this.is_release = is_release;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public long getRelease_time() {
            return release_time;
        }

        public void setRelease_time(long release_time) {
            this.release_time = release_time;
        }
    }


    /**
     * count : 9
     * list : [{"post_title":"123","input_userschool":"001","input_time":1513059448000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"123","post_id":"bf8313fa-2f98-4153-97a9-8e4bad50f7d0","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/1513059394225.png","release_time":1513059448000},{"post_title":"不不不他吞吞吐吐","input_userschool":"001","input_time":1513064887000,"user_name":"admin","input_user":"a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae","key_words":"","dept_name":"潍坊科技学院","is_jinghua":"","post_content":"不不不他吞吞吐吐不不不他吞吞吐吐不不不他吞吞吐吐","post_id":"3f319bf4-9850-4be7-a106-7e9cb5fca887","is_zhiding":"","release_user":"","quan_zhong":0,"reply_num":0,"is_release":"","pic_url":"upload/InteractivepostPic/1513064885400.png,upload/InteractivepostPic/1513065016780.png,upload/InteractivepostPic/1513064887055.png,upload/InteractivepostPic/1513065023002.jpg,upload/InteractivepostPic/1513065015264.png,upload/InteractivepostPic/1513047585826.png,upload/InteractivepostPic/1513065019873.png,upload/InteractivepostPic/1513065026183.jpg","release_time":1513064887000}]
     */

//    private int count;
//    private List<ListBean> list;
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
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
//         * post_title : 123
//         * input_userschool : 001
//         * input_time : 1513059448000
//         * user_name : admin
//         * input_user : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
//         * key_words :
//         * dept_name : 潍坊科技学院
//         * is_jinghua :
//         * post_content : 123
//         * post_id : bf8313fa-2f98-4153-97a9-8e4bad50f7d0
//         * is_zhiding :
//         * release_user :
//         * quan_zhong : 0
//         * reply_num : 0
//         * is_release :
//         * pic_url : upload/InteractivepostPic/1513059394225.png
//         * release_time : 1513059448000
//         */
//
//        private String post_title;
//        private String input_userschool;
//        private long input_time;
//        private String user_name;
//        private String input_user;
//        private String key_words;
//        private String dept_name;
//        private String is_jinghua;
//        private String post_content;
//        private String post_id;
//        private String is_zhiding;
//        private String release_user;
//        private int quan_zhong;
//        private int reply_num;
//        private String is_release;
//        private String pic_url;
//        private long release_time;
//
//        public String getPost_title() {
//            return post_title;
//        }
//
//        public void setPost_title(String post_title) {
//            this.post_title = post_title;
//        }
//
//        public String getInput_userschool() {
//            return input_userschool;
//        }
//
//        public void setInput_userschool(String input_userschool) {
//            this.input_userschool = input_userschool;
//        }
//
//        public long getInput_time() {
//            return input_time;
//        }
//
//        public void setInput_time(long input_time) {
//            this.input_time = input_time;
//        }
//
//        public String getUser_name() {
//            return user_name;
//        }
//
//        public void setUser_name(String user_name) {
//            this.user_name = user_name;
//        }
//
//        public String getInput_user() {
//            return input_user;
//        }
//
//        public void setInput_user(String input_user) {
//            this.input_user = input_user;
//        }
//
//        public String getKey_words() {
//            return key_words;
//        }
//
//        public void setKey_words(String key_words) {
//            this.key_words = key_words;
//        }
//
//        public String getDept_name() {
//            return dept_name;
//        }
//
//        public void setDept_name(String dept_name) {
//            this.dept_name = dept_name;
//        }
//
//        public String getIs_jinghua() {
//            return is_jinghua;
//        }
//
//        public void setIs_jinghua(String is_jinghua) {
//            this.is_jinghua = is_jinghua;
//        }
//
//        public String getPost_content() {
//            return post_content;
//        }
//
//        public void setPost_content(String post_content) {
//            this.post_content = post_content;
//        }
//
//        public String getPost_id() {
//            return post_id;
//        }
//
//        public void setPost_id(String post_id) {
//            this.post_id = post_id;
//        }
//
//        public String getIs_zhiding() {
//            return is_zhiding;
//        }
//
//        public void setIs_zhiding(String is_zhiding) {
//            this.is_zhiding = is_zhiding;
//        }
//
//        public String getRelease_user() {
//            return release_user;
//        }
//
//        public void setRelease_user(String release_user) {
//            this.release_user = release_user;
//        }
//
//        public int getQuan_zhong() {
//            return quan_zhong;
//        }
//
//        public void setQuan_zhong(int quan_zhong) {
//            this.quan_zhong = quan_zhong;
//        }
//
//        public int getReply_num() {
//            return reply_num;
//        }
//
//        public void setReply_num(int reply_num) {
//            this.reply_num = reply_num;
//        }
//
//        public String getIs_release() {
//            return is_release;
//        }
//
//        public void setIs_release(String is_release) {
//            this.is_release = is_release;
//        }
//
//        public String getPic_url() {
//            return pic_url;
//        }
//
//        public void setPic_url(String pic_url) {
//            this.pic_url = pic_url;
//        }
//
//        public long getRelease_time() {
//            return release_time;
//        }
//
//        public void setRelease_time(long release_time) {
//            this.release_time = release_time;
//        }
//    }
}
