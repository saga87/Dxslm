package com.dxslm.entity;

/**
 * Created by fxn on 2017/12/18.
 */

public class Success {
    /**
     * jsonStr : {"msg":"评论/回复成功","success":true}
     * msg : 评论/回复成功
     * success : true
     */

    private String jsonStr;
    private String msg;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
