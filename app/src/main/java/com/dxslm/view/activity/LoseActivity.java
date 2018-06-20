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
import com.dxslm.adapter.LoveAdapter;
import com.dxslm.entity.lost.LoseList;
import com.dxslm.entity.love.LoveList;
import com.dxslm.model.ActivityModel;
import com.dxslm.model.HeadModel;
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
/**
 * 失物招领
 */
public class LoseActivity extends AppCompatActivity {

    @Bind(R.id.iv_lost_back)
    ImageView ivLostBack;
    @Bind(R.id.iv_lost_ddd)
    ImageView ivLostDdd;
    @Bind(R.id.recyclerview_lost)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_lost)
    TwinklingRefreshLayout refresh;
    private int page = 1;
    private int pagesize = 20;
    private String deptId;
    private LoseAdapter adapter;
    private String roleId;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        ButterKnife.bind(this);
        //获取数据
        getData();
    }


    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        deptId = (String) helper.getSharedPreference("deptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        ActivityModel.getInstance().lostList(page, pagesize,userId, deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
                LoseList list = (LoseList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<LoseList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(this);
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LoseAdapter(list,this);
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
        ActivityModel.getInstance().lostList(page, pagesize,userId, deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
                LoseList list = (LoseList) object;
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

    @OnClick({R.id.iv_lost_back, R.id.iv_lost_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_lost_back:
                finish();
                break;
            case R.id.iv_lost_ddd:
                break;
        }
    }
}
