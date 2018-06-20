package com.dxslm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dxslm.R;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ywg on 2016/6/29.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mItemList;
    private LayoutInflater mInflater;
    private List<String> list = new ArrayList<>();

    public ImageAdapter(Context context, List<String> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.item_image, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv_item_image = convertView.findViewById(R.id.iv_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final String item = mItemList.get(position);
        final String url =  UrlUtil.URL+item;
        GlideUtil.getBitmapFitCenter(url,viewHolder.iv_item_image);

        list.clear();
        for (int i = 0;i<mItemList.size();i++){
            list.add( UrlUtil.URL+ mItemList.get(i));
        }
        viewHolder.iv_item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.showPhotos(list, (Activity) mContext,position);
            }
        });
        return convertView;
    }

    class ViewHolder {
        private ImageView iv_item_image;
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
