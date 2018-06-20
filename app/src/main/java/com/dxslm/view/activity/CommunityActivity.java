package com.dxslm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.R;
import com.dxslm.adapter.CommunityAdapter;
import com.dxslm.adapter.NewsAdapter;
import com.dxslm.entity.community.CommunityList;
import com.dxslm.entity.community.ComunityList;
import com.dxslm.entity.news.NewsList;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
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
/**
 * 校园社团
 */
public class CommunityActivity extends BaseActivity {

    @Bind(R.id.iv_community_back)
    ImageView ivCommunityBack;
    @Bind(R.id.iv_community_ddd)
    ImageView ivCommunityDdd;
    @Bind(R.id.recyclerview_community)
    RecyclerView recyclerview;
    @Bind(R.id.refresh_community)
    TwinklingRefreshLayout refresh;
    private int page = 1;
    private int pagesize = 20;
    private String roleId;
    private String deptId;
    private CommunityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        ButterKnife.bind(this);
        //获取数据
        getData();
    }

    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        roleId = (String) helper.getSharedPreference("roleId", "");
        deptId = (String) helper.getSharedPreference("deptId", "");
        HeadModel.getInstance().communityList(page, pagesize,deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
             //   CommunityList list = (CommunityList) object;
                ComunityList list = (ComunityList) object;
                setRecyclerAdapter(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    public void setRecyclerAdapter(List<ComunityList.ListBean> list) {
        ProgressLayout headerView = new ProgressLayout(this);
        refresh.setHeaderView(headerView);
        refresh.setMaxHeadHeight(100);
        refresh.setOverScrollBottomShow(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommunityAdapter(list);
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
                        getList(page);
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
                        getList(page);
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ComunityList.ListBean> list = adapter.getData();
                Intent intent = new Intent(CommunityActivity.this, CommunityDetailsActivity.class);
                intent.putExtra("communityId",list.get(position).getCommunity_id());
                startActivity(intent);
            }
        });
    }

    /**获取上拉下拉  刷新数据*/
    public void getList(int page1) {
        HeadModel.getInstance().communityList(page, pagesize,deptId, roleId, new ICallBack() {
            public void succeed(Object object) {
                ComunityList list = (ComunityList) object;
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

    @OnClick({R.id.iv_community_back, R.id.iv_community_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_community_back:
                finish();
                break;
            case R.id.iv_community_ddd:
                break;
        }
    }
}
