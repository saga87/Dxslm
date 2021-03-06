package com.dxslm.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.R;
import com.dxslm.entity.student.StudentActivityList;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;


public class StudentActivityAdapter extends BaseQuickAdapter<StudentActivityList.ListBean , BaseViewHolder> {
    List<String> imgs = new ArrayList<>();
    public StudentActivityAdapter(List data) {
        super(R.layout.item_student_union, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentActivityList.ListBean item) {
        imgs.clear();
        helper.setText(R.id.item_tv_student_union_title, item.getActivity_theme());
        helper.setText(R.id.item_tv_student_union_contact, item.getActivity_content());
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
