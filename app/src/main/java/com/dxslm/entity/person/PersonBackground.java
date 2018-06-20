package com.dxslm.entity.person;

/**
 * Created by Administrator on 2018/3/29.
 */

public class PersonBackground {


    /**
     * jsonStr : {"msg":"上传成功","success":true,"obj":"upload/headpic/20180329724901.jpg"}
     * msg : 上传成功
     * obj : upload/headpic/20180329724901.jpg
     * success : true
     */

    private String jsonStr;
    private String msg;
    private String obj;
    private boolean success;

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

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
