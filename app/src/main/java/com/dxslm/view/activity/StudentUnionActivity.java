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
import com.dxslm.view.BaseActivity;
import com.dxslm.view.fragment.ActivityFragment;
import com.dxslm.view.fragment.SponsorFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentUnionActivity extends BaseActivity {

    @Bind(R.id.iv_student_union_back)
    ImageView ivStudentUnionBack;
    @Bind(R.id.iv_student_union_ddd)
    ImageView ivStudentUnionDdd;
    @Bind(R.id.ll_found_paw_1)
    LinearLayout llFoundPaw1;
    @Bind(R.id.tv_found_paw_1)
    TextView tvFoundPaw1;
    @Bind(R.id.ll_found_paw_2)
    LinearLayout llFoundPaw2;
    @Bind(R.id.tv_found_paw_2)
    TextView tvFoundPaw2;
    @Bind(R.id.viewPager_student_union)
    ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_union);
        ButterKnife.bind(this);
        //设置listener;
        setOnlistener();
        //设置Adapter
        setAdapter();
        tvFoundPaw1.setVisibility(View.VISIBLE);
        tvFoundPaw2.setVisibility(View.GONE);
    }
    private void setAdapter() {
        try {
            fragments = new ArrayList<>();
            fragments.add(new ActivityFragment());
            fragments.add(new SponsorFragment());

            MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(2);
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
                        tvFoundPaw1.setVisibility(View.VISIBLE);
                        tvFoundPaw2.setVisibility(View.GONE);
                        break;
                    case 1:
                        tvFoundPaw1.setVisibility(View.GONE);
                        tvFoundPaw2.setVisibility(View.VISIBLE);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        llFoundPaw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        llFoundPaw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
    }

    @OnClick({R.id.iv_student_union_back, R.id.iv_student_union_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_student_union_back:
                finish();
                break;
            case R.id.iv_student_union_ddd:
                break;
        }
    }
}
