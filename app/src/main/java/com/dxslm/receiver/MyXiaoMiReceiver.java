package com.dxslm.receiver;

import android.content.Context;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * Created by lp on 2018/4/11.
 */

public class MyXiaoMiReceiver extends PushMessageReceiver {

    private static final String TAG = "MyXiaoMiReceiver";

    @Override
    //需要MIUI7以上
    public void onNotificationMessageArrived(Context context, MiPushMessage msg) {
//        super.onNotificationMessageArrived(context,msg);
        String content = msg.getContent();
//        Log.e(TAG,"=============onNotificationMessageArrived:"+msg);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage msg) {
//        super.onNotificationMessageClicked(context,msg);
        String content = msg.getContent();
//        Log.e(TAG,"=============onNotificationMessageClicked:"+msg);
    }



}
