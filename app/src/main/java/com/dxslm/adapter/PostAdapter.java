package com.dxslm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.posts.PostList;
import com.dxslm.ui.GridViewForScrollview;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.view.activity.OtherPersonActivity;
import com.dxslm.view.activity.PersonNewsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class PostAdapter extends BaseQuickAdapter<PostList.ListBean , BaseViewHolder> {
    //private List<String> imgs = new ArrayList<>();
    private Context context;
    private String userId;
    public PostAdapter(List data, Context ctx) {
//        super(R.layout.item_post, data);
        super(R.layout.item_npost, data);
        context = ctx;
        SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
        userId = (String) helper.getSharedPreference("userId", "");
    }

    @Override
    protected void convert(BaseViewHolder helper, final PostList.ListBean item) {
        //imgs.clear();
        String pic = item.getHeadpic();
        if (null==pic||"".equals(pic.trim())){
            GlideUtil.getDefaultIconBitmap(R.drawable.mydefault, (ImageView) helper.getView(R.id.iv_touxiang_npost));
        }else {
            GlideUtil.getIconBitmap(UrlUtil.URL+pic, (ImageView) helper.getView(R.id.iv_touxiang_npost));
        }

        helper.getView(R.id.iv_touxiang_npost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userId.equals(item.getInput_user())){
                    Intent intent = new Intent(mContext, PersonNewsActivity.class);
                    intent.putExtra("userId",item.getInput_user());
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, OtherPersonActivity.class);
                    intent.putExtra("userId",item.getInput_user());
                    mContext.startActivity(intent);
                }
            }
        });


        helper.setText(R.id.item_tv_post_title, item.getPost_title());
        helper.setText(R.id.item_tv_post_contact, item.getUser_name());

        if (item.getDept_name()==null||"".equals(item.getDept_name())){
            helper.setText(R.id.item_tv_post_time, DateUtil.getDateToString(item.getInput_time(),"yyyy-MM-dd"));
        }else{
            helper.setText(R.id.item_tv_post_time, DateUtil.getDateToString(item.getInput_time(),"yyyy-MM-dd")+" | "
                    +item.getDept_name());
        }


        GridViewForScrollview gv = helper.getView(R.id.item_gv_post);
        String str = item.getPic_url();
        List<String> imgs = new ArrayList<>();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i]);
            }
            if (imgs.size() > 0) {
                SmallImageAdapter adapter = new SmallImageAdapter(imgs,context);
                gv.setAdapter(adapter);
            } else {
                imgs.clear();
//                SmallImageAdapter adapter = new SmallImageAdapter(imgs,context);
//                gv.setAdapter(adapter);
            }
        }else {
            imgs.clear();
            SmallImageAdapter adapter = new SmallImageAdapter(imgs,context);
            gv.setAdapter(adapter);
        }
    }
}
