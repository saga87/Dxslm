package com.dxslm.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.ClassifyOne;
import com.dxslm.entity.RegsiterSchoolList;
import com.dxslm.entity.Success;
import com.dxslm.entity.user.YzmCode;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.model.UserModel;
import com.dxslm.util.CountDownTimerUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
import com.dxslm.view.BaseFragment;
import com.dxslm.view.activity.SchoolClassifyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fxn on 2017/12/11.
 */

public class EmailRegisterFragment extends BaseFragment {
    @Bind(R.id.et_email_register_name)
    EditText etEmailRegisterName;
    @Bind(R.id.sp_email_register_school)
    TextView spEmailRegisterSchool;
    @Bind(R.id.et_email_register_pws)
    EditText etEmailRegisterPws;
    @Bind(R.id.et_email_register_email)
    EditText etEmailRegisterEmail;
    @Bind(R.id.btn_email_register_yzm)
    Button btnEmailRegisterYzm;
    @Bind(R.id.et_email_register_yzm)
    EditText etEmailRegisterYzm;
    @Bind(R.id.btn_email_register_ok)
    Button btnEmailRegisterOk;
    private List<String> schoolList = new ArrayList<>();
    private RegsiterSchoolList list;
    private String yzm;
    private LoginBroadcastReceiver mReceiver;
    private ClassifyOne.ListBean pss;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email_register, null);
        ButterKnife.bind(this, view);

        //注册广播方法
        mReceiver = new EmailRegisterFragment.LoginBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("school");
        LocalBroadcastManager.getInstance(MyApplication.getInstance()).registerReceiver(mReceiver,intentFilter);
        return view;
    }

    /**
     * 自定义广播接受器,用来处理登录广播
     */
    private class LoginBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("school")) {
                //处理我们具体的逻辑,更新UI
                pss = (ClassifyOne.ListBean) intent.getSerializableExtra("item");
                spEmailRegisterSchool.setText(pss.getDept_name());
                spEmailRegisterSchool.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_email_register_yzm, R.id.btn_email_register_ok, R.id.sp_email_register_school})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_email_register_yzm:
                String email = etEmailRegisterEmail.getText().toString();
                if(email.equals("")){
                    Util.showToast("请输入邮箱号");
                    return;
                }
                btnEmailRegisterYzm.setTextColor(Color.RED);
                CountDownTimerUtil countDownTimer = new CountDownTimerUtil(60000,1000,btnEmailRegisterYzm);
                countDownTimer.start();
                UserModel.getInstance().emailYzm(email, new ICallBack() {
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
            case R.id.btn_email_register_ok:
                String name = etEmailRegisterName.getText().toString();
                String pws = etEmailRegisterPws.getText().toString();
                String email1 = etEmailRegisterEmail.getText().toString();
                String yanzhengma = etEmailRegisterYzm.getText().toString();
                if(name.equals("")||pws.equals("")||email1.equals("")||yanzhengma.equals("")){
                    Util.showToast("请补全数据");
                    return;
                }
                if(!yanzhengma.equals(yzm)||yzm==null){
                    Util.showToast("验证码输入不正确");
                    return;
                }
                if(pss==null){
                    Util.showToast("请先选择学校");
                    return;
                }
                UserModel.getInstance().register(name, pws, pss.getDept_id(), email1, new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success success = (Success) object;
                        if(success.isSuccess()){
                            getActivity().finish();
                            Util.showToast("注册成功");
                        }else {
                            Util.showToast("注册失败"+success.getMsg());
                        }
                    }
                    @Override
                    public void error(Object object) {
                        Util.showToast("注册失败，请过会在试");
                    }
                });
                break;
            case R.id.sp_email_register_school:
                Intent intent = new Intent(getActivity(), SchoolClassifyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
