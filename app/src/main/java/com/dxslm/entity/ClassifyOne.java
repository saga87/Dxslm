package com.dxslm.entity;

import java.io.Serializable;
import java.util.List;


public class ClassifyOne implements Serializable{


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * dept_order : 1
         * dept_parent_id : 0
         * dept_other :
         * dept_name : 山东
         * dept_id : 001
         */

        private int dept_order;
        private String dept_parent_id;
        private String dept_other;
        private String dept_name;
        private String dept_id;
        private boolean chick;

        public boolean isChick() {
            return chick;
        }

        public void setChick(boolean chick) {
            this.chick = chick;
        }

        public int getDept_order() {
            return dept_order;
        }

        public void setDept_order(int dept_order) {
            this.dept_order = dept_order;
        }

        public String getDept_parent_id() {
            return dept_parent_id;
        }

        public void setDept_parent_id(String dept_parent_id) {
            this.dept_parent_id = dept_parent_id;
        }

        public String getDept_other() {
            return dept_other;
        }

        public void setDept_other(String dept_other) {
            this.dept_other = dept_other;
        }

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
