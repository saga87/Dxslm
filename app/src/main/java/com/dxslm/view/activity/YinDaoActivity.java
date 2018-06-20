package com.dxslm.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.user.UserMessage;
import com.dxslm.model.ICallBack;
import com.dxslm.model.LoginModel;
import com.dxslm.model.OtherModel;
import com.dxslm.util.DeviceUtil;
import com.dxslm.util.OSUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.view.BaseActivity;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;


public class YinDaoActivity extends BaseActivity {
    private UserMessage userMessage;
    private String username1;
    private String paw1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Intent intent = new Intent(YinDaoActivity.this,HeadActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case 2:
                    Intent intent2 = new Intent(YinDaoActivity.this,LoginActivity.class);
                    startActivity(intent2);
                    finish();
                    break;

                case 3:
                    Toast.makeText(YinDaoActivity.this,"token获取失败，请重新登陆",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(YinDaoActivity.this,LoginActivity.class);
                    startActivity(intent3);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherModel.getInstance().setHuawei(false);
//        Log.e("YinDaoActivity","========onCreate 当前机型是"+ DeviceUtil.getDeviceBrand());

        XGPushClickedResult click = XGPushManager.onActivityStarted(this);
        if (click != null) {
            //查看是不是全新打开的面板
//            Log.e("YinDaoActivity","=========click != null");
            if (isTaskRoot()) {
                return;
            }
            String customContent = click.getCustomContent();
            JSONObject object = JSON.parseObject(customContent);
            if (object!=null){
                String where = object.getString("where");
                String id = object.getString("id");
                gotoReportPage(where,id);
                finish();
                return;
            }
        }

//        Log.e("YinDaoActivity","=========click= null");

        setContentView(R.layout.activity_yin_dao);
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    autoLogin();
                }
            },1800);

    }


    private void gotoReportPage(String where, String id) {
//        Log.e("YinDaoActivity","=============where:"+where+",id="+id);
        switch (where){
            case "热点":
                Intent intentA = new Intent(YinDaoActivity.this,NewsDetailsActivity.class);
                intentA.putExtra("newsId",id);
                startActivity(intentA);
             break;
            case "大学生":
                Intent intentB = new Intent(YinDaoActivity.this,StudentDetailsActivity.class);
                intentB.putExtra("articleId",id);
                startActivity(intentB);
                break;
            case "校园头条":
                Intent intentC = new Intent(YinDaoActivity.this,SchoolDetailsActivity.class);
                intentC.putExtra("infoId",id);
                startActivity(intentC);
                break;
            case "本校论坛":
                Intent intentD = new Intent(YinDaoActivity.this,ForumDetailsActivity.class);
                intentD.putExtra("forumId",id);
                startActivity(intentD);
                break;
            case "高校圈":
                Intent intentE = new Intent(YinDaoActivity.this,PostDetailsActivity.class);
                intentE.putExtra("postId",id);
                startActivity(intentE);
                break;

            case "表白墙":
                Intent intentA1 = new Intent(YinDaoActivity.this,LoveActivity.class);
//                intentA1.putExtra("newsId",id);
                startActivity(intentA1);
                break;
            case "二手":
                Intent intentB1 = new Intent(YinDaoActivity.this,UsedActivity.class);
//                intentB1.putExtra("articleId",id);
                startActivity(intentB1);
                break;
            case "失物招领":
                Intent intentC1 = new Intent(YinDaoActivity.this,LoseActivity.class);
//                intentC1.putExtra("infoId",id);
                startActivity(intentC1);
                break;
            case "兼职":
                Intent intentD1 = new Intent(YinDaoActivity.this,OtherWorkActivity.class);
//                intentD1.putExtra("forumId",id);
                startActivity(intentD1);
                break;
            case "吃喝玩乐":
                Intent intentE1 = new Intent(YinDaoActivity.this,ChwlDetailsActivity.class);
                intentE1.putExtra("sellerId",id);
                startActivity(intentE1);
                break;

        }
    }


    private void autoLogin() {
        if (OtherModel.getInstance().isHuawei()){
            YinDaoActivity.this.finish();
            return;
        }
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "login");
        username1 = (String) helper.getSharedPreference("username", "");
        paw1 = (String) helper.getSharedPreference("paw", "");
        if (username1==null||"".equals(username1)||paw1==null||"".equals(paw1)){
            Message message = Message.obtain();
            message.what = 2;
            handler.sendMessage(message);
            return;
        }
//        Log.e("YindaoActivity","=============ostype:"+OSUtil.getRomType());

        LoginModel.getInstance().login(username1, paw1, new ICallBack() {
            public void succeed(Object object) {
                userMessage = (UserMessage) object;
                if(userMessage.isSuccess()){
                    if(userMessage!=null) {
                        SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
                        helper.put("userId", userMessage.getUser_id());
                        helper.put("deptId", userMessage.getDept_id());
                        helper.put("roleId", userMessage.getRole_id());
                        helper.put("school", userMessage.getUserschool());
                        helper.put("pic", userMessage.getThpic());
                        helper.put("bgpic",userMessage.getBgpic());
                        helper.put("schoolBadgePic", userMessage.getSchoolBadgePic());
                        helper.put("schoolSignPic", userMessage.getSchoolSignPic());
                        OtherModel.getInstance().sendXGToken(OtherModel.getInstance().getToken());
                        Message message = Message.obtain();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }else{
                    Message message = Message.obtain();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
            public void error(Object object) {
                Message message = Message.obtain();
                message.what = 2;
                handler.sendMessage(message);
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);// 必须要调用这句
//        Log.e("YinDaoActivity","=============onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
