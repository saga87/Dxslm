package com.dxslm.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.view.activity.StudentUnionDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fxn on 2017/12/11.
 */

public class Util {
    /**
     * 检查网络是否连通
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        // 创建并初始化连接对象
        ConnectivityManager connMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 判断初始化是否成功并作出相应处理
        if (connMan != null) {
            // 调用getActiveNetworkInfo方法创建对象,如果不为空则表明网络连通，否则没连通
            NetworkInfo info = connMan.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

    /**显示Toast*/
    public static void showToast(String str) {
        if (str == null) {
            return;
        }
        Toast toast = Toast.makeText(MyApplication.getInstance(), str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**关闭软键盘*/
    public static void closeFocus(Context context) {
        InputMethodManager inputMgr = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMgr.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 游览图片
     *  list 图片路径
     *  activity 所在的activity
     *  position 所在的第几张图片
     */
    public static void showPhotos(List list,Activity activity,int position) {
        //使用方式
        PictureConfig config = new PictureConfig.Builder()
                .setListData((ArrayList<String>) list)	//图片数据List<String> list
                .setPosition(position)	//图片下标（从第position张图片开始浏览）
                .setDownloadPath("pictureviewer")	//图片下载文件夹地址
                .setIsShowNumber(true)//是否显示数字下标
                .needDownload(true)	//是否支持图片下载
                .setPlacrHolder(R.drawable.error)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                .build();
        ImagePagerActivity.startActivity(activity, config);
    }


    private static final int MIN_DELAY_TIME = 2000;  // 两次点击间隔不能少于2000ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

}
