package com.dxslm.entity.user;


public class UserMessage {

    /**
     * bgpic : upload/headpic/20180330376146.jpg
     * msg : 成功
     * userschool : 潍坊科技学院
     * schoolSignPic : upload/SchoolPic/1521599275653.jpg,
     * role_id : 5df9f232-d2bc-4d52-83b1-eaed8a5b05e0,
     * user_id : a58d19ce-1ebb-42ae-ad5c-ec3a207e82ae
     * thpic : upload/headpic/20180329269529.jpg
     * success : true
     * schoolBadgePic : upload/SchoolPic/1521620077302.jpg,
     * dept_id : 00101
     */

    private String bgpic;
    private String msg;
    private String userschool;
    private String schoolSignPic;
    private String role_id;
    private String user_id;
    private String thpic;
    private boolean success;
    private String schoolBadgePic;
    private String dept_id;
    private String isGly = "0"; //是否管理员
    public String getIsGly() {
        return isGly;
    }
    public void setIsGly(String isGly) {
        this.isGly = isGly;
    }

    public String getBgpic() {
        return bgpic;
    }

    public void setBgpic(String bgpic) {
        this.bgpic = bgpic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserschool() {
        return userschool;
    }

    public void setUserschool(String userschool) {
        this.userschool = userschool;
    }

    public String getSchoolSignPic() {
        return schoolSignPic;
    }

    public void setSchoolSignPic(String schoolSignPic) {
        this.schoolSignPic = schoolSignPic;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getThpic() {
        return thpic;
    }

    public void setThpic(String thpic) {
        this.thpic = thpic;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSchoolBadgePic() {
        return schoolBadgePic;
    }

    public void setSchoolBadgePic(String schoolBadgePic) {
        this.schoolBadgePic = schoolBadgePic;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }
}
