package com.dxslm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.chwl.ChwlGoods;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class ChwlDetailsAdapter extends BaseQuickAdapter<ChwlGoods.ListBean , BaseViewHolder> {
    private Context context;
    public ChwlDetailsAdapter(List data, Context context) {
        super(R.layout.item_chwl_details, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ChwlGoods.ListBean item) {
        helper.setText(R.id.item_tv_chwl_details_name, item.getMenu_name());
        helper.setText(R.id.item_tv_chwl_details_price, "¥"+item.getMenu_price());
        GlideUtil.getBitmap(UrlUtil.URL+item.getPic_url(), (ImageView) helper.getView(R.id.item_iv_chwl_details_image));
        //显示图片
        helper.getView(R.id.item_iv_chwl_details_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = new ArrayList<>();
                list.add( UrlUtil.URL+ item.getPic_url());
                Util.showPhotos(list, (Activity) mContext,0);
            }
        });
    }

}
