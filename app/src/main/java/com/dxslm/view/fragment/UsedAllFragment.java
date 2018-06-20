package com.dxslm.view.fragment;

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
import com.dxslm.adapter.LoveAdapter;
import com.dxslm.adapter.UsedAdapter;
import com.dxslm.entity.love.LoveList;
import com.dxslm.entity.used.UsedList;
import com.dxslm.model.ActivityModel;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
import com.dxslm.view.BaseFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by fxn on 2017/12/26.
 */

public class UsedAllFragment extends BaseFragment {

    @Bind(R.id.recyclerview_used_all)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_used_all)
    TwinklingRefreshLayout refresh;
    private int page = 1;
    private int pagesize = 20;
    private String deptId;
    private UsedAdapter adapter;
    private String roleId;
    private String userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_used, null);
        ButterKnife.bind(this, view);

        //获取数据
        getData();
        return view;
    }

    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(getContext(), "user");
        deptId = (String) helper.getSharedPreference("deptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        ActivityModel.getInstance().usedList(page, pagesize,userId, deptId, roleId,"", new ICallBack() {
            public void succeed(Object object) {
                UsedList list = (UsedList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<UsedList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(getActivity());
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new UsedAdapter(list,getActivity());
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
    }
    /**获取上拉下拉  刷新数据*/
    public void getList() {
        ActivityModel.getInstance().usedList(page, pagesize,userId,  deptId, roleId,"", new ICallBack() {
            public void succeed(Object object) {
                UsedList list = (UsedList) object;
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
