package com.dxslm.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.entity.user.YzmCode;
import com.dxslm.model.ICallBack;
import com.dxslm.model.UserModel;
import com.dxslm.util.CountDownTimerUtil;
import com.dxslm.util.Util;
import com.dxslm.view.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EmailPawFragment extends BaseFragment {
    @Bind(R.id.et_email_paw_email)
    EditText etEmailPawEmail;
    @Bind(R.id.btn_email_paw_yzm)
    Button btnEmailPawYzm;
    @Bind(R.id.et_email_paw_yzm)
    EditText etEmailPawYzm;
    @Bind(R.id.et_email_paw_xpaw)
    EditText etEmailPawXpaw;
    @Bind(R.id.et_email_paw_jpaw)
    EditText etEmailPawJpaw;
    @Bind(R.id.btn_email_paw_ok)
    Button btnEmailPawOk;
    private String yzm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email_paw, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_email_paw_yzm, R.id.btn_email_paw_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_email_paw_yzm:
                String email = etEmailPawEmail.getText().toString();
                if(email.equals("")){
                    Util.showToast("请输入邮箱号");
                    return;
                }
                btnEmailPawYzm.setTextColor(Color.RED);
                CountDownTimerUtil countDownTimer = new CountDownTimerUtil(60000,1000,btnEmailPawYzm);
                countDownTimer.start();
                UserModel.getInstance().emailPawYzm(email, new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        YzmCode code = (YzmCode) object;
                        if(code.getMsg().equals("发送成功")){
                            Util.showToast("发送成功");
                            yzm = code.getCode();
                        }
                    }
                    @Override
                    public void error(Object object) {
                        Util.showToast("请检查邮箱号");
                    }
                });
                break;
            case R.id.btn_email_paw_ok:
                String email1 = etEmailPawEmail.getText().toString();
                String jpws = etEmailPawJpaw.getText().toString();
                String xpws = etEmailPawXpaw.getText().toString();
                String yanzhengma = etEmailPawYzm.getText().toString();
                if(email1.equals("")||jpws.equals("")||xpws.equals("")||yanzhengma.equals("")){
                    Util.showToast("请补全数据");
                    return;
                }
                if(!yanzhengma.equals(yzm)||yzm==null){
                    Util.showToast("验证码输入不正确");
                    return;
                }
                if(!jpws.equals(xpws)){
                    Util.showToast("两次密码输入不一致，请从新输入。");
                    return;
                }
                UserModel.getInstance().emailSearchPaw(email1,jpws, new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success success = (Success) object;
                        if(success.isSuccess()){
                            getActivity().finish();
                            Util.showToast("找回成功");
                        }else {
                            Util.showToast("找回失败"+success.getMsg());
                        }
                    }
                    @Override
                    public void error(Object object) {
                        Util.showToast("找回失败，请过会在试");
                    }
                });
                break;
        }
    }
}
