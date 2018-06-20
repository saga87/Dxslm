package com.dxslm.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class IssueImageAdapter extends BaseQuickAdapter<String , BaseViewHolder> {

    public IssueImageAdapter(List data) {
        super(R.layout.item_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        ImageView iv = helper.getView(R.id.iv_item_image);
        GlideUtil.getBitmap(UrlUtil.URL+item, iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = new ArrayList<>();
                list.add( UrlUtil.URL+ item);
                Util.showPhotos(list, (Activity) mContext,0);
            }
        });
    }
}
