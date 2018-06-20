package com.dxslm.model;

import com.dxslm.entity.Success;
import com.dxslm.entity.chwl.ChwlCommentList;
import com.dxslm.entity.chwl.ChwlDetails;
import com.dxslm.entity.chwl.ChwlGoods;
import com.dxslm.entity.chwl.ChwlList;
import com.dxslm.entity.lost.LoseCommentList;
import com.dxslm.entity.lost.LoseList;
import com.dxslm.entity.love.DzNum;
import com.dxslm.entity.otherwork.OtherWorkCommentList;
import com.dxslm.entity.otherwork.OtherWorkList;
import com.dxslm.entity.used.UsedCommentList;
import com.dxslm.entity.used.UsedList;
import com.dxslm.okhttp.CommonOkHttpClient;
import com.dxslm.okhttp.gson.JsonGson;
import com.dxslm.okhttp.listener.DisposeDataHandle;
import com.dxslm.okhttp.listener.DisposeDataListener;
import com.dxslm.okhttp.request.CommonRequest;
import com.dxslm.okhttp.request.RequestParams;
import com.dxslm.util.UrlUtil;


public class ActivityModel {
    private static  ActivityModel mInstance = null;

    public static ActivityModel getInstance() {
        if (mInstance == null) {
            synchronized (HeadModel.class) {
                if (mInstance == null) {
                    mInstance = new ActivityModel();
                }
            }
        }
        return mInstance;
    }
    /**二手信息list*/
    public void usedList(int page, int pagesize, String user_id, String dept_id,String role_id,String buyorsell, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("user_id",user_id);
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        params.put("buyorsell",buyorsell);
        String url = UrlUtil.URL+"wkrj/mobile/shinfo/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    UsedList user = JsonGson.getObject(str, UsedList.class);
                    back.succeed(user);
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
    /**二手信息评论list*/
    public void usedCommentList(String shi_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("shi_id",shi_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/shinfo/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    UsedCommentList user = JsonGson.getObject(str, UsedCommentList.class);
                    back.succeed(user);
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
    /**二手信息评论*/
    public void usedComment(String role_id,String shi_id,String shr_content,String user_id,String shr_user,
                            String inputId,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("shi_id",shi_id);
        params.put("shr_content",shr_content);
        params.put("user_id",user_id);
        params.put("shr_user",shr_user);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/shinfo/m_replyinfo";
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
                back.error((String) reasonObj);
            }
        }));
    }
    /**二手信息点赞*/
    public void usedDz(String role_id,String shi_id,String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("shi_id",shi_id);
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/shinfo/m_dz";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                String str = (String) responseObj;
                Success user = JsonGson.getObject(str, Success.class);
                back.succeed(user);
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error((String) reasonObj);
            }
        }));
    }
    /**二手信息点赞数量*/
    public void usedDzNum(String role_id,String shi_id,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("shi_id",shi_id);
        String url = UrlUtil.URL+"wkrj/mobile/shinfo/m_dzs";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                String str = (String) responseObj;
                DzNum user = JsonGson.getObject(str, DzNum.class);
                back.succeed(user);
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error((String) reasonObj);
            }
        }));
    }


    /**失物招领list*/
    public void lostList(int page, int pagesize, String user_id, String dept_id,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("user_id",user_id);
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/swzl/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    LoseList user = JsonGson.getObject(str, LoseList.class);
                    back.succeed(user);
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
    /**失物招领评论list*/
    public void lostCommentList(String swzl_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("swzl_id",swzl_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/swzl/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    LoseCommentList user = JsonGson.getObject(str, LoseCommentList.class);
                    back.succeed(user);
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
    /**失物招领评论*/
    public void lostComment(String role_id,String swzl_id,String swzlr_content,String user_id,String swzlr_user,String inputId, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("swzl_id",swzl_id);
        params.put("swzlr_content",swzlr_content);
        params.put("user_id",user_id);
        params.put("swzlr_user",swzlr_user);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/swzl/m_replyinfo";
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
                back.error((String) reasonObj);
            }
        }));
    }
    /**失物招领点赞*/
    public void lostDz(String role_id,String swzl_id,String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("swzl_id",swzl_id);
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/swzl/m_dz";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                String str = (String) responseObj;
                Success user = JsonGson.getObject(str, Success.class);
                back.succeed(user);
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error((String) reasonObj);
            }
        }));
    }
    /**失物招领点赞数量*/
    public void lostDzNum(String role_id,String swzl_id,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("swzl_id",swzl_id);
        String url = UrlUtil.URL+"wkrj/mobile/swzl/m_dzs";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                String str = (String) responseObj;
                DzNum user = JsonGson.getObject(str, DzNum.class);
                back.succeed(user);
            }
            @Override
            public void onFailure(Object reasonObj) {
                back.error((String) reasonObj);
            }
        }));
    }
    /**兼职list*/
    public void otherWorkList(int page, int pagesize, String user_id, String dept_id,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("user_id",user_id);
        params.put("dept_id",dept_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/ptj/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    OtherWorkList user = JsonGson.getObject(str, OtherWorkList.class);
                    back.succeed(user);
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
    /**兼职评论list*/
    public void otherWorkCommentList(String ptj_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("ptj_id",ptj_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/ptj/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    OtherWorkCommentList user = JsonGson.getObject(str, OtherWorkCommentList.class);
                    back.succeed(user);
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
    /**兼职评论*/
    public void otherWorkComment(String role_id,String swzl_id,String swzlr_content,String user_id,String swzlr_user,String inputId, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("ptj_id",swzl_id);
        params.put("ptr_content",swzlr_content);
        params.put("user_id",user_id);
        params.put("ptr_user",swzlr_user);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/ptj/m_replyinfo";
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
                back.error((String) reasonObj);
            }
        }));
    }
    /**兼职点赞*/
    public void otherWorkDz(String role_id,String ptj_id,String user_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("ptj_id",ptj_id);
        params.put("user_id",user_id);
        String url = UrlUtil.URL+"wkrj/mobile/ptj/m_dz";
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
                back.error((String) reasonObj);
            }
        }));
    }
    /**兼职点赞数量*/
    public void otherWorkDzNum(String role_id,String ptj_id,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("ptj_id",ptj_id);
        String url = UrlUtil.URL+"wkrj/mobile/ptj/m_dzs";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    DzNum user = JsonGson.getObject(str, DzNum.class);
                    back.succeed(user);
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
    /**吃喝玩乐list*/
    public void chwlList(int page ,int pagesize,String dept_id,String user_id, String type,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        params.put("pagesize",pagesize+"");
        params.put("dept_id",dept_id);
        params.put("user_id",user_id);
        params.put("type",type);
        String url = UrlUtil.URL+"wkrj/mobile/seller/m_list";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ChwlList user = JsonGson.getObject(str, ChwlList.class);
                    back.succeed(user);
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
    /**吃喝玩乐详情*/
    public void chwlDetails(String role_id,String seller_id,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("seller_id",seller_id);
        String url = UrlUtil.URL+"wkrj/mobile/seller/m_details";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ChwlDetails user = JsonGson.getObject(str, ChwlDetails.class);
                    back.succeed(user);
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
    /**吃喝玩乐菜品详情*/
    public void chwlGoodsDetails(String role_id,String seller_id,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("seller_id",seller_id);
        String url = UrlUtil.URL+"wkrj/mobile/seller/m_menu";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ChwlGoods user = JsonGson.getObject(str, ChwlGoods.class);
                    back.succeed(user);
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
    /**吃喝玩乐评论list*/
    public void chwlCommentList(String seller_id ,String role_id, final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("seller_id",seller_id);
        params.put("role_id",role_id);
        String url = UrlUtil.URL+"wkrj/mobile/seller/m_replylist";
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    String str = (String) responseObj;
                    ChwlCommentList user = JsonGson.getObject(str, ChwlCommentList.class);
                    back.succeed(user);
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
    /**吃喝玩乐评论*/
    public void chwlComment(String role_id,String seller_id,String ser_content,String user_id,String ser_beuser,String pic_url,
                            String inputId,final ICallBack back){
        RequestParams params = new RequestParams();
        params.put("role_id",role_id);
        params.put("seller_id",seller_id);
        params.put("ser_content",ser_content);
        params.put("user_id",user_id);
        params.put("ser_beuser",ser_beuser);
        params.put("pic_url",pic_url);
        params.put("input_user",inputId);
//        params.put("token",OtherModel.getInstance().getToken());
        String url = UrlUtil.URL+"wkrj/mobile/seller/m_replyinfo";
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
                back.error((String) reasonObj);
            }
        }));
    }
}
