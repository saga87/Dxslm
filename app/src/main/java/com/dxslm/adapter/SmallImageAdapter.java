package com.dxslm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ywg on 2016/6/29.
 */
public class SmallImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mItemList;
    private LayoutInflater mInflater;
    private List<String> list = new ArrayList<>();

    public SmallImageAdapter(List<String> itemList,Context context) {
        this.mItemList = itemList;
        this.mContext = context;
        mInflater = LayoutInflater.from(MyApplication.getInstance());
    }

    @Override
    public int getCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_small_image, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv_item_small_image = convertView.findViewById(R.id.iv_item_small_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final String item = mItemList.get(position);
        String url =  UrlUtil.URL+item;
//        GlideUtil.getBitmapWH(url,viewHolder.iv_item_small_image,400,600);
        GlideUtil.getBitmap(url,viewHolder.iv_item_small_image);
        list.clear();
        for (int i = 0;i<mItemList.size();i++){
            list.add( UrlUtil.URL+ mItemList.get(i));
        }
        viewHolder.iv_item_small_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.showPhotos(list, (Activity) mContext,position);
            }
        });
        return convertView;
    }

    class ViewHolder {
        private ImageView iv_item_small_image;
    }

    public void refreshData(List<String> itemList) {
        if (mItemList.size() != 0 && mItemList != null) {
            mItemList.clear();
        }
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addData(List<String> itemList) {
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

}
