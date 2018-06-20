package com.dxslm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.chwl.ChwlList;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;

import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class ChwlAdapter extends BaseQuickAdapter<ChwlList.ListBean , BaseViewHolder> {
    private Context context;
    public ChwlAdapter(List data,Context context) {
        super(R.layout.item_chwl, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChwlList.ListBean item) {
        helper.setText(R.id.item_tv_chwl_name, "店面："+item.getSeller_zhaopai());
        helper.setText(R.id.item_tv_chwl_qjxf, "平均消费："+item.getSeller_perfee()+"元/人");
        helper.setText(R.id.item_tv_chwl_add, "地址："+item.getSeller_address());
        GlideUtil.getBitmap(UrlUtil.URL+item.getPic_url(), (ImageView) helper.getView(R.id.item_iv_chwl_image));
    }
}
