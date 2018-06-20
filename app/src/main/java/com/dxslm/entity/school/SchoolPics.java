package com.dxslm.entity.school;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class SchoolPics {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * schoolSignPic : upload/SchoolPic/1521599275653.jpg,
         * schoolBadgePic : upload/SchoolPic/1521620077302.jpg,
         */

        private String schoolSignPic;
        private String schoolBadgePic;

        public String getSchoolSignPic() {
            return schoolSignPic;
        }

        public void setSchoolSignPic(String schoolSignPic) {
            this.schoolSignPic = schoolSignPic;
        }

        public String getSchoolBadgePic() {
            return schoolBadgePic;
        }

        public void setSchoolBadgePic(String schoolBadgePic) {
            this.schoolBadgePic = schoolBadgePic;
        }
    }
}
