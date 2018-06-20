package com.dxslm.adapter;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.entity.issue.IssueList;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class IssueAdapter extends BaseQuickAdapter<IssueList.ListBean , BaseViewHolder> {
    List<String> imgs = new ArrayList<>();
    private final String roleId;
    private Handler handler;

    public IssueAdapter(List data,Handler handler) {
        super(R.layout.item_issue, data);
        this.handler = handler;
        SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
        roleId = (String) helper.getSharedPreference("roleId", "");
    }

    @Override
    protected void convert(final BaseViewHolder helper, final IssueList.ListBean item) {
        imgs.clear();

        String title = item.getTltle();
        if (title==null||"".equals(title)){
            helper.setText(R.id.item_tv_issue_title, item.getContent());
        }else{
            helper.setText(R.id.item_tv_issue_title, title);
        }

        helper.setText(R.id.item_tv_issue_time, DateUtil.getDateToString(item.getTime(),"yyyy-MM-dd"));

        String str = item.getPic_url();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i]);
            }
            if (imgs.size() > 0) {
                String url = UrlUtil.URL + imgs.get(0);
                GlideUtil.getBitmap(url, (ImageView) helper.getView(R.id.item_iv_issue_image));
            } else {
                helper.getView(R.id.item_iv_issue_image).setVisibility(View.GONE);
            }
        }else {
            helper.getView(R.id.item_iv_issue_image).setVisibility(View.GONE);
        }
        helper.getView(R.id.item_iv_issue_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyModel.getInstance().issueDelete(roleId, item.getType(), item.getId(), new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success s = (Success) object;
                        if(s.isSuccess()){
                            handler.sendEmptyMessage(1);
                        }else {
                            Util.showToast("删除失败"+s.getMsg());
                        }
                    }
                    @Override
                    public void error(Object object) {

                    }
                });
            }
        });
    }
}
