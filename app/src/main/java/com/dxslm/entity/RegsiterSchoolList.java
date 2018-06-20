package com.dxslm.entity;

import java.util.List;


public class RegsiterSchoolList {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * dept_name : 潍坊科技学院
         * dept_id : 001
         */

        private String dept_name;
        private String dept_id;

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }
    }
}
