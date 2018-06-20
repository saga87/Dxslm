package com.dxslm.model;

import android.util.Log;

import com.dxslm.entity.Success;
import com.dxslm.entity.community.CommunityDetails;
import com.dxslm.entity.community.CommunityList;
import com.dxslm.entity.community.ComunityList;
import com.dxslm.entity.forum.ForumCommentList;
import com.dxslm.entity.forum.ForumDetails;
import com.dxslm.entity.forum.ForumList;
import com.dxslm.entity.love.DzNum;
import com.dxslm.entity.love.LoveCommentList;
import com.dxslm.entity.love.LoveList;
import com.dxslm.entity.news.NewCommentList;
import com.dxslm.entity.news.NewDetails;
import com.dxslm.entity.news.NewsList;
import com.dxslm.entity.posts.PostCommentList;
import com.dxslm.entity.posts.PostDetails;
import com.dxslm.entity.posts.PostList;
import com.dxslm.entity.school.SchoolCommentList;
import com.dxslm.entity.school.SchoolDetails;
import com.dxslm.entity.school.SchoolList;
import com.dxslm.entity.student.StudentActivityDetails;
import com.dxslm.entity.student.StudentActivityList;
import com.dxslm.entity.student.StudentCommentList;
import com.dxslm.entity.student.StudentDetails;
import com.dxslm.entity.student.StudentList;
import com.dxslm.entity.student.StudentSponsorList;
import com.dxslm.entity.student.StudentSponsordetails;
import com.dxslm.okhttp.CommonOkHttpClient;
import com.dxslm.okhttp.gson.JsonGson;
import com.dxslm.okhttp.listener.DisposeDataHandle;
import com.dxslm.okhttp.listener.DisposeDataListener;
import com.dxslm.okhttp.request.CommonRequest;
import com.dxslm.okhttp.request.RequestParams;
import com.dxslm.util.UrlUtil;
import com.google.gson.Gson;

/**
 * Created by fxn on 2017/12/12.
 */

public class HeadModel {
    private static  HeadModel mInstance = null;

    public static HeadModel getInstance() {
        if (mInstance == null) {
            synchronized (HeadModel.class) {
                if (mInstance == null) {
                    mInstance = new HeadModel();
                }
            }
        }
        return mInstance;
    }

    /**热点list*/
    public void newsList(int page, int pagesize,String dept_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        String url = UrlUtil.URL+"wkrj/mobile/hotnews/m_hotnewslist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    NewsList user = JsonGson.getObject(str, NewsList.class);
                    back.succeed(user);
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

    /**热点详情*/
    public void newsDetails(String news_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("news_id",news_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/hotnews/m_hotnewsdetails";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    NewDetails user = JsonGson.getObject(str, NewDetails.class);
                    back.succeed(user);
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

    /**热点评论列表*/
    public void newsCommentList(String news_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("news_id",news_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/hotnews/m_newsreplylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    NewCommentList user = JsonGson.getObject(str, NewCommentList.class);
                    back.succeed(user);
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

    /**热点评论*/
    public void newsComment(String news_id ,String role_id, String user_id,  String hnr_content
            ,String hnr_beuser,String pic_url,String inputId, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("news_id",news_id);
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("hnr_content",hnr_content);
        params.put("hnr_beuser",hnr_beuser);
        params.put("pic_url",pic_url);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/hotnews/m_replyhotnews";
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
                back.error(reasonObj);
            }
        }));
    }
    public void newsCommentss(String news_id ,String role_id, String user_id,  String hnr_content,String hnr_beuser,String pic_url, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("news_id",news_id);
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("hnr_content",hnr_content);
        params.put("hnr_beuser",hnr_beuser);
        params.put("pic_url",pic_url);
        String url = UrlUtil.URL+"wkrj/mobile/hotnews/m_replyhotnews";
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
                back.error(reasonObj);
            }
        }));
    }
    /**删除新闻热点评论*/
    public void newsCommentDelete(String hnr_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("hnr_id",hnr_id);
        params.put("role_id",role_id);

        String url = UrlUtil.URL+"wkrj/mobile/hotnews/m_delreplynews";
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
                back.error(reasonObj);
            }
        }));
    }

    //删除表白墙评论
    public void commentDelete(String role_id ,String bbr_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("bbr_id",bbr_id);

        String url = UrlUtil.URL+"wkrj/mobile/bbinfo/m_delreplyinfo";
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
                back.error(reasonObj);
            }
        }));
    }



