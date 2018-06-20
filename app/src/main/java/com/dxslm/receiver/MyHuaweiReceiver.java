package com.dxslm.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.dxslm.model.OtherModel;
import com.dxslm.view.activity.ChwlDetailsActivity;
import com.dxslm.view.activity.ForumDetailsActivity;
import com.dxslm.view.activity.LoseActivity;
import com.dxslm.view.activity.LoveActivity;
import com.dxslm.view.activity.NewsDetailsActivity;
import com.dxslm.view.activity.OtherWorkActivity;
import com.dxslm.view.activity.PostDetailsActivity;
import com.dxslm.view.activity.SchoolDetailsActivity;
import com.dxslm.view.activity.StudentDetailsActivity;
import com.dxslm.view.activity.UsedActivity;
import com.huawei.hms.support.api.push.PushReceiver;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lp on 2018/4/11.
 */

public class MyHuaweiReceiver extends PushReceiver {
    @Override
    public void onEvent(Context context, Event arg1, Bundle arg2) {
        super.onEvent(context, arg1, arg2);

        if (arg2!=null&&arg1==Event.NOTIFICATION_OPENED){
            String pushMsg = arg2.getString(BOUND_KEY.pushMsgKey);
//            Log.e("MyHuaweiReceiver","$$$$=============pushMsg:"+pushMsg);
            try {
                JSONArray jsonArray = new JSONArray(pushMsg);
                org.json.JSONObject jObject = jsonArray.getJSONObject(0);
                org.json.JSONObject jObject1 = jsonArray.getJSONObject(1);
                if (jObject!=null){
//                    Log.e("MyReceiver","=====================jObject!=null");
                    String where = jObject1.getString("where");
                    String id = jObject.getString("id");
//                    Log.e("MyReceiver","=====================where:"+where+",,,id:"+id);
                    gotoReportPage(context,where,id);
                    OtherModel.getInstance().setHuawei(true);
                }
            } catch (JSONException e) {
                Log.e("MyReceiver","=====================JSONException");
//                e.printStackTrace();
            }

        }
        showToast("onEvent" + arg1 + " Bundle " + arg2 ,  context);

    }

    private void gotoReportPage(Context context,String where, String id) {
//        Log.e("YinDaoActivity","=============where:"+where+",id="+id);
        switch (where){
            case "热点":
                Intent intentA = new Intent(context,NewsDetailsActivity.class);
                intentA.putExtra("newsId",id);
                context.startActivity(intentA);
                break;
            case "大学生":
                Intent intentB = new Intent(context,StudentDetailsActivity.class);
                intentB.putExtra("articleId",id);
                context.startActivity(intentB);
                break;
            case "校园头条":
                Intent intentC = new Intent(context,SchoolDetailsActivity.class);
                intentC.putExtra("infoId",id);
                context.startActivity(intentC);
                break;
            case "本校论坛":
                Intent intentD = new Intent(context,ForumDetailsActivity.class);
                intentD.putExtra("forumId",id);
                context.startActivity(intentD);
                break;
            case "高校圈":
                Intent intentE = new Intent(context,PostDetailsActivity.class);
                intentE.putExtra("postId",id);
                context.startActivity(intentE);
                break;

            case "表白墙":
                Intent intentA1 = new Intent(context,LoveActivity.class);
//                intentA1.putExtra("newsId",id);
                context.startActivity(intentA1);
                break;
            case "二手":
                Intent intentB1 = new Intent(context,UsedActivity.class);
//                intentB1.putExtra("articleId",id);
                context.startActivity(intentB1);
                break;
            case "失物招领":
                Intent intentC1 = new Intent(context,LoseActivity.class);
//                intentC1.putExtra("infoId",id);
                context.startActivity(intentC1);
                break;
            case "兼职":
                Intent intentD1 = new Intent(context,OtherWorkActivity.class);
//                intentD1.putExtra("forumId",id);
                context.startActivity(intentD1);
                break;
            case "吃喝玩乐":
                Intent intentE1 = new Intent(context,ChwlDetailsActivity.class);
                intentE1.putExtra("sellerId",id);
                context.startActivity(intentE1);
                break;

        }
    }
    

    @Override
    public boolean onPushMsg(Context context, byte[] arg1, Bundle arg2) {

        showToast("onPushMsg" + new String(arg1) + " Bundle " + arg2 ,  context);
        return super.onPushMsg(context, arg1, arg2);
    }

    @Override
    public void onPushMsg(Context context, byte[] arg1, String arg2) {

        showToast("onPushMsg" + new String(arg1) + " arg2 " + arg2 ,  context);
        super.onPushMsg(context, arg1, arg2);
    }

    @Override
    public void onPushState(Context context, boolean arg1) {

        showToast("onPushState" + arg1,  context);
        super.onPushState(context, arg1);
    }

    @Override
    public void onToken(Context context, String arg1, Bundle arg2) {
        super.onToken(context, arg1, arg2);

        showToast(" onToken" + arg1 + "bundke " + arg2,  context);
    }

    @Override
    public void onToken(Context context, String arg1) {
        super.onToken(context, arg1);
        showToast(" onToken" + arg1 ,  context);
    }

    public  void showToast(final String toast, final Context context)
    {

//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//        }).start();

//        Log.e("MyHuaweiReceiver","============"+toast);

    }

    private void  writeToFile(String conrent) {
        String SDPATH = Environment.getExternalStorageDirectory() + "/huawei.txt";
        try {
            FileWriter fileWriter = new FileWriter(SDPATH, true);

            fileWriter.write(conrent+"\r\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
