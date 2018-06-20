package com.dxslm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.R;
import com.dxslm.adapter.IssueAdapter;
import com.dxslm.adapter.LoveAdapter;
import com.dxslm.entity.forum.ForumList;
import com.dxslm.entity.issue.IssueList;
import com.dxslm.entity.love.LoveList;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
import com.dxslm.view.BaseActivity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IssueActivity extends BaseActivity {

    @Bind(R.id.iv_issue_back)
    ImageView ivIssueBack;
    @Bind(R.id.iv_issue_ddd)
    ImageView ivIssueDdd;
    @Bind(R.id.recyclerview_issue)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_issue)
    TwinklingRefreshLayout refresh;
    private int page = 1;
    private int pagesize = 20;
    private String deptId;
    private IssueAdapter adapter;
    private String roleId;
    private String userId;

    private Handler handler= new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Util.showToast("删除成功");
                setList();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        ButterKnife.bind(this);
        String content = getIntent().getStringExtra("content");
        Log.e("IssueActivity","===================onCreate  content:"+content);
        //获取数据
        getData();

    }


    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        deptId = (String) helper.getSharedPreference("orideptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        MyModel.getInstance().issueList(page,pagesize,roleId, userId, new ICallBack() {
            public void succeed(Object object) {
                IssueList list = (IssueList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<IssueList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(this);
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IssueAdapter(list,handler);
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
                List<IssueList.ListBean> list = adapter.getData();
                Intent intent = new Intent(IssueActivity.this, IssueDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
    /**获取上拉下拉  刷新数据*/
    public void getList() {
        MyModel.getInstance().issueList(page,pagesize,roleId, userId, new ICallBack() {
            public void succeed(Object object) {
                IssueList list = (IssueList) object;
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
    /**刷新数据*/
    public void setList() {
        pagesize = page * pagesize;
        page = 1;
        MyModel.getInstance().issueList(page,pagesize,roleId, userId, new ICallBack() {
            public void succeed(Object object) {
                IssueList list = (IssueList) object;
                adapter.replaceData(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    @OnClick({R.id.iv_issue_back, R.id.iv_issue_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_issue_back:
                finish();
                break;
            case R.id.iv_issue_ddd:
                break;
        }
    }
}
