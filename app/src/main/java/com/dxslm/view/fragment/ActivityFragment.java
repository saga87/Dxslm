package com.dxslm.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.R;
import com.dxslm.adapter.SchoolAdapter;
import com.dxslm.adapter.StudentActivityAdapter;
import com.dxslm.adapter.TestAdapter;
import com.dxslm.entity.school.SchoolList;
import com.dxslm.entity.student.StudentActivityList;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
import com.dxslm.view.BaseFragment;
import com.dxslm.view.activity.SchoolActivity;
import com.dxslm.view.activity.SchoolDetailsActivity;
import com.dxslm.view.activity.StudentUnionDetailsActivity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ActivityFragment extends BaseFragment {
    @Bind(R.id.recyclerview_factivity)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_factivity)
    TwinklingRefreshLayout refresh;
    private String deptId;
    private String roleId;
    private int page = 1;
    private int pagesize = 20;
    private StudentActivityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, null);
        ButterKnife.bind(this, view);
        getData();
        return view;
    }
    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(getContext(), "user");
        deptId = (String) helper.getSharedPreference("deptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        HeadModel.getInstance().activityList(page, pagesize, deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
                StudentActivityList list = (StudentActivityList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<StudentActivityList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(getActivity());
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StudentActivityAdapter(list);
        recyclerview.setAdapter(adapter);
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
                        getList();
                        refreshLayout.finishRefreshing();
                    }
                }, 1000);
            }
            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = page + 1;
                        getList();
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<StudentActivityList.ListBean> list = adapter.getData();
                Intent intent = new Intent(getActivity(), StudentUnionDetailsActivity.class);
                intent.putExtra("activityId",list.get(position).getActivity_id());
                startActivity(intent);
            }
        });
    }

    /**获取上拉下拉  刷新数据*/
    public void getList() {
        HeadModel.getInstance().activityList(page, pagesize, deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
                StudentActivityList list = (StudentActivityList) object;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
