package com.dxslm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.news.NewsList;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class TestAdapter extends BaseQuickAdapter<NewsList.ListBean, BaseViewHolder> {

//    private List<String> imgs = new ArrayList<>();
    private Context context;

    public TestAdapter(List data,Context context) {
  //      super(R.layout.item_photo, data);
        super(R.layout.item_frontpage, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsList.ListBean item) {
        List<String> imgs = new ArrayList<>();
        String str = item.getPic_url();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i]);
            }
            if (imgs.size() > 0) {
                helper.getView(R.id.iv_pic).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_info).setVisibility(View.VISIBLE);
                helper.getView(R.id.fp_news_contact).setVisibility(View.VISIBLE);
                helper.getView(R.id.fp_news_time).setVisibility(View.VISIBLE);
                String url = UrlUtil.URL + imgs.get(0);
                GlideUtil.getBitmap(url, (ImageView) helper.getView(R.id.iv_pic));
                helper.setText(R.id.tv_info, item.getNews_title());
                helper.setText(R.id.fp_news_contact, item.getNews_content().trim());
                helper.setText(R.id.fp_news_time, DateUtil.getDateToString(item.getInput_time(),"yyyy-MM-dd"));
            } else {
                imgs.clear();
                helper.getView(R.id.iv_pic).setVisibility(View.GONE);
                helper.getView(R.id.tv_info).setVisibility(View.GONE);
                helper.getView(R.id.fp_news_contact).setVisibility(View.GONE);
                helper.getView(R.id.fp_news_time).setVisibility(View.GONE);
            }
        }else {
            imgs.clear();
            helper.getView(R.id.iv_pic).setVisibility(View.GONE);
            helper.getView(R.id.tv_info).setVisibility(View.GONE);
            helper.getView(R.id.fp_news_contact).setVisibility(View.GONE);
            helper.getView(R.id.fp_news_time).setVisibility(View.GONE);
        }

    }
}
