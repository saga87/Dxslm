package com.dxslm.view.fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.adapter.TestAdapter;
import com.dxslm.entity.ClassifyOne;
import com.dxslm.entity.news.NewsList;
import com.dxslm.entity.school.SchoolPics;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.model.OtherModel;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.dxslm.view.BaseFragment;
import com.dxslm.view.activity.ChwlActivity;
import com.dxslm.view.activity.CommunityActivity;
import com.dxslm.view.activity.ForumActivity;
import com.dxslm.view.activity.LoseActivity;
import com.dxslm.view.activity.LoveActivity;
import com.dxslm.view.activity.NewsActivity;
import com.dxslm.view.activity.NewsDetailsActivity;
import com.dxslm.view.activity.OtherWorkActivity;
import com.dxslm.view.activity.PostActivity;
import com.dxslm.view.activity.SchoolActivity;
import com.dxslm.view.activity.SchoolClassifyActivity;
import com.dxslm.view.activity.StudentActivity;
import com.dxslm.view.activity.StudentUnionActivity;
import com.dxslm.view.activity.UsedActivity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HeadFragment extends BaseFragment {

    @Bind(R.id.iv_head_back)
    ImageView ivHeadBack;

    @Bind(R.id.recyclerview_head)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_head)
    TwinklingRefreshLayout refresh;
    @Bind(R.id.tv_head_school)
    TextView tvHeadSchool;

    private View view;
    private LoginBroadcastReceiver mReceiver;
    private String school;
    private String deptId;
    private int page = 1;
    private int pagesize = 20;
    private TestAdapter adapter;

//    private String schoolBadgePic;
    private String schoolSignPic;
    //    private String url1;
//    private String url2;
    private ImageView imageView_xiaohui;
    private  ImageView imageView_jianzhu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_head, null);
        ButterKnife.bind(this, view);

        try {
            SharedPreferencesHelper helper = new SharedPreferencesHelper(getContext(), "user");
            school = (String) helper.getSharedPreference("school", "");
            deptId = (String) helper.getSharedPreference("deptId", "");
            //校徽和建筑物
//            schoolBadgePic = (String) helper.getSharedPreference("schoolBadgePic", "");
            schoolSignPic = (String) helper.getSharedPreference("schoolSignPic", "");
//            url1 = UrlUtil.URL+schoolBadgePic;
//            url2 = UrlUtil.URL+schoolSignPic;
            tvHeadSchool.setText(school);
            //注册广播方法
            mReceiver = new LoginBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter("school");
            LocalBroadcastManager.getInstance(MyApplication.getInstance()).registerReceiver(mReceiver,intentFilter);
            //获取数据
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                ClassifyOne.ListBean pss = (ClassifyOne.ListBean) intent.getSerializableExtra("item");
                tvHeadSchool.setText(pss.getDept_name());
                SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(),"user");
                helper.put("deptId",pss.getDept_id());
                deptId = pss.getDept_id();
                changePic(deptId);
                page = 1;
                getList(page);

            }
        }
    }
    private void getData() {
        HeadModel.getInstance().newsList(page, pagesize, deptId, new ICallBack() {
            public void succeed(Object object) {
                NewsList list = (NewsList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<NewsList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(getContext());
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TestAdapter(list,getActivity());
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.head_head_recycler, null);
        adapter.setHeaderView(view1);
        recyclerview.setAdapter(adapter);
        //初始化头部局的控件
        setView(view1);
        //动画效果
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //动画默认只执行一次,如果想重复执行可设置
        adapter.isFirstOnly(false);
        refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        getList(page);
                        refreshLayout.finishRefreshing();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = page + 1;
                        getList(page);
                        refreshLayout.finishLoadmore();
                    }
                }, 500);
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<NewsList.ListBean> list = adapter.getData();
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("newsId",list.get(position).getNews_id());
                startActivity(intent);
            }
        });

    }

    /**获取上拉下拉  刷新数据*/
    public void getList(int page1) {
        HeadModel.getInstance().newsList(page1, pagesize, deptId, new ICallBack() {
            public void succeed(Object object) {
                NewsList list = (NewsList) object;
                if(page == 1) {
                    adapter.replaceData(list.getList());
                }else{
                    adapter.addData(list.getList());
                }
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    private void changePic(final String deptId){
        OtherModel.getInstance().getSchoolPic(deptId, new ICallBack() {
            @Override
            public void succeed(Object object) {
                SchoolPics schoolPics = (SchoolPics) object;
                SchoolPics.ListBean sl =schoolPics.getList().get(0);
                if(sl==null){
                    Util.showToast("图片为空");
                }
//                String  badge = sl.getSchoolBadgePic();
                String sign = sl.getSchoolSignPic();
//                if(badge!=null){
//                    GlideUtil.getBitmap((UrlUtil.URL+badge).split(",")[0],imageView_xiaohui);
//                }
                if(sign!=null){
                    GlideUtil.getBitmap((UrlUtil.URL+sign).split(",")[0],imageView_jianzhu);
                }
            }

            @Override
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    private void setView(View view) {
        LinearLayout ll1 = view.findViewById(R.id.ll_head_img1);
        LinearLayout ll2 = view.findViewById(R.id.ll_head_img2);
        LinearLayout ll3 = view.findViewById(R.id.ll_head_img3);
        LinearLayout ll4 = view.findViewById(R.id.ll_head_img4);
        LinearLayout ll5 = view.findViewById(R.id.ll_head_img5);
        LinearLayout ll6 = view.findViewById(R.id.ll_head_img6);
        LinearLayout ll7 = view.findViewById(R.id.ll_head_img7);
        LinearLayout ll8 = view.findViewById(R.id.ll_head_img8);
        Button btn1 = view.findViewById(R.id.btn_head_chwl);
        Button btn2 = view.findViewById(R.id.btn_head_es);
        Button btn3 = view.findViewById(R.id.btn_head_swzl);
        Button btn4 = view.findViewById(R.id.btn_head_jz);
//        imageView_xiaohui=view.findViewById(R.id.head_xiaohui);
        imageView_jianzhu=view.findViewById(R.id.head_xiaohui_tupian);
        changePic(deptId);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentActivity.class);
                startActivity(intent);
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SchoolActivity.class);
                startActivity(intent);
            }
        });
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoveActivity.class);
                startActivity(intent);
            }
        });
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForumActivity.class);
                startActivity(intent);
            }
        });
        ll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);
            }
        });
        ll7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentUnionActivity.class);
                startActivity(intent);
            }
        });
        ll8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommunityActivity.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChwlActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UsedActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoseActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OtherWorkActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mReceiver);
    }

    @OnClick({R.id.iv_head_back, R.id.tv_head_school})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_back:
                logout();
                break;
            case R.id.tv_head_school:
                Intent intent = new Intent(getActivity(), SchoolClassifyActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void logout(){
        new AlertDialog.Builder(getContext()).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        getActivity().finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }


}
