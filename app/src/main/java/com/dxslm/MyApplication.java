package com.dxslm;

import android.app.Application;
import android.util.Log;

import com.dxslm.model.OtherModel;
import com.dxslm.share.ShareManager;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;


/**
 * Created by fxn on 2017/12/8.
 */

public class MyApplication extends Application {

    private static MyApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initShareSDK();


//        XGPushConfig.setMzPushAppId(this, APP_ID);
//        XGPushConfig.setMzPushAppKey(this, APP_KEY);
//
        XGPushConfig.setMiPushAppId(getApplicationContext(), "2882303761517766262");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "5971776610262");

        XGPushConfig.setHuaweiDebug(true);

        //打开第三方推送
        XGPushConfig.enableOtherPush(getApplicationContext(), true);


        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
//                Log.e("TPush", "====注册成功，设备token为：" + o);
                OtherModel.getInstance().setToken(o.toString());
                OtherModel.getInstance().sendXGToken(o.toString());
            }

            @Override
            public void onFail(Object o, int errorCode, String message) {
                Log.e("TPush", "注册失败，错误码：" + errorCode + ",错误信息：" + message);
            }
        });

//        XGPushManager.bindAccount(this, "Dxslm");
//        XGPushManager.setTag(this,"DxslmXG");
//        XGPushManager.registerPush(this);

    }

    public static MyApplication getInstance() {
        return mApplication;
    }

    public void initShareSDK() {
        ShareManager.initSDK(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        XGPushManager.unregisterPush(this);

    }
}
