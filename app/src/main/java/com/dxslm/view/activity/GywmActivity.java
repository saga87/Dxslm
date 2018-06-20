package com.dxslm.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxslm.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GywmActivity extends AppCompatActivity {

    @Bind(R.id.iv_gywm_back)
    ImageView ivGywmBack;
    @Bind(R.id.iv_news_ddd)
    ImageView ivNewsDdd;
    @Bind(R.id.tv_gywm_phone)
    TextView tvGywmPhone;
    @Bind(R.id.tv_gywm_email)
    TextView tvGywmEmail;
    @Bind(R.id.tv_gywm_wx)
    TextView tvGywmWx;
    @Bind(R.id.tv_gywm_qq)
    TextView tvGywmQq;
    @Bind(R.id.tv_gywm_gzh)
    TextView tvGywmGzh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gywm);
        ButterKnife.bind(this);
        tvGywmPhone.setText("联系电话：   " + "17863632588");
        tvGywmEmail.setText("电子邮箱：   " + "2074077087@qq.com");
        tvGywmWx.setText("微信：   " + "17863632588");
        tvGywmQq.setText("QQ：   " + "2074077087");
        tvGywmGzh.setText("微信公众号：   " + "大学生高校联盟");
    }

    @OnClick(R.id.iv_gywm_back)
    public void onViewClicked() {
        finish();
    }
}
