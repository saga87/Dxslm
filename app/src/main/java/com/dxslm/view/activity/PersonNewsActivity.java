package com.dxslm.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.entity.user.PersonMessage;
import com.dxslm.entity.user.User;
import com.dxslm.model.ICallBack;
import com.dxslm.model.OtherModel;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.view.BaseActivity;
import com.liji.circleimageview.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonNewsActivity extends BaseActivity {

    @Bind(R.id.iv_user_message_back_1)
    ImageView ivUserMessageBack1;
    @Bind(R.id.iv_user_message_ddd_1)
    ImageView ivUserMessageDdd1;
    @Bind(R.id.et_user_message_nc_news)
    TextView etUserMessageNcNews;
    @Bind(R.id.et_user_message_sjh_news)
    TextView etUserMessageSjhNews;
    @Bind(R.id.et_user_message_yx_news)
    TextView etUserMessageYxNews;
    private String  userId ;
    private ImageView imageView;
    private CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_news);
        ButterKnife.bind(this);
        imageView = (ImageView) findViewById(R.id.img_tupian_person_new);
        circleImageView = (CircleImageView) findViewById(R.id.iv_my_touxiang_per_new);
        userId = getIntent().getStringExtra("userId");
        setModel();
    }
    private void  setModel(){
        OtherModel.getInstance().getMessage(userId, new ICallBack() {
            @Override
            public void succeed(Object object) {
               PersonMessage personMessage = (PersonMessage) object;

                etUserMessageNcNews.setText(personMessage.getList().get(0).getUser_realname());
                etUserMessageSjhNews.setText(personMessage.getList().get(0).getUser_tel());
                etUserMessageYxNews.setText(personMessage.getList().get(0).getUser_email());
                String pic_url = personMessage.getList().get(0).getHeadpic();
                if (null==pic_url||"".equals(pic_url)){
                    GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,circleImageView);
                }else {
                    GlideUtil.getIconBitmap(UrlUtil.URL+pic_url, circleImageView);
                }
         //       GlideUtil.getIconBitmap(UrlUtil.URL+personMessage.getList().get(0).getHeadpic(),circleImageView);
                GlideUtil.getBitmap(UrlUtil.URL+personMessage.getList().get(0).getBackgroundpic(),imageView);
               Log.e("++++++++++++",personMessage.getList().get(0).getBackgroundpic());

                Log.e("---------------",personMessage.getList().get(0).getHeadpic());
            }

            @Override
            public void error(Object object) {

            }
        });
    }
    @OnClick({R.id.iv_user_message_back_1, R.id.iv_user_message_ddd_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_message_back_1:
                finish();
                break;
            case R.id.iv_user_message_ddd_1:
                break;
        }
    }
}
