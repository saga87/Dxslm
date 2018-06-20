package com.dxslm.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dxslm.R;
import com.dxslm.view.BaseActivity;
import com.dxslm.view.fragment.UsedAllFragment;
import com.dxslm.view.fragment.UsedComeFragment;
import com.dxslm.view.fragment.UsedGoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsedActivity extends BaseActivity {
    @Bind(R.id.iv_used_back)
    ImageView ivUsedBack;
    @Bind(R.id.iv_used_ddd)
    ImageView ivUsedDdd;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rb_1, rb_2, rb_3;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used);
        ButterKnife.bind(this);
        //初始化控件
        setView();
        //设置listener;
        setOnlistener();
        //设置Adapter
        setAdapter();
    }

    private void setAdapter() {
        fragments = new ArrayList<>();
        fragments.add(new UsedAllFragment());
        fragments.add(new UsedComeFragment());
        fragments.add(new UsedGoFragment());

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        rb_1.setTextColor(Color.WHITE);
    }

    @OnClick({R.id.iv_used_back, R.id.iv_used_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_used_back:
                finish();
                break;
            case R.id.iv_used_ddd:
                break;
        }
    }

    private class MyAdapter extends FragmentPagerAdapter {

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
                switch (checkedId) {
                    case R.id.rb_used_1:
                        viewPager.setCurrentItem(0);
                        rb_1.setTextColor(Color.WHITE);
                        rb_2.setTextColor(Color.BLACK);
                        rb_3.setTextColor(Color.BLACK);
                        break;
                    case R.id.rb_used_2:
                        viewPager.setCurrentItem(1);
                        rb_1.setTextColor(Color.BLACK);
                        rb_2.setTextColor(Color.WHITE);
                        rb_3.setTextColor(Color.BLACK);
                        break;
                    case R.id.rb_used_3:
                        viewPager.setCurrentItem(2);
                        rb_1.setTextColor(Color.BLACK);
                        rb_2.setTextColor(Color.BLACK);
                        rb_3.setTextColor(Color.WHITE);
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
                switch (position) {
                    case 0:
                        rb_1.setChecked(true);
                        rb_1.setTextColor(Color.WHITE);
                        rb_2.setTextColor(Color.BLACK);
                        rb_3.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        rb_2.setChecked(true);
                        rb_1.setTextColor(Color.BLACK);
                        rb_2.setTextColor(Color.WHITE);
                        rb_3.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        rb_3.setChecked(true);
                        rb_1.setTextColor(Color.BLACK);
                        rb_2.setTextColor(Color.BLACK);
                        rb_3.setTextColor(Color.WHITE);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager_used);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_used);
        rb_1 = (RadioButton) findViewById(R.id.rb_used_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_used_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_used_3);
    }
}
