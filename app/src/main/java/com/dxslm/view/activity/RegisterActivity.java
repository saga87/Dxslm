package com.dxslm.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dxslm.R;
import com.dxslm.view.fragment.EmailRegisterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.iv_register_back)
    ImageView ivRegisterBack;
    @Bind(R.id.iv_register_ddd)
    ImageView ivRegisterDdd;
    @Bind(R.id.ll_register_2)
    LinearLayout llRegister2;
    @Bind(R.id.viewPager_register)
    ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //设置listener;
        setOnlistener();
        //设置Adapter
        setAdapter();
    }
    private void setAdapter() {
        try {
            fragments = new ArrayList<>();
            fragments.add(new EmailRegisterFragment());

            RegisterActivity.MyAdapter adapter = new RegisterActivity.MyAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }


    @OnClick({R.id.iv_register_back, R.id.ll_register_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_register_back:
                finish();
                break;
            case R.id.ll_register_2:
                viewPager.setCurrentItem(0);
                break;
        }
    }
}
