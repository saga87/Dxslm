package com.dxslm.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.dxslm.entity.XGNotification;
import com.dxslm.model.OtherModel;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lp on 2018/4/8.
 */

public class MyXGReceiver extends XGPushBaseReceiver {
    public static final String LogTag = "MyXGReceiver";
//    private Intent intent = new Intent("");
    private void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult xgPushRegisterResult) {
        if (context == null || xgPushRegisterResult == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = xgPushRegisterResult + "注册成功";
            // 在这里拿token
            String token = xgPushRegisterResult.getToken();
//            Log.e(LogTag, "============================token="+token);
//            OtherModel.getInstance().sendXGToken(token);
//            OtherModel.getInstance().setToken(token);
        }
        else {
            text = xgPushRegisterResult + "注册失败，错误码：" + errorCode;
        }
        Log.e(LogTag, "text="+text);

    }

    @Override
    public void onUnregisterResult(Context context, int errorCode) {
//        Log.e(LogTag, "==========================onUnregisterResult..");
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
//            Log.e(LogTag, "==========================onUnregisterResult success");
        }
    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {

    }

    //点击不同类型通知，进入不同详情
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
        Log.i("MyXGReceiver","=================onNotifactionClickedResult");
        if (context == null || xgPushClickedResult == null) {
            Log.e("MyXGReceiver","=================onNotifactionClickedResult参数为空");
            return;
        }
        String text = "";
        if (xgPushClickedResult.getActionType() ==
                XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
            // 通知在通知栏被点击啦。。。。。
            // APP自己处理点击的相关动作
            // 这个动作可以在activity的onResume也能监听，请看第3点相关内容
            text = "通知被打开 :" + xgPushClickedResult;
//            Log.e("MyXGReceiver", "text=" + text);
//            Log.e("MyXGReceiver", "content=" + xgPushClickedResult.getContent());
//            Log.e("MyXGReceiver", "activityName=" + xgPushClickedResult.getActivityName());
            // 获取自定义key-value，我们的app主要的根据这块的内容进行控制的，所以主要处理这块的代码
            String customContent = xgPushClickedResult.getCustomContent();
            if (customContent != null && !"".equals(customContent)) {
//                Log.e("MyXGReceiver", "customContent=" + customContent);
//                PushResult pushResult = Json.get()
//                        .toObject(customContent,
//                                PushResult.class);
//                switch (pushResult.getIndex()) {
//                    //......你的业务处理代码default:
//                    break;
//                }
            }


        }else if (xgPushClickedResult.getActionType() ==
                XGPushClickedResult.NOTIFACTION_DELETED_TYPE){
            Log.e("MyXGReceiver", "关掉了通知");
        }

        }

    // 收到通知后的操作
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
//        if (context == null || xgPushShowedResult == null) {
//            return;
//        }
//        XGNotification notific = new XGNotification();
//        notific.setMsg_id(xgPushShowedResult.getMsgId());
//        notific.setTitle(xgPushShowedResult.getTitle());
//        notific.setContent(xgPushShowedResult.getContent());
//        // notificationActionType==1为Activity，2为url，3为intent
//        notific.setNotificationActionType(xgPushShowedResult
//                .getNotificationActionType());
//        //Activity,url,intent都可以通过getActivity()获得
//        notific.setActivity(xgPushShowedResult.getActivity());
//        notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(Calendar.getInstance().getTime()));
////        context.sendBroadcast(intent);
//        show(context, "您有1条新消息, " + "通知被展示 ， " + xgPushShowedResult.toString());
    }
}
