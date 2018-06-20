package com.dxslm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.dxslm.R;
import com.dxslm.entity.ClassifyOne;

import java.util.List;


public class ClassifyAdapter extends BaseAdapter {

    private Context context;
    private List<ClassifyOne.ListBean> listinfos;

    public ClassifyAdapter(Context context, List<ClassifyOne.ListBean> listinfos){
        this.context =context;
        this.listinfos = listinfos;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listinfos.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listinfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        convertView = LayoutInflater.from(context).inflate(R.layout.item_classify, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_item_classify);
        ClassifyOne.ListBean persionInfo = listinfos.get(position);
        tv.setText(persionInfo.getDept_name());
        if (persionInfo.isChick()) {
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        return convertView;
    }
}
