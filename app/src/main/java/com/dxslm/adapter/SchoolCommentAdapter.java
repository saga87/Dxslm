package com.dxslm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.entity.school.SchoolCommentList;
import com.dxslm.model.DialogCallback;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.dxslm.view.activity.OtherPersonActivity;
import com.dxslm.view.activity.PersonNewsActivity;
import com.dxslm.view.activity.PersonSchoolActivity;
import com.dxslm.view.dialog.CommentDialog;
import com.liji.circleimageview.CircleImageView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Ywg on 2016/6/29.
 */
public class SchoolCommentAdapter extends BaseAdapter {

    private Context mContext;
    private List<SchoolCommentList.ListBean> mItemList;
    private LayoutInflater mInflater;
    private Handler handler;
    private final String roleId;
    private CommentDialog dialog;
    private Success success;
    private SchoolCommentList commenList;
    private  final String userId;
    private String isGly;
    private String deptId;


    public SchoolCommentAdapter(Context context, List<SchoolCommentList.ListBean> itemList,Handler handler) {
        this.mContext = context;
        this.mItemList = itemList;
        mInflater = LayoutInflater.from(context);
        this.handler = handler;
        SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        isGly = (String) helper.getSharedPreference("isGly", "0");
        deptId = (String) helper.getSharedPreference("orideptId", "");
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
            viewHolder.iv_delete = convertView.findViewById(R.id.item_iv_news_delete);
            viewHolder.circleImageView=convertView.findViewById(R.id.iv_my_touxiang_stu_dainji);
            viewHolder.tv_item_huifu = convertView.findViewById(R.id.tv_item_comment_title_huifu);
            //评论回复图片
            viewHolder.picture = convertView.findViewById(R.id.pinglun_img);
            //整体点击图片内容
            viewHolder.layout = convertView.findViewById(R.id.line_item_news_zongti);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final SchoolCommentList.ListBean item = mItemList.get(position);
        viewHolder.tv_item_comment_title.setText(item.getUsername());
        viewHolder.tv_item_comment_time.setText(DateUtil.getDateToString(item.getCrr_time(),"yyyy-MM-dd"));
        //  viewHolder.tv_item_comment_contact.setText(item.getCrr_content());
        if (null==item.getHeadPic()||"".equals(item.getHeadPic())){
            GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,viewHolder.circleImageView);
        }else{
            String url = UrlUtil.URL+item.getHeadPic();
            GlideUtil.getIconBitmap(url,viewHolder.circleImageView);
        }
        if (item.getBusername()!=null&&!item.getBusername().equals("")){
            viewHolder.tv_item_huifu.setText("回复："+item.getBusername());
        }else {
            viewHolder.tv_item_huifu.setText("");
        }
        if (item.getPic_url()!=null&&!item.getPic_url().equals("")){
            viewHolder.tv_item_comment_contact.setVisibility(View.GONE);
            viewHolder.picture.setVisibility(View.VISIBLE);
            GlideUtil.getBitmap(UrlUtil.URL+item.getPic_url(),viewHolder.picture);

        }else {
            viewHolder.tv_item_comment_contact.setVisibility(View.VISIBLE);
            viewHolder.picture.setVisibility(View.GONE);
            parseMessage(item.getCrr_content(),viewHolder.tv_item_comment_contact);
        }


        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(item.getCrr_user().equals(userId)) {
//                    HeadModel.getInstance().aroundSchoolCommentDelete(roleId, item.getCrr_id(), new ICallBack() {
//                        @Override
//                        public void succeed(Object object) {
//                            Success success = (Success) object;
//                            if (success.isSuccess()) {
//                                handler.sendEmptyMessage(1);
//                            }
//                        }
//
//                        @Override
//                        public void error(Object object) {
//
//                        }
//                    });
//                }else {
//                    Util.showToast("这不是你的评论，无法删除" );
//                }

                if ("1".equals(isGly)){
                    if (deptId.equals(item.getDept_id())){
                        deleteComment(item.getCrr_id());
                    }else {
                        Util.showToast("无法删除他校评论" );
                    }
                }else if(item.getCrr_user().equals(userId)) {
                    deleteComment(item.getCrr_id());
                }else {
                    Util.showToast("这不是你的评论，无法删除" );
                }

            }
        });
        viewHolder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userId.equals(item.getCrr_user())){
                    Intent intent = new Intent(mContext, PersonNewsActivity.class);
                    intent.putExtra("userId",item.getCrr_user());
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, OtherPersonActivity.class);
                    intent.putExtra("userId",item.getCrr_user());
                    mContext.startActivity(intent);
                }

            }
        });
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new CommentDialog(mContext, item.getUsername(),
                        new DialogCallback() {
                            @Override
                            public void succeed(Object object) {
                                String str = (String) object;
                                if(!object.equals("")){
                                    String bbrUset = item.getCrr_user();
                                    HeadModel.getInstance().schoolComment(item.getInfo_id(), roleId,userId,str,bbrUset,"", bbrUset,new ICallBack() {
                                        @Override
                                        public void succeed(Object object) {
                                            success = (Success) object;
//                                            Log.e("+++++++++++++++",success+"1111");
                                            if(success.isSuccess()){
                                                HeadModel.getInstance().schoolCommentList(item.getInfo_id(), roleId, new ICallBack() {
                                                    public void succeed(Object object) {
                                                        commenList = (SchoolCommentList) object;
                                                        refreshData(commenList.getList());
                                                        Util.showToast("回复成功");
                                                    }
                                                    public void error(Object object) {
                                                        Util.showToast("评论失败"+success.getMsg());
                                                    }
                                                });
                                                dialog.dismiss();
                                                Util.closeFocus(mContext);
//
                                            }else {
                                                Util.showToast("发送失败");
                                            }
                                        }
                                        @Override
                                        public void error(Object object) {}
                                    });
                                }else {
                                    Util.showToast("评论不能为空");
                                }
                            }
                        });
                dialog.show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        private TextView tv_item_comment_title;
        private TextView tv_item_comment_time;
        private TextView tv_item_comment_contact;
        private ImageView iv_delete;
        private CircleImageView circleImageView;
        private TextView tv_item_huifu;
        private ImageView picture;
        private LinearLayout layout;
    }

    private void deleteComment(String crr_id) {
        HeadModel.getInstance().aroundSchoolCommentDelete(roleId, crr_id, new ICallBack() {
            @Override
            public void succeed(Object object) {
                Success success = (Success) object;
                if (success.isSuccess()) {
                    handler.sendEmptyMessage(1);
                }
            }
            @Override
            public void error(Object object) {

            }
        });
    }

    private void parseMessage(String s, TextView mMessageTextView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(s);
        Pattern pattern = Pattern.compile("\\[(\\S+?)\\]");//匹配[xx]的字符串
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String group = matcher.group();
            group = group.substring(1, group.length() - 1);
//            Log.e("399", group);
            int drawableId = mContext.getResources().getIdentifier(group, "drawable", mContext.getPackageName());
            Drawable drawable = mContext.getResources().getDrawable(drawableId);
            int size = (int) (35 * mContext.getResources().getDisplayMetrics().density);
            drawable.setBounds(0, 0, size, size);
            ImageSpan imageSpan = new ImageSpan(drawable);
            spannableStringBuilder.setSpan(imageSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mMessageTextView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
    }
    public void refreshData(List<SchoolCommentList.ListBean> itemList) {
        if (mItemList.size() != 0 && mItemList != null) {
            mItemList.clear();
        }
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addData(List<SchoolCommentList.ListBean> itemList) {
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

}
