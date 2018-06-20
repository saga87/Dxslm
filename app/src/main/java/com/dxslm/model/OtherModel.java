package com.dxslm.model;

import android.util.Log;

import com.dxslm.MyApplication;
import com.dxslm.entity.school.SchoolPics;
import com.dxslm.entity.user.PersonMessage;
import com.dxslm.okhttp.CommonOkHttpClient;
import com.dxslm.okhttp.gson.JsonGson;
import com.dxslm.okhttp.listener.DisposeDataHandle;
import com.dxslm.okhttp.listener.DisposeDataListener;
import com.dxslm.okhttp.request.CommonRequest;
import com.dxslm.okhttp.request.RequestParams;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;

/**
 * Created by Administrator on 2018/3/22.
 */

public class OtherModel {

    private boolean isHuawei = false;

    private String token = "";

    public boolean isHuawei() {
        return isHuawei;
    }

    public void setHuawei(boolean huawei) {
        isHuawei = huawei;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private static  OtherModel mInstance = null;

    public static OtherModel getInstance() {
        if (mInstance == null) {
            synchronized (OtherModel.class) {
                if (mInstance == null) {
                    mInstance = new OtherModel();
                }
            }
        }
        return mInstance;
    }

    public String getUsername(){
        String username;
        SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance()
                , "login");
        username = (String) helper.getSharedPreference("username", "");
        return username;
    }


    /**上传信鸽的token**/
    public void sendXGToken(String xgtoken){
        RequestParams params = new RequestParams();
        String username = getUsername();
        if (username==null||"".equals(username)){
            Log.e("OtherModel","=======current username is null");
            return;
        }

        if (xgtoken==null||"".equals(xgtoken)){
            Log.e("OtherModel","=======current xgtoken is null");
            return;
        }


        params.put("username",username);
        params.put("xgToken",xgtoken);
        String url = UrlUtil.URL+"wkrj/mobile/other/xgtoken";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                String str = (String) responseObj;
//                Log.e("OtherModel","==========token result:"+str);
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        }));
    }




    /**热点list*/
    public void getSchoolPic(String dept_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("dept_id",dept_id);
        String url = UrlUtil.URL+"wkrj/mobile/other/getSchoolPic";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                 //   NewsList user = JsonGson.getObject(str, NewsList.class);
                    SchoolPics schoolPics = JsonGson.getObject(str,SchoolPics.class);
                    back.succeed(schoolPics);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error((String) reasonObj);
            }
        }));
    }
    //个人信息
    public void getMessage(String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/getPersonInfo";
        //String url = UrlUtil.URL+"wkrj/mobile/personal_info/m_persoalinfo";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                   PersonMessage schoolPics = JsonGson.getObject(str,PersonMessage.class);;
                    back.succeed(schoolPics);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error((String) reasonObj);
            }
        }));
    }
}
