package com.dxslm.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dxslm.R;
import com.dxslm.entity.user.PersonMessage;
import com.dxslm.model.ICallBack;
import com.dxslm.model.OtherModel;
import com.dxslm.ui.NoScrollViewPager;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.view.BaseActivity;
import com.dxslm.view.fragment.OtherCameraFragment;
import com.dxslm.view.fragment.OtherCollectFragment;
import com.dxslm.view.fragment.OtherCollectUnFinishFragment;
import com.dxslm.view.fragment.OtherMessageFragment;
import com.liji.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherPersonActivity extends BaseActivity {

    @Bind(R.id.otheruser_back)
    ImageView otheruserBack;
    @Bind(R.id.img_otheruser)
    ImageView imgOtheruser;
    @Bind(R.id.iv_touxiang_otheruser)
    CircleImageView ivTouxiangOtheruser;


    @Bind(R.id.rb_xc)
    RadioButton rbXc;
    @Bind(R.id.rb_xx)
    RadioButton rbXx;
    @Bind(R.id.rb_fb)
    RadioButton rbFb;
    @Bind(R.id.radioGroup_otheruser)
    RadioGroup radioGroupOtheruser;
    @Bind(R.id.viewPager_otheruser)
    NoScrollViewPager viewPagerOtheruser;

    private String userId;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person);
        ButterKnife.bind(this);
        userId = getIntent().getStringExtra("userId");
        setModel();
        radioGroupOtheruser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_xc:
                        viewPagerOtheruser.setCurrentItem(0);
                        break;
                    case R.id.rb_xx:
                        viewPagerOtheruser.setCurrentItem(1);
                        break;
                    case R.id.rb_fb:
                        viewPagerOtheruser.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPagerOtheruser.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rbXc.setChecked(true);
                        rbXc.setTextColor(Color.parseColor("#436EEE"));
                        rbXx.setTextColor(Color.BLACK);
                        rbFb.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        rbXx.setChecked(true);
                        rbXx.setTextColor(Color.parseColor("#436EEE"));
                        rbXc.setTextColor(Color.BLACK);
                        rbFb.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        rbFb.setChecked(true);
                        rbFb.setTextColor(Color.parseColor("#436EEE"));
                        rbXx.setTextColor(Color.BLACK);
                        rbXc.setTextColor(Color.BLACK);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setGroup();
    }

    public String getUserId(){
        return userId;
    }

    private void setGroup() {
        fragments = new ArrayList<>();
        fragments.add(new OtherCameraFragment());
        fragments.add(new OtherMessageFragment());
        fragments.add(new OtherCollectUnFinishFragment());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPagerOtheruser.setAdapter(adapter);
        viewPagerOtheruser.setOffscreenPageLimit(3);
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

    private void setModel() {
        OtherModel.getInstance().getMessage(userId, new ICallBack() {
            @Override
            public void succeed(Object object) {
                PersonMessage personMessage = (PersonMessage) object;

                String pic_url = personMessage.getList().get(0).getHeadpic();
                if (null == pic_url || "".equals(pic_url)) {
                    GlideUtil.getDefaultIconBitmap(R.drawable.mydefault, ivTouxiangOtheruser);
                } else {
                    GlideUtil.getIconBitmap(UrlUtil.URL + pic_url, ivTouxiangOtheruser);
                }
                GlideUtil.getBitmap(UrlUtil.URL + personMessage.getList().get(0).getBackgroundpic(), imgOtheruser);
            }

            @Override
            public void error(Object object) {

            }
        });
    }

    @OnClick(R.id.otheruser_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.otheruser_back:
                finish();
                break;
        }
    }

}
