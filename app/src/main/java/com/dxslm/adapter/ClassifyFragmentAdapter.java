package com.dxslm.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.ClassifyOne;
import com.dxslm.util.LocalBroadcastUtil;

import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class ClassifyFragmentAdapter extends BaseQuickAdapter<ClassifyOne.ListBean, BaseViewHolder> {
    private Context context;
    public ClassifyFragmentAdapter(Context context, List data) {
        super(R.layout.item_fragment_classify, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ClassifyOne.ListBean item) {
        helper.setText(R.id.tv_item_fracla_title, item.getDept_name());
        helper.getView(R.id.tv_item_fracla_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalBroadcastUtil.sendBroadcast("school",item);
            }
        });
    }
}
