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

public class UserMesssgeActivity extends AppCompatActivity {


    @Bind(R.id.iv_user_message_back_1)
    ImageView ivUserMessageBack1;
    @Bind(R.id.iv_user_message_ddd_1)
    ImageView ivUserMessageDdd1;
    @Bind(R.id.et_user_message_nc_1)
    TextView etUserMessageNc1;
    @Bind(R.id.et_user_message_sjh_1)
    TextView etUserMessageSjh1;
    @Bind(R.id.et_user_message_yx_1)
    TextView etUserMessageYx1;
    private String Sar_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_messsge);
        ButterKnife.bind(this);
        Sar_user = getIntent().getStringExtra("Sar_user");
        setModel();
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

    private void  setModel(){
        OtherModel.getInstance().getMessage(Sar_user, new ICallBack() {
            @Override
            public void succeed(Object object) {
                PersonMessage personMessage = (PersonMessage) object;
                etUserMessageNc1.setText(personMessage.getList().get(0).getUser_realname());
                etUserMessageSjh1.setText(personMessage.getList().get(0).getUser_tel());
                etUserMessageYx1.setText(personMessage.getList().get(0).getUser_email());

            }

            @Override
            public void error(Object object) {

            }
        });
    }

}
