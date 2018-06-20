package com.dxslm.entity;

import java.util.List;

/**
 * Created by fxn on 2017/12/18.
 */

public class PhotoSuccess {
    /**
     * jsonStr : {"msg":"上传成功","success":true,"obj":[{"pic_url":"upload/headpic/20171227558954.jpg"}]}
     * msg : 上传成功
     * obj : [{"pic_url":"upload/headpic/20171227558954.jpg"}]
     * success : true
     */

    private String jsonStr;
    private String msg;
    private boolean success;
    private List<ObjBean> obj;

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * pic_url : upload/headpic/20171227558954.jpg
         */

        private String pic_url;

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
