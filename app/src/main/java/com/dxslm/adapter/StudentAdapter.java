package com.dxslm.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.student.StudentList;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class StudentAdapter extends BaseQuickAdapter<StudentList.ListBean , BaseViewHolder> {
    List<String> imgs = new ArrayList<>();
    public StudentAdapter(List data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentList.ListBean item) {
        imgs.clear();
        helper.setText(R.id.item_tv_news_title, item.getArticle_title());
        helper.setText(R.id.n_news_contact, item.getArticle_content().trim());
        helper.setText(R.id.item_tv_news_contact, item.getUser_name()+"  评论:"+item.getReply_num()+"  "+
                DateUtil.getDateToString(item.getInput_time(),"yyyy-MM-dd"));
        String str = item.getPic_url();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i]);
            }
            if (imgs.size() > 0) {
                String url = UrlUtil.URL + imgs.get(0);
                GlideUtil.getBitmap(url, (ImageView) helper.getView(R.id.item_iv_news_image));
            } else {
            }
        }else {
        }
    }
}
