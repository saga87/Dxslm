package com.dxslm.util;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.dxslm.MyApplication;
import com.dxslm.entity.ClassifyOne;


public class LocalBroadcastUtil {
    /**
     * 发送学校改名的局部广播
     */
    public static void sendBroadcast(String action,ClassifyOne.ListBean item){
        Intent intent = new Intent(action);
        intent.putExtra("item",item);
        LocalBroadcastManager.getInstance(MyApplication.getInstance()).sendBroadcast(intent);
    }
}
