package com.dxslm.view.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dxslm.R;
import com.dxslm.ui.NoScrollViewPager;
import com.dxslm.util.StatusBarUtil;
import com.dxslm.view.BaseActivity;
import com.dxslm.view.fragment.FriendFragment;
import com.dxslm.view.fragment.HeadFragment;
import com.dxslm.view.fragment.IssueFragment;
import com.dxslm.view.fragment.MyFragment;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;


import java.util.ArrayList;
import java.util.List;

public class HeadActivity extends BaseActivity {
    private NoScrollViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rb_1,rb_2,rb_3,rb_4;
    private List<Fragment> fragments;
    private Drawable topDrawable1,topDrawable2,topDrawable3,topDrawable4,
            topDrawable11,topDrawable22,topDrawable33,topDrawable44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        StatusBarUtil.setWindowStatusBarColor(this, Color.parseColor("#3f91d2"));
        initDrawable();
        //初始化控件
        setView();
        //设置listener;
        setOnlistener();
        //设置Adapter
        setAdapter();
        //申请相机权限
        initPermission();
    }

    private void initDrawable() {
        topDrawable1 = getResources().getDrawable(R.drawable.img1);
        topDrawable2 = getResources().getDrawable(R.drawable.img2);
        topDrawable3 = getResources().getDrawable(R.drawable.img3);
        topDrawable4 = getResources().getDrawable(R.drawable.img4);

        topDrawable11 = getResources().getDrawable(R.drawable.img11);
        topDrawable22 = getResources().getDrawable(R.drawable.img22);
        topDrawable33 = getResources().getDrawable(R.drawable.img33);
        topDrawable44 = getResources().getDrawable(R.drawable.img44);
    }

    private void setAdapter() {
        fragments = new ArrayList<>();
        fragments.add(new HeadFragment());
        fragments.add(new IssueFragment());
        fragments.add(new FriendFragment());
        fragments.add(new MyFragment());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }
    private class  MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    private void setOnlistener() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_4:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rb_1.setChecked(true);
                        topDrawable11.setBounds(0, 0, topDrawable11.getMinimumWidth(), topDrawable11.getMinimumHeight());
                        topDrawable2.setBounds(0, 0, topDrawable2.getMinimumWidth(), topDrawable2.getMinimumHeight());
                        topDrawable3.setBounds(0, 0, topDrawable3.getMinimumWidth(), topDrawable3.getMinimumHeight());
                        topDrawable4.setBounds(0, 0, topDrawable4.getMinimumWidth(), topDrawable4.getMinimumHeight());
                        rb_1.setCompoundDrawables(null,topDrawable11,null,null);
                        rb_2.setCompoundDrawables(null,topDrawable2,null,null);
                        rb_3.setCompoundDrawables(null,topDrawable3,null,null);
                        rb_4.setCompoundDrawables(null,topDrawable4,null,null);
                        rb_1.setTextColor(Color.parseColor("#0983FE"));
                        rb_2.setTextColor(Color.BLACK);
                        rb_3.setTextColor(Color.BLACK);
                        rb_4.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        rb_2.setChecked(true);
                        topDrawable1.setBounds(0, 0, topDrawable1.getMinimumWidth(), topDrawable1.getMinimumHeight());
                        topDrawable22.setBounds(0, 0, topDrawable22.getMinimumWidth(), topDrawable22.getMinimumHeight());
                        topDrawable3.setBounds(0, 0, topDrawable3.getMinimumWidth(), topDrawable3.getMinimumHeight());
                        topDrawable4.setBounds(0, 0, topDrawable4.getMinimumWidth(), topDrawable4.getMinimumHeight());
                        rb_1.setCompoundDrawables(null,topDrawable1,null,null);
                        rb_2.setCompoundDrawables(null,topDrawable22,null,null);
                        rb_3.setCompoundDrawables(null,topDrawable3,null,null);
                        rb_4.setCompoundDrawables(null,topDrawable4,null,null);
                        rb_1.setTextColor(Color.BLACK);
                        rb_2.setTextColor(Color.parseColor("#0983FE"));
                        rb_3.setTextColor(Color.BLACK);
                        rb_4.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        rb_3.setChecked(true);
                        topDrawable1.setBounds(0, 0, topDrawable1.getMinimumWidth(), topDrawable1.getMinimumHeight());
                        topDrawable2.setBounds(0, 0, topDrawable2.getMinimumWidth(), topDrawable2.getMinimumHeight());
                        topDrawable33.setBounds(0, 0, topDrawable33.getMinimumWidth(), topDrawable33.getMinimumHeight());
                        topDrawable4.setBounds(0, 0, topDrawable4.getMinimumWidth(), topDrawable4.getMinimumHeight());
                        rb_1.setCompoundDrawables(null,topDrawable1,null,null);
                        rb_2.setCompoundDrawables(null,topDrawable2,null,null);
                        rb_3.setCompoundDrawables(null,topDrawable33,null,null);
                        rb_4.setCompoundDrawables(null,topDrawable4,null,null);
                        rb_1.setTextColor(Color.BLACK);
                        rb_2.setTextColor(Color.BLACK);
                        rb_3.setTextColor(Color.parseColor("#0983FE"));
                        rb_4.setTextColor(Color.BLACK);
                        break;
                    case 3:
                        rb_4.setChecked(true);
                        topDrawable1.setBounds(0, 0, topDrawable1.getMinimumWidth(), topDrawable1.getMinimumHeight());
                        topDrawable2.setBounds(0, 0, topDrawable2.getMinimumWidth(), topDrawable2.getMinimumHeight());
                        topDrawable3.setBounds(0, 0, topDrawable3.getMinimumWidth(), topDrawable3.getMinimumHeight());
                        topDrawable44.setBounds(0, 0, topDrawable44.getMinimumWidth(), topDrawable44.getMinimumHeight());
                        rb_1.setCompoundDrawables(null,topDrawable1,null,null);
                        rb_2.setCompoundDrawables(null,topDrawable2,null,null);
                        rb_3.setCompoundDrawables(null,topDrawable3,null,null);
                        rb_4.setCompoundDrawables(null,topDrawable44,null,null);
                        rb_1.setTextColor(Color.BLACK);
                        rb_2.setTextColor(Color.BLACK);
                        rb_3.setTextColor(Color.BLACK);
                        rb_4.setTextColor(Color.parseColor("#0983FE"));
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    private void initPermission() {
        // 申请权限。
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .send();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    //权限申请回调接口
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if(requestCode == 100) {
                // TODO 相应代码。
                //do nothing
            }
        }
        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(HeadActivity.this, deniedPermissions)) {
                // 用自定义的提示语
                AndPermission.defaultSettingDialog(HeadActivity.this, 103)
                        .setTitle("权限申请失败")
                        .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                        .setPositiveButton("好，去设置")
                        .show();
            }
        }
    };
    private void setView() {
        viewPager= (NoScrollViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);
    }
}
