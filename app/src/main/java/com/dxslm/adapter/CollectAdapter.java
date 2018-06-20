package com.dxslm.adapter;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.entity.collect.CollectList;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;

import java.util.List;


public class CollectAdapter extends BaseQuickAdapter<CollectList.ListBean , BaseViewHolder> {
    private String roleId;
    private Handler handler;
    public CollectAdapter(List data, String roleId, Handler handler) {
        super(R.layout.item_collect, data);
        this.roleId = roleId;
        this.handler = handler;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CollectList.ListBean item) {
//        GlideUtil.getIconBitmap(UrlUtil.URL+item.getHeadpic(), (ImageView) helper.getView(R.id.item_civ_collect_icon));
        String pic_url = item.getHeadpic();
        if (null==pic_url||"".equals(pic_url)){
            GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,(ImageView) helper.getView(R.id.item_civ_collect_icon));
        }else {
            GlideUtil.getIconBitmap(UrlUtil.URL+pic_url, (ImageView) helper.getView(R.id.item_civ_collect_icon));
        }
        helper.setText(R.id.item_tv_collect_name, item.getUsernc());
        helper.setText(R.id.item_tv_collect_tianshu, item.getDays()+"天前");
        helper.setText(R.id.item_tv_collect_context, item.getCollect_content());
        ImageView delete = helper.getView(R.id.item_iv_collect_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyModel.getInstance().deleteCollect(roleId, item.getCollect_id(), new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success success = (Success) object;
                        if(success.isSuccess()){
                            handler.sendEmptyMessage(1);
                        }else {
                            Util.showToast("删除失败"+success.getMsg());
                        }
                    }
                    @Override
                    public void error(Object object) {
                        Util.showToast("删除失败"+object.toString());
                    }
                });
            }
        });
    }
}
