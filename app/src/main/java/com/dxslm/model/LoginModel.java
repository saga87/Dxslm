package com.dxslm.model;


import com.dxslm.entity.ClassifyOne;
import com.dxslm.entity.UpdateApp;
import com.dxslm.entity.user.UserMessage;
import com.dxslm.okhttp.CommonOkHttpClient;
import com.dxslm.okhttp.gson.JsonGson;
import com.dxslm.okhttp.listener.DisposeDataHandle;
import com.dxslm.okhttp.listener.DisposeDataListener;
import com.dxslm.okhttp.request.CommonRequest;
import com.dxslm.okhttp.request.RequestParams;
import com.dxslm.util.UrlUtil;


public class LoginModel {
    private static  LoginModel mInstance = null;

    public static LoginModel getInstance() {
        if (mInstance == null) {
            synchronized (LoginModel.class) {
                if (mInstance == null) {
                    mInstance = new LoginModel();
                }
            }
        }
        return mInstance;
    }
    /**登陆*/
    public void login(String username, String password,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("username",username);
        params.put("password",password);
//        String token = OtherModel.getInstance().getToken();
//        params.put("xgToken",token);
        String url = UrlUtil.URL+"wkrjsystem/wkrjlogin/checkLogin_mobile";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    UserMessage user = JsonGson.getObject(str.toString(), UserMessage.class);
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
    /**学校名获取*/
    public void schoolClassifyOne(String dept_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("dept_id",dept_id);
        String url = UrlUtil.URL+"wkrj/mobile/register/find_school";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ClassifyOne user = JsonGson.getObject(str.toString(), ClassifyOne.class);
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
    /**版本升级*/
    public void updateApp( final ICallBack back){
        RequestParams params = new RequestParams();
        String url = UrlUtil.urlUpateApp;
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    UpdateApp user = JsonGson.getObject(str.toString(), UpdateApp.class);
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
