package com.dxslm.view.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.R;
import com.dxslm.adapter.IssueAdapter;
import com.dxslm.entity.issue.IssueList;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.Util;
import com.dxslm.view.activity.IssueActivity;
import com.dxslm.view.activity.IssueDetailsActivity;
import com.dxslm.view.activity.OtherPersonActivity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 别人发布
 */
public class OtherCollectFragment extends Fragment {
    @Bind(R.id.otheruser_recyclerview_issue)
    RecyclerView otheruserRecyclerviewIssue;
    @Bind(R.id.otheruser_refresh_issue)
    TwinklingRefreshLayout otheruserRefreshIssue;
    private String userId;
    private View view;
    private int page = 1;
    private int pagesize = 20;
    private IssueAdapter adapter;
    private Context myContext;

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

    public OtherCollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_other_collect, container, false);
        ButterKnife.bind(this, view);
        getData();
        return view;
    }

    private void getData() {
        MyModel.getInstance().issueList(page,pagesize,null, userId, new ICallBack() {
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
        ProgressLayout headerView = new ProgressLayout(myContext);
        otheruserRefreshIssue.setHeaderView(headerView);
        otheruserRefreshIssue.setMaxHeadHeight(100);
        otheruserRefreshIssue.setOverScrollBottomShow(false);
        otheruserRecyclerviewIssue.setLayoutManager(new LinearLayoutManager(myContext));
        adapter = new IssueAdapter(list,handler);
        otheruserRecyclerviewIssue.setAdapter(adapter);
        //动画效果
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //动画默认只执行一次,如果想重复执行可设置
        adapter.isFirstOnly(false);

        otheruserRefreshIssue.setOnRefreshListener(new RefreshListenerAdapter() {
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
                Intent intent = new Intent(((OtherPersonActivity) myContext), IssueDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
    /**获取上拉下拉  刷新数据*/
    public void getList() {
        MyModel.getInstance().issueList(page,pagesize,null, userId, new ICallBack() {
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
        MyModel.getInstance().issueList(page,pagesize,null, userId, new ICallBack() {
            public void succeed(Object object) {
                IssueList list = (IssueList) object;
                adapter.replaceData(list.getList());
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }



    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        myContext = context;
        userId = ((OtherPersonActivity) context).getUserId();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
