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
import com.dxslm.view.fragment.EmailPawFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPawActivity extends AppCompatActivity {

    @Bind(R.id.iv_search_paw_back)
    ImageView ivSearchPawBack;
    @Bind(R.id.iv_search_paw_ddd)
    ImageView ivSearchPawDdd;
    @Bind(R.id.ll_search_paw_2)
    LinearLayout llSearchPaw2;
    @Bind(R.id.viewPager_search_paw)
    ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_paw);
        ButterKnife.bind(this);
        //设置listener;
        setOnlistener();
        //设置Adapter
        setAdapter();
    }
    private void setAdapter() {
        try {
            fragments = new ArrayList<>();
            fragments.add(new EmailPawFragment());

            SearchPawActivity.MyAdapter adapter = new SearchPawActivity.MyAdapter(getSupportFragmentManager());
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


    @OnClick({R.id.iv_search_paw_back, R.id.ll_search_paw_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_paw_back:
                finish();
                break;
            case R.id.ll_search_paw_2:
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
