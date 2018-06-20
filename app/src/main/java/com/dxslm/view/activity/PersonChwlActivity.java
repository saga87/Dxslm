package com.dxslm.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.entity.user.PersonMessage;
import com.dxslm.model.ICallBack;
import com.dxslm.model.OtherModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonChwlActivity extends AppCompatActivity {
    @Bind(R.id.iv_user_message_back_1)
    ImageView ivUserMessageBack1;
    @Bind(R.id.iv_user_message_ddd_1)
    ImageView ivUserMessageDdd1;
    @Bind(R.id.et_user_message_nc_chwl)
    TextView etUserMessageNcChwl;
    @Bind(R.id.et_user_message_sjh_chwl)
    TextView etUserMessageSjhChwl;
    @Bind(R.id.et_user_message_yx_chwl)
    TextView etUserMessageYxChwl;
    private String ser_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_chwl);
        ButterKnife.bind(this);
        ser_user = getIntent().getStringExtra("ser_user");
        setModel();
    }

    private void  setModel(){
        OtherModel.getInstance().getMessage(ser_user, new ICallBack() {
            @Override
            public void succeed(Object object) {
                PersonMessage personMessage = (PersonMessage) object;
                etUserMessageNcChwl.setText(personMessage.getList().get(0).getUser_realname());
                etUserMessageSjhChwl.setText(personMessage.getList().get(0).getUser_tel());
                etUserMessageYxChwl.setText(personMessage.getList().get(0).getUser_email());

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
