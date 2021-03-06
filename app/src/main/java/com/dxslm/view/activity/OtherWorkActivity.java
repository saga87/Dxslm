package com.dxslm.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.R;
import com.dxslm.adapter.LoseAdapter;
import com.dxslm.adapter.OtherWorkAdapter;
import com.dxslm.entity.lost.LoseList;
import com.dxslm.entity.otherwork.OtherWorkList;
import com.dxslm.model.ActivityModel;
import com.dxslm.model.ICallBack;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherWorkActivity extends AppCompatActivity {

    @Bind(R.id.iv_other_work_back)
    ImageView ivOtherWorkBack;
    @Bind(R.id.iv_other_work_ddd)
    ImageView ivOtherWorkDdd;
    @Bind(R.id.recyclerview_other_work)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_other_work)
    TwinklingRefreshLayout refresh;
    private int page = 1;
    private int pagesize = 20;
    private String deptId;
    private OtherWorkAdapter adapter;
    private String roleId;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_work);
        ButterKnife.bind(this);
        //获取数据
        getData();
    }


    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        deptId = (String) helper.getSharedPreference("deptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        ActivityModel.getInstance().otherWorkList(page, pagesize,userId, deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
                OtherWorkList list = (OtherWorkList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<OtherWorkList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(this);
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OtherWorkAdapter(list,this);
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
        ActivityModel.getInstance().otherWorkList(page, pagesize,userId, deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
                OtherWorkList list = (OtherWorkList) object;
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


    @OnClick({R.id.iv_other_work_back, R.id.iv_other_work_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_other_work_back:
                finish();
                break;
            case R.id.iv_other_work_ddd:
                break;
        }
    }
}
