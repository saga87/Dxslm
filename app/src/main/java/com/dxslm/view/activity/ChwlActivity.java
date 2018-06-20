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
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.view.fragment.ActivityFragment;
import com.dxslm.view.fragment.ChiFragment;
import com.dxslm.view.fragment.HeFragment;
import com.dxslm.view.fragment.LeFragment;
import com.dxslm.view.fragment.SponsorFragment;
import com.dxslm.view.fragment.WanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChwlActivity extends AppCompatActivity {

    @Bind(R.id.iv_chwl_back)
    ImageView ivChwlBack;
    @Bind(R.id.iv_chwl_ddd)
    ImageView ivChwlDdd;
    @Bind(R.id.ll_chwl_1)
    LinearLayout llChwl1;
    @Bind(R.id.tv_chwl_1)
    TextView tvChwl1;
    @Bind(R.id.ll_chwl_2)
    LinearLayout llChwl2;
    @Bind(R.id.tv_chwl_2)
    TextView tvChwl2;
    @Bind(R.id.ll_chwl_3)
    LinearLayout llChwl3;
    @Bind(R.id.tv_chwl_3)
    TextView tvChwl3;
    @Bind(R.id.ll_chwl_4)
    LinearLayout llChwl4;
    @Bind(R.id.tv_chwl_4)
    TextView tvChwl4;
    @Bind(R.id.viewPager_chwl)
    ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chwl);
        ButterKnife.bind(this);
        //设置listener;
        setOnlistener();
        //设置Adapter
        setAdapter();
        tvChwl1.setVisibility(View.VISIBLE);
        tvChwl2.setVisibility(View.GONE);
        tvChwl3.setVisibility(View.GONE);
        tvChwl4.setVisibility(View.GONE);
    }
    private void setAdapter() {
        try {
            fragments = new ArrayList<>();
            fragments.add(new ChiFragment());
            fragments.add(new HeFragment());
            fragments.add(new WanFragment());
            fragments.add(new LeFragment());

            ChwlActivity.MyAdapter adapter = new ChwlActivity.MyAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(4);
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
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tvChwl1.setVisibility(View.VISIBLE);
                        tvChwl2.setVisibility(View.GONE);
                        tvChwl3.setVisibility(View.GONE);
                        tvChwl4.setVisibility(View.GONE);
                        break;
                    case 1:
                        tvChwl1.setVisibility(View.GONE);
                        tvChwl2.setVisibility(View.VISIBLE);
                        tvChwl3.setVisibility(View.GONE);
                        tvChwl4.setVisibility(View.GONE);
                        break;
                    case 2:
                        tvChwl1.setVisibility(View.GONE);
                        tvChwl2.setVisibility(View.GONE);
                        tvChwl3.setVisibility(View.VISIBLE);
                        tvChwl4.setVisibility(View.GONE);
                        break;
                    case 3:
                        tvChwl1.setVisibility(View.GONE);
                        tvChwl2.setVisibility(View.GONE);
                        tvChwl3.setVisibility(View.GONE);
                        tvChwl4.setVisibility(View.VISIBLE);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.iv_chwl_back, R.id.iv_chwl_ddd, R.id.ll_chwl_1, R.id.ll_chwl_2, R.id.ll_chwl_3, R.id.ll_chwl_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_chwl_back:
                finish();
                break;
            case R.id.iv_chwl_ddd:
                break;
            case R.id.ll_chwl_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_chwl_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_chwl_3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.ll_chwl_4:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
