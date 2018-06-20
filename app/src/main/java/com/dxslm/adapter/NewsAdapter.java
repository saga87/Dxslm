package com.dxslm.adapter;

import android.content.Context;
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


public class NewsAdapter extends BaseQuickAdapter<NewsList.ListBean , BaseViewHolder> {
    private List<String> imgs = new ArrayList<>();
    private Context context;
    List<String> list = new ArrayList<>();
    public NewsAdapter(List data,Context context) {
        super(R.layout.item_news, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsList.ListBean item) {
        imgs.clear();
        helper.setText(R.id.item_tv_news_title, item.getNews_title());
        helper.setText(R.id.n_news_contact, item.getNews_content().trim());
        helper.setText(R.id.item_tv_news_contact, item.getUser_name()+"  评论:"+item.getReply_num()+"  "+
                DateUtil.getDateToString(item.getInput_time(),"yyyy-MM-dd"));

        String str = item.getPic_url();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i]);
            }
            if (imgs.size() > 0) {
                final String url = UrlUtil.URL + imgs.get(0);
                GlideUtil.getBitmap(url, (ImageView) helper.getView(R.id.item_iv_news_image));
            } else {
            }
        }else {
        }

    }
}
