package com.dxslm.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.student.StudentSponsorList;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/9.
 */

public class StudentSuponsorAdapter extends BaseQuickAdapter<StudentSponsorList.ListBean , BaseViewHolder> {
    List<String> imgs = new ArrayList<>();
    public StudentSuponsorAdapter(List data) {
        super(R.layout.item_student_union, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentSponsorList.ListBean item) {
        imgs.clear();
        helper.setText(R.id.item_tv_student_union_title, item.getDept_name());
        helper.setText(R.id.item_tv_student_union_contact, item.getActivity_name());
        helper.setText(R.id.item_tv_student_union_time,  DateUtil.getDateToString(item.getInput_time(),"yyyy-MM-dd"));
        String str = item.getPic_url();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i].toString());
            }
            if (imgs.size() > 0) {
                String url = UrlUtil.URL + imgs.get(0);
                GlideUtil.getBitmap(url, (ImageView) helper.getView(R.id.item_iv_student_union_image));
            } else {
            }
        }else {
        }
    }
}
