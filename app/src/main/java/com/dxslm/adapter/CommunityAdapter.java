package com.dxslm.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.community.ComunityList;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;


public class CommunityAdapter extends BaseQuickAdapter<ComunityList.ListBean , BaseViewHolder> {
    List<String> imgs = new ArrayList<>();
    public CommunityAdapter(List data) {
        super(R.layout.item_community, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ComunityList.ListBean item) {
        imgs.clear();
        helper.setText(R.id.item_tv_community_title, item.getCommunity_name());
        helper.setText(R.id.item_tv_community_contact, item.getCommunity_instruction());
        String str = item.getPic_url();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i].toString());
            }
            if (imgs.size() > 0) {
                String url = UrlUtil.URL + imgs.get(0);
                GlideUtil.getBitmap(url, (ImageView) helper.getView(R.id.item_iv_community_image));
            } else {
            }
        }else {
        }

    }
}
