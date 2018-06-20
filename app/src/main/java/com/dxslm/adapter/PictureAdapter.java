package com.dxslm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lp on 2018/6/19.
 */

public class PictureAdapter extends BaseAdapter{

    private Context mContext;
    private List<String> mItemList;
    private LayoutInflater mInflater;
    private List<String> list = new ArrayList<>();
    private AlertDialog.Builder normalDialog;

    private Handler mHandler;


    public PictureAdapter(List<String> itemList,Context context,Handler handler) {
        this.mItemList = itemList;
        this.mContext = context;
        mHandler = handler;
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

        viewHolder.iv_item_small_image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showNormalDialog(position);
                return true;
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

    private void showNormalDialog(final int position) {
        normalDialog = new AlertDialog.Builder(mContext);
        normalDialog.setTitle("删除");
        normalDialog.setMessage("您是否要删除这张图片");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyModel.getInstance().delPics(mItemList.get(position), new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success s = (Success) object;
                        if (s.isSuccess()){
                            mHandler.sendEmptyMessage(1);
                        }else{
                            Util.showToast("删除失败"+s.getMsg());
                        }
                    }

                    @Override
                    public void error(Object object) {

                    }
                });
            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                normalDialog.create();
            }
        });
        normalDialog.show();
    }

}
