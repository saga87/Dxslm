package com.dxslm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dxslm.R;
import com.dxslm.adapter.PictureAdapter;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.ui.GridViewForScrollview;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
import com.dxslm.view.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoActivity extends BaseActivity {

    @Bind(R.id.xc_back)
    ImageView xcBack;
    @Bind(R.id.xc_add)
    Button xcAdd;
    @Bind(R.id.xc_view)
    GridViewForScrollview xcView;

    private String userId;
    private SharedPreferencesHelper helper;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Util.showToast("删除成功");
                getData();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
    }

    private void getData() {
        helper = new SharedPreferencesHelper(this, "user");
        userId = (String) helper.getSharedPreference("userId", "");
        MyModel.getInstance().picList(userId,new ICallBack() {
            public void succeed(Object object) {
                List<String> imgs = (List<String>) object;
                PictureAdapter adapter = new PictureAdapter(imgs,PhotoActivity.this,handler);
                xcView.setAdapter(adapter);
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @OnClick({R.id.xc_back,R.id.xc_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xc_back:
                finish();
                break;
            case R.id.xc_add:
                Intent addxc = new Intent(PhotoActivity.this, AddPhotoActivity.class);
                startActivity(addxc);
                break;
        }
    }
}
