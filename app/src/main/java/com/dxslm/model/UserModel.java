package com.dxslm.model;

import com.dxslm.entity.RegsiterSchoolList;
import com.dxslm.entity.Success;
import com.dxslm.entity.user.User;
import com.dxslm.entity.user.YzmCode;
import com.dxslm.okhttp.CommonOkHttpClient;
import com.dxslm.okhttp.gson.JsonGson;
import com.dxslm.okhttp.listener.DisposeDataHandle;
import com.dxslm.okhttp.listener.DisposeDataListener;
import com.dxslm.okhttp.request.CommonRequest;
import com.dxslm.okhttp.request.RequestParams;
import com.dxslm.util.UrlUtil;


public class UserModel {
    private static UserModel mInstance = null;

    public static UserModel getInstance() {
        if (mInstance == null) {
            synchronized (UserModel.class) {
                if (mInstance == null) {
                    mInstance = new UserModel();
                }
            }
        }
        return mInstance;
    }
    /**注册用户*/
    public void register(String user_name, String user_password,String dept_id,String user_email, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_name",user_name);
        params.put("user_password",user_password);
        params.put("dept_id",dept_id);
        params.put("user_email",user_email);
        String url = UrlUtil.URL+"wkrj/mobile/register/mail_adduser";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    Success user = JsonGson.getObject(str, Success.class);
                    back.succeed(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj.toString());
            }
        }));
    }
    /**学校列表*/
    public void schoolList(final ICallBack back){
        RequestParams params = new RequestParams();
        String url = UrlUtil.URL+"wkrj/mobile/register/find_school";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    RegsiterSchoolList user = JsonGson.getObject(str, RegsiterSchoolList.class);
                    back.succeed(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj.toString());
            }
        }));
    }
    /**注册邮箱验证码*/
    public void emailYzm(String user_email,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_email",user_email);
        String url = UrlUtil.URL+"wkrj/mobile/register/email_register";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    YzmCode user = JsonGson.getObject(str, YzmCode.class);
                    back.succeed(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj.toString());
            }
        }));
    }
    /**邮箱密码找回*/
    public void emailSearchPaw(String user_email,String user_password,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_email",user_email);
        params.put("user_password",user_password);
        String url = UrlUtil.URL+"wkrj/mobile/jsspr/mail_jsspr";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    RegsiterSchoolList user = JsonGson.getObject(str, RegsiterSchoolList.class);
                    back.succeed(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj.toString());
            }
        }));
    }
    /**找回密码邮箱验证码*/
    public void emailPawYzm(String user_email,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_email",user_email);
        String url = UrlUtil.URL+"wkrj/mobile/jsspr/email_code";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    YzmCode user = JsonGson.getObject(str, YzmCode.class);
                    back.succeed(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj.toString());
            }
        }));
    }

}
