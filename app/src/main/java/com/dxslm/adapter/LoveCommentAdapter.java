package com.dxslm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.entity.forum.ForumCommentList;
import com.dxslm.util.DateUtil;

import java.util.List;


/**
 * Created by Ywg on 2016/6/29.
 */
public class LoveCommentAdapter extends BaseAdapter {

    private Context mContext;
    private List<ForumCommentList.ListBean> mItemList;
    private LayoutInflater mInflater;

    public LoveCommentAdapter(Context context, List<ForumCommentList.ListBean> itemList) {
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
            convertView = mInflater.inflate(R.layout.item_news_comment, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_item_comment_title = convertView.findViewById(R.id.tv_item_comment_title);
            viewHolder.tv_item_comment_time = convertView.findViewById(R.id.tv_item_comment_time);
            viewHolder.tv_item_comment_contact = convertView.findViewById(R.id.tv_item_comment_contact);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ForumCommentList.ListBean item = mItemList.get(position);
        viewHolder.tv_item_comment_title.setText(item.getUsername());
        viewHolder.tv_item_comment_time.setText(DateUtil.getDateToString(item.getFr_time(),"yyyy-MM-dd"));
        viewHolder.tv_item_comment_contact.setText(item.getFr_content());
        return convertView;
    }

    class ViewHolder {
        private TextView tv_item_comment_title;
        private TextView tv_item_comment_time;
        private TextView tv_item_comment_contact;
    }

    public void refreshData(List<ForumCommentList.ListBean> itemList) {
        if (mItemList.size() != 0 && mItemList != null) {
            mItemList.clear();
        }
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addData(List<ForumCommentList.ListBean> itemList) {
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

}