//删除大学生热点评论
    public void studentCommentDelete(String role_id ,String sar_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("sar_id",sar_id);

        String url = UrlUtil.URL+"wkrj/mobile/article/m_delreplyinfo";
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
                back.error(reasonObj);
            }
        }));
    }
    //删除身边校园头条的回复
    public void aroundSchoolCommentDelete(String role_id ,String crr_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("crr_id",crr_id);
        String url = UrlUtil.URL+"wkrj/mobile/around_school/m_delreplyinfo";
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
                back.error(reasonObj);
            }
        }));
    }
    //删除校园论坛评论
    public void forumlCommentDelete(String role_id ,String fr_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("fr_id",fr_id);
        String url = UrlUtil.URL+"wkrj/mobile/forum/m_delreplyinfo";
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
                back.error(reasonObj);
            }
        }));
    }
    //撒很难出高校圈评论
    public void interactCommentDelete(String role_id ,String pr_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("pr_id",pr_id);

        String url = UrlUtil.URL+"wkrj/mobile/interactivepost/m_delreplypost";
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
                back.error(reasonObj);
            }
        }));
    }
    //删除吃喝玩乐评论
    public void chwlCommentDelete(String role_id ,String ser_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("ser_id",ser_id);

        String url = UrlUtil.URL+"wkrj/mobile/seller/m_delreplyinfo";
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
                back.error(reasonObj);
            }
        }));
    }
    /**高校互动list*/
    public void postList(int page, int pagesize,String dept_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        String url = UrlUtil.URL+"wkrj/mobile/interactivepost/m_postlist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    PostList user = JsonGson.getObject(str, PostList.class);
                    back.succeed(user);
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
    /**高校互动详情*/
    public void psotDetails(String role_id ,String post_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("post_id",post_id);
        String url = UrlUtil.URL+"wkrj/mobile/interactivepost/m_postdetails";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    PostDetails user = JsonGson.getObject(str, PostDetails.class);
                    back.succeed(user);
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
    /**高校互动评论列表*/
    public void psotCommentList(String post_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("post_id",post_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/interactivepost/postreplylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    PostCommentList user = JsonGson.getObject(str, PostCommentList.class);
                    back.succeed(user);
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
    /**高校互动评论*/
    public void psotComment(String post_id ,String role_id, String user_id,  String pr_content,String pr_beuser,String pic_url,
                            String inputId,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("post_id",post_id);
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("pr_content",pr_content);
        params.put("pr_beuser",pr_beuser);
        params.put("pic_url",pic_url);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/interactivepost/m_replypost";
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
                back.error(reasonObj);
            }
        }));
    }

    /**身边校园list*/
    public void schoolList(int page, int pagesize, String dept_id, String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/around_school/m_aschoollist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    SchoolList user = JsonGson.getObject(str, SchoolList.class);
                    back.succeed(user);
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
    /**身边校园详情*/
    public void schooDetails(String role_id ,String info_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("info_id",info_id);
        String url = UrlUtil.URL+"wkrj/mobile/around_school/m_details";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    SchoolDetails user = JsonGson.getObject(str, SchoolDetails.class);
                    back.succeed(user);
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
    /**身边校园评论列表*/
    public void schoolCommentList(String info_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("info_id",info_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/around_school/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    SchoolCommentList user = JsonGson.getObject(str, SchoolCommentList.class);
                    back.succeed(user);
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
    /**身边校园评论*/
    public void schoolComment(String info_id ,String role_id, String user_id,  String crr_content,String crr_beuser,String pic_url,
                              String inputId,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("info_id",info_id);
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("crr_content",crr_content);
        params.put("crr_beuser",crr_beuser);
        params.put("pic_url",pic_url);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/around_school/m_replyinfo";
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
                back.error(reasonObj);
            }
        }));
    }
    /**大学生list*/
    public void studentList(int page, int pagesize, String dept_id, String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/article/m_articlelist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    StudentList user = JsonGson.getObject(str, StudentList.class);
                    back.succeed(user);
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
    /**大学生详情*/
    public void studentDetails(String role_id ,String article_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("article_id",article_id);
        String url = UrlUtil.URL+"wkrj/mobile/article/m_details";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    StudentDetails user = JsonGson.getObject(str, StudentDetails.class);
                    back.succeed(user);
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
    /**大学生评论列表*/
    public void studentCommentList(String article_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("article_id",article_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/article/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    StudentCommentList user = JsonGson.getObject(str, StudentCommentList.class);
                    back.succeed(user);
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
    /**大学生评论*/
    public void studentComment(String article_id ,String role_id, String user_id,  String sar_content,String sar_beuser,String pic_url,
                               String inputId,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("article_id",article_id);
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("sar_content",sar_content);
        params.put("sar_beuser",sar_beuser);
        params.put("pic_url",pic_url);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/article/m_replyinfo";
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
                back.error(reasonObj);
            }
        }));
    }

    /**社团list*/
    public void communityList(int page, int pagesize,String dept_id, String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/community/m_communitylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
//                    String str = (String) responseObj;
//                    CommunityList user = JsonGson.getObject(str, CommunityList.class);
//                    back.succeed(user);
                    String str = (String) responseObj;
                    ComunityList user = JsonGson.getObject(str, ComunityList.class);
                    back.succeed(user);
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
    /**社团详情*/
    public void communityDetails(String role_id ,String community_id , final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("community_id",community_id);
        String url = UrlUtil.URL+"wkrj/mobile/community/m_details";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    CommunityDetails user = JsonGson.getObject(str, CommunityDetails.class);
                    back.succeed(user);
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
    /**论坛list*/
    public void forumList(int page, int pagesize, String dept_id, String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/forum/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ForumList user = JsonGson.getObject(str, ForumList.class);
                    back.succeed(user);
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
    /**论坛详情*/
    public void forumDetails(String role_id ,String forum_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("forum_id",forum_id);
        String url = UrlUtil.URL+"wkrj/mobile/forum/m_details";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ForumDetails user = JsonGson.getObject(str, ForumDetails.class);
                    back.succeed(user);
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
    /**论坛评论列表*/
    public void forumCommentList(String forum_id  ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("forum_id",forum_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/forum/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ForumCommentList user = JsonGson.getObject(str, ForumCommentList.class);
                    back.succeed(user);
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
    /**论坛评论*/
    public void forumComment(String forum_id ,String role_id, String user_id,  String fr_content,String fr_beuser
            ,String pic_url, String inputId,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("forum_id",forum_id);
        params.put("role_id",role_id);
        params.put("user_id",user_id);
        params.put("fr_content",fr_content);
        params.put("fr_beuser",fr_beuser);
        params.put("pic_url",pic_url);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/forum/m_replyinfo";
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
                back.error(reasonObj);
            }
        }));
    }
    /**表白墙list*/
    public void loveList(int page, int pagesize, String user_id, String dept_id,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("user_id",user_id);
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/bbinfo/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    LoveList user = JsonGson.getObject(str, LoveList.class);
                    back.succeed(user);
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
    /**表白墙评论list*/
    public void loveCommentList(String bb_id,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("bb_id",bb_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/bbinfo/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    LoveCommentList user = JsonGson.getObject(str, LoveCommentList.class);
                    back.succeed(user);
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
    /**表白墙评论*/
    public void loveComment(String role_id,String bb_id,String bbr_content,String user_id,String bbr_user,String inputId, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("bb_id",bb_id);
        params.put("bbr_content",bbr_content);
        params.put("user_id",user_id);
        params.put("bbr_user",bbr_user);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/bbinfo/m_replyinfo";
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
                back.error(reasonObj);
            }
        }));
    }
    /**表白墙点赞*/
    public void loveDz(String role_id,String bb_id,String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("bb_id",bb_id);
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/bbinfo/m_dz";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                    String str = (String) responseObj;
                    Success user = JsonGson.getObject(str, Success.class);
                    back.succeed(user);
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj);
            }
        }));
    }
    /**表白墙点赞数量*/
    public void loveDzNum(String role_id,String bb_id,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("bb_id",bb_id);
        String url = UrlUtil.URL+"wkrj/mobile/bbinfo/m_dzs";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                String str = (String) responseObj;
                DzNum user = JsonGson.getObject(str, DzNum.class);
                back.succeed(user);
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error(reasonObj);
            }
        }));
    }
    /**学生会 活动list*/
    public void activityList(int page, int pagesize, String dept_id, String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/activity/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    StudentActivityList user = JsonGson.getObject(str, StudentActivityList.class);
                    back.succeed(user);
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
    /**学生会 活动详情*/
    public void activityDetails(String role_id ,String activity_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("activity_id",activity_id);
        String url = UrlUtil.URL+"wkrj/mobile/activity/m_details";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    StudentActivityDetails user = JsonGson.getObject(str, StudentActivityDetails.class);
                    back.succeed(user);
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
    /**学生会 赞助list*/
    public void sponsorList(int page, int pagesize, String dept_id, String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/support/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    StudentSponsorList user = JsonGson.getObject(str, StudentSponsorList.class);
                    back.succeed(user);
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
    /**学生会 赞助详情*/
    public void sponsorDetails(String role_id ,String support_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("support_id",support_id);
        String url = UrlUtil.URL+"wkrj/mobile/support/m_details";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    StudentSponsordetails user = JsonGson.getObject(str, StudentSponsordetails.class);
                    back.succeed(user);
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
}
