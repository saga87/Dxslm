package com.dxslm.model;

import android.util.ArrayMap;
import android.util.Log;

import com.dxslm.entity.PhotoSuccess;
import com.dxslm.entity.Success;
import com.dxslm.entity.collect.CollectList;
import com.dxslm.entity.issue.IssueDetails;
import com.dxslm.entity.issue.IssueList;
import com.dxslm.entity.person.PersonBackground;
import com.dxslm.entity.person.Pinglun;
import com.dxslm.entity.user.User;
import com.dxslm.okhttp.CommonOkHttpClient;
import com.dxslm.okhttp.gson.JsonGson;
import com.dxslm.okhttp.listener.DisposeDataHandle;
import com.dxslm.okhttp.listener.DisposeDataListener;
import com.dxslm.okhttp.request.CommonRequest;
import com.dxslm.okhttp.request.RequestParams;
import com.dxslm.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyModel {
    private static MyModel mInstance = null;

    public static MyModel getInstance() {
        if (mInstance == null) {
            synchronized (MyModel.class) {
                if (mInstance == null) {
                    mInstance = new MyModel();
                }
            }
        }
        return mInstance;
    }


//    private Map<String,List<String>> userpics = new HashMap<>();



    /**查看个人信息*/
    public void getMessage(String role_id, String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/m_persoalinfo";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    User user = JsonGson.getObject(str, User.class);
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
    public void getPerson(String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/getPersonInfo";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    User user = JsonGson.getObject(str, User.class);
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
    /**修改个人信息*/
    public void alterMessage(String role_id, String user_id,String user_tel,String user_realname,String user_email, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("user_tel",user_tel);
        params.put("user_realname",user_realname);
        params.put("user_email",user_email);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/m_updateinfo";
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

    /**上传头像个人信息*/
    public void setIcon(String user_id ,List<String> file, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/upload_headpic";
        CommonOkHttpClient.uploadFile(CommonRequest.CommonRequest(url,file,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    PhotoSuccess user = JsonGson.getObject(str, PhotoSuccess.class);
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
    public void personBackground(String user_id,List<String> file, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/uploadBackgroundPic";
        CommonOkHttpClient.uploadFile(CommonRequest.CommonRequest(url,file,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    PersonBackground user = JsonGson.getObject(str, PersonBackground.class);
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
    /**上传评论图片*/
    public void personIcon(List<String> file, final ICallBack back){
        RequestParams params = new RequestParams();
        String url = UrlUtil.URL+"wkrj/mobile/replypic/uploadpic";
        CommonOkHttpClient.uploadFile(CommonRequest.CommonRequest(url,file,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String htmlStr = (String) responseObj;
                    Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
                    ArrayList<JsonObject> jsonObjects = new Gson().fromJson(htmlStr, type);
                    List<Pinglun> arrayList = new ArrayList<>();
                    for (JsonObject jsonObject : jsonObjects){
                        arrayList.add(new Gson().fromJson(jsonObject, Pinglun.class));
                    }
                    back.succeed(arrayList);
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
    /**获取发布信息list*/
    public void issueList(int page,int pagesize,String role_id, String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/release/releaselist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    IssueList user = JsonGson.getObject(str, IssueList.class);
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
    /**删除发布信息*/
    public void issueDelete(String role_id, String type,String id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("type",type);
        params.put("id",id);
        String url = UrlUtil.URL+"wkrj/mobile/release/delrelease";
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

    /**获取发布信息详情*/
    public void issueDetails(String role_id, String user_id,String id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("id",id);
        String url = UrlUtil.URL+"wkrj/mobile/release/getdetails";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    IssueDetails user = JsonGson.getObject(str, IssueDetails.class);
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


    public void picList(String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
//        params.put("page",page+"");
//        params.put("pagesize",pagesize+"");
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/picGroupList";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    JSONArray jsonArray = new JSONArray(str);
                    List<String> urls = new ArrayList<>();
                    JSONObject jsonObject;
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        jsonObject = jsonArray.getJSONObject(i);
                        urls.add(jsonObject.get("pic_url").toString());
                    }
                    back.succeed(urls);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj);
            }
        }));
    }




    /**
     * 删除相册图片
     * @param pic_url
     * @param back
     */
    public void delPics(String pic_url,final ICallBack back){
        RequestParams params = new RequestParams();
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/delPicGroup";
        params.put("pic_url",pic_url);
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




    /**
     * 上传相册
     * @param user_id
     * @param file
     * @param back
     */
    public void uploadPics(String user_id, List<String> file,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/personal_info/uploadPicGroup";
        CommonOkHttpClient.uploadFile(CommonRequest.CommonRequest(url,file,params),new DisposeDataHandle(new DisposeDataListener() {
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




    /**上传发布信息*/
    public void setIssue(String user_id ,String dept_id ,String type ,String content ,String address ,String title,
                         String buyorsell , List<String> file, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_id",user_id);
        params.put("dept_id",dept_id);
        params.put("type",type);
        params.put("content",content);
        params.put("address",address);
        params.put("title",title);
        params.put("buyorsell",buyorsell);
        String url = UrlUtil.URL+"wkrj/mobile/release/releaseinfo";
        CommonOkHttpClient.uploadFile(CommonRequest.CommonRequest(url,file,params),new DisposeDataHandle(new DisposeDataListener() {
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
    /**获取收藏list*/
    public void collectList(int page,int pagesize,String role_id, String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/collection/m_collectlist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    CollectList user = JsonGson.getObject(str, CollectList.class);
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
    /**删除收藏*/
    public void deleteCollect(String role_id, String collect_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("collect_id",collect_id);
        String url = UrlUtil.URL+"wkrj/mobile/collection/m_delmycollection";
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
    /**上传收藏*/
    public void setCollect(String user_id ,String role_id ,String ct_id ,String collect_content ,String collert_type , final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("user_id",user_id);
        params.put("role_id",role_id);
        params.put("ct_id",ct_id);
        params.put("collect_content",collect_content);
        params.put("collect_type",collert_type);
        String url = UrlUtil.URL+"wkrj/mobile/collection/m_mycollection";
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
}
