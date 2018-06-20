package com.dxslm.view;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dxslm.R;
import com.dxslm.util.ActivityCollector;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.StatusBarUtil;



public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加该活动
        ActivityCollector.addActivity(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        float val = 1.0f;
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        String font = (String) helper.getSharedPreference("fontsize", "");
        if ("小".equals(font)){
            val = 0.9f;
        }else if("大".equals(font)){
            val = 1.2f;
        }else if("超大".equals(font)){
            val = 1.3f;
        }
        Resources resource = getResources();
        Configuration configuration = resource.getConfiguration();
        configuration.fontScale = val;// 设置字体的缩放比例
        resource.updateConfiguration(configuration, resource.getDisplayMetrics());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除该活动
        ActivityCollector.removeActivity(this);
    }

}
