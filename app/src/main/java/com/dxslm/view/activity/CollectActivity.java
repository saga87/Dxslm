package com.dxslm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.R;
import com.dxslm.adapter.CollectAdapter;
import com.dxslm.adapter.IssueAdapter;
import com.dxslm.entity.collect.CollectList;
import com.dxslm.entity.issue.IssueList;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectActivity extends AppCompatActivity {

    @Bind(R.id.iv_collect_back)
    ImageView ivCollectBack;
    @Bind(R.id.iv_collect_ddd)
    ImageView ivCollectDdd;
    @Bind(R.id.recyclerview_collect)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_collect)
    TwinklingRefreshLayout refresh;
    private int page = 1;
    private int pagesize = 20;
    private String deptId;
    private CollectAdapter adapter;
    private String roleId;
    private String userId;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Util.showToast("删除成功");
                replace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        //获取数据
        getData();
    }


    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        deptId = (String) helper.getSharedPreference("orideptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        MyModel.getInstance().collectList(page,pagesize,roleId, userId, new ICallBack() {
            public void succeed(Object object) {
                CollectList list = (CollectList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<CollectList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(this);
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CollectAdapter(list,roleId,handler);
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
                        page = 20;
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
                List<CollectList.ListBean> list = adapter.getData();
                if(list.get(position).getCollect_type().equals("1")) {
                    Intent intent = new Intent(CollectActivity.this, NewsDetailsActivity.class);
                    intent.putExtra("newsId",list.get(position).getCt_id());
                    startActivity(intent);
                }else if(list.get(position).getCollect_type().equals("2")){
                    Intent intent = new Intent(CollectActivity.this, StudentDetailsActivity.class);
                    intent.putExtra("articleId",list.get(position).getCt_id());
                    startActivity(intent);
                }else if(list.get(position).getCollect_type().equals("3")){
                    Intent intent = new Intent(CollectActivity.this, SchoolDetailsActivity.class);
                    intent.putExtra("infoId",list.get(position).getCt_id());
                    startActivity(intent);
                }else if(list.get(position).getCollect_type().equals("4")){
                    Intent intent = new Intent(CollectActivity.this, ForumDetailsActivity.class);
                    intent.putExtra("forumId",list.get(position).getCt_id());
                    startActivity(intent);
                }else if(list.get(position).getCollect_type().equals("5")){
                    Intent intent = new Intent(CollectActivity.this, PostDetailsActivity.class);
                    intent.putExtra("postId",list.get(position).getCt_id());
                    startActivity(intent);
                }
//                else if(list.get(position).getCollect_type().equals("6")){
//                    Intent intent = new Intent(CollectActivity.this, NewsDetailsActivity.class);
//                    intent.putExtra("newsId",list.get(position).getCt_id());
//                    startActivity(intent);
//                }
            }
        });
    }
    /**获取上拉下拉  刷新数据*/
    public void getList() {
        MyModel.getInstance().collectList(page,pagesize,roleId, userId, new ICallBack() {
            public void succeed(Object object) {
                CollectList list = (CollectList) object;
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
    public void replace() {
        pagesize = page * pagesize;
        page = 1;
        MyModel.getInstance().collectList(page,pagesize,roleId, userId, new ICallBack() {
            public void succeed(Object object) {
                CollectList list = (CollectList) object;
                adapter.replaceData(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }


    @OnClick({R.id.iv_collect_back, R.id.iv_collect_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_collect_back:
                finish();
                break;
            case R.id.iv_collect_ddd:
                break;
        }
    }
}
