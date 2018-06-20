package com.dxslm.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.UpdateApp;
import com.dxslm.entity.user.UserMessage;
import com.dxslm.model.ICallBack;
import com.dxslm.model.LoginModel;
import com.dxslm.model.OtherModel;
import com.dxslm.util.OSUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UpdateAppHttpUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.dxslm.view.BaseActivity;
import com.vector.update_app.UpdateAppManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.et_login_username)
    EditText etLoginUsername;
    @Bind(R.id.et_login_paw)
    EditText etLoginPaw;
    @Bind(R.id.tv_login_find_paw)
    TextView tvLoginFindPaw;
    @Bind(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    @Bind(R.id.btn_login_register)
    Button btnLoginRegister;
    private UserMessage userMessage;
    private String username;
    private String paw;
    private ProgressDialog waitingDialog;
    private int size=14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }
        ButterKnife.bind(this);

        try {
            SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "login");
            String username1 = (String) helper.getSharedPreference("username", "");
            String paw1 = (String) helper.getSharedPreference("paw", "");
            etLoginUsername.setText(username1);
            etLoginPaw.setText(paw1);
         updateApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 更新app
     */
    private void updateApp() {
        LoginModel.getInstance().updateApp( new ICallBack() {
            @Override
            public void succeed(Object object) {
                try {
                    UpdateApp app = (UpdateApp) object;
                    String version = Util.getLocalVersionName(LoginActivity.this);
                    double newVersion = Double.parseDouble(app.getNew_version());
                    double oldVersion = Double.parseDouble(version);
                    if(newVersion>oldVersion){
                        new UpdateAppManager
                                .Builder()
                                //当前Activity
                                .setActivity(LoginActivity.this)
                                //更新地址
                                .setUpdateUrl(UrlUtil.urlUpateApp)
                                //实现httpManager接口的对象
                                .setHttpManager(new UpdateAppHttpUtil())
                                .build()
                                .update();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void error(Object object) {
            }
        });
    }


    @OnClick({R.id.tv_login_find_paw, R.id.btn_login_confirm , R.id.btn_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_find_paw:
                Intent intent1 = new Intent(LoginActivity.this,SearchPawActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_login_confirm:
                showWaitingDialog();
                btnLoginConfirm.setClickable(false);
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        btnLoginConfirm.setClickable(true);
                    }
                }, 2000);
                username = etLoginUsername.getText().toString();
                paw = etLoginPaw.getText().toString();


                //登陆
                LoginModel.getInstance().login(username, paw, new ICallBack() {
                    public void succeed(Object object) {
                        userMessage = (UserMessage) object;
                        if(userMessage.isSuccess()){
                            if(userMessage!=null) {
                                SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
                                helper.put("userId", userMessage.getUser_id());
                                helper.put("deptId", userMessage.getDept_id());
                                helper.put("roleId", userMessage.getRole_id());
                                helper.put("school", userMessage.getUserschool());
                                helper.put("pic", userMessage.getThpic());
                                helper.put("bgpic",userMessage.getBgpic());
                                helper.put("schoolBadgePic", userMessage.getSchoolBadgePic());
                                helper.put("schoolSignPic", userMessage.getSchoolSignPic());
                                helper.put("size",size);
                                helper.put("orideptId",userMessage.getDept_id());
                                helper.put("isGly",userMessage.getIsGly());

//                                Intent intent = new Intent(LoginActivity.this, YinDaoActivity.class);
//                                startActivity(intent);

                                Intent intent = new Intent(LoginActivity.this, HeadActivity.class);
                                startActivity(intent);

                                SharedPreferencesHelper helper1 = new SharedPreferencesHelper(MyApplication.getInstance(),"login");
                                helper1.put("username",username);
                                helper1.put("paw",paw);

                                OtherModel.getInstance().sendXGToken(OtherModel.getInstance().getToken());

                                finish();
                            }
                        }else{
                            Util.showToast("登陆失败"+userMessage.getMsg());
                        }
                        waitingDialog.dismiss();
                    }
                    public void error(Object object) {
                        waitingDialog.dismiss();
                        Util.showToast("登陆失败"+(String) object);
                    }
                });
                break;
            case R.id.btn_login_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        waitingDialog=
                new ProgressDialog(LoginActivity.this);
        waitingDialog.setTitle("页面加载");
        waitingDialog.setMessage("登录中  请等待...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (waitingDialog!=null){
            waitingDialog.dismiss();
        }
        super.onBackPressed();
    }
}
