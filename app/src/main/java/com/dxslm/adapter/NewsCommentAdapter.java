package com.dxslm.adapter;

import android.app.Activity;
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
import com.dxslm.entity.news.NewCommentList;
import com.dxslm.entity.user.PersonMessage;
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
import com.dxslm.view.dialog.CommentDialog;
import com.liji.circleimageview.CircleImageView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Ywg on 2016/6/29.
 */
public class NewsCommentAdapter extends BaseAdapter {
    private final String roleId;
    private final String userId;
    private Context mContext;
    private List<NewCommentList.ListBean> mItemList;
    private LayoutInflater mInflater;
    private String newsId;
    private Handler handler;
    private CommentDialog dialog;
    private NewCommentList commenList;
    private Success success;
    private String isGly;
    private String deptId;


    public NewsCommentAdapter(Context context, List<NewCommentList.ListBean> itemList,Handler handler) {
        this.mContext = context;
        this.mItemList = itemList;
        this.handler = handler;
        mInflater = LayoutInflater.from(context);
        // Bundle bundle = getIntent().getExtras();
        SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        isGly = (String) helper.getSharedPreference("isGly", "0");
        deptId = (String) helper.getSharedPreference("orideptId", "");

        //   newsId = (String) bundle.get("newsId");
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
            //评论回复图片
            viewHolder.picture = convertView.findViewById(R.id.pinglun_img);
            //整体点击图片内容
            viewHolder.layout = convertView.findViewById(R.id.line_item_news_zongti);
            viewHolder.circleImageView = convertView.findViewById(R.id.iv_my_touxiang_stu_dainji);
            viewHolder.iv_item_comment_delete = convertView.findViewById(R.id.item_iv_news_delete);
            viewHolder.tv_item_huifu = convertView.findViewById(R.id.tv_item_comment_title_huifu);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final NewCommentList.ListBean item = mItemList.get(position);
        viewHolder.tv_item_comment_title.setText(item.getUsername());
        viewHolder.tv_item_comment_time.setText(DateUtil.getDateToString(item.getHnr_time(),"yyyy-MM-dd"));
        if (null==item.getHeadPic()||"".equals(item.getHeadPic())){
            GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,viewHolder.circleImageView);
        }else{
            String url = UrlUtil.URL+item.getHeadPic();
            GlideUtil.getIconBitmap(url,viewHolder.circleImageView);
        }

        if(item.getBusername()!=null&&!item.getBusername().equals("")){
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
            parseMessage(item.getHnr_content(),viewHolder.tv_item_comment_contact);
        }
//        parseMessage(item.getHnr_content(),viewHolder.tv_item_comment_contact);
        viewHolder.iv_item_comment_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (item.getHnr_user().equals(userId)) {
//                    deleteComment(item.getHnr_id());
//                }else {
//                    Util.showToast("这不是你的评论，无法删除" );
//                }
                if ("1".equals(isGly)){
                    if (deptId.equals(item.getDept_id())){
                        deleteComment(item.getHnr_id());
                    }else {
                        Util.showToast("无法删除他校评论" );
                    }
                }else if(item.getHnr_user().equals(userId)) {
                    deleteComment(item.getHnr_id());
                }else {
                    Util.showToast("这不是你的评论，无法删除" );
                }


            }
        });
        //点击头像
        viewHolder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userId.equals(item.getHnr_user())){
                    Intent intent = new Intent(mContext, PersonNewsActivity.class);
                    intent.putExtra("userId",item.getHnr_user());
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, OtherPersonActivity.class);
                    intent.putExtra("userId",item.getHnr_user());
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
                                    String bbrUset = item.getHnr_user();
                                    HeadModel.getInstance().newsComment(item.getNews_id(),roleId,userId,str,bbrUset,"",bbrUset, new ICallBack() {
                                        @Override
                                        public void succeed(Object object) {
                                            success = (Success) object;
                                            if(success.isSuccess()){
                                                HeadModel.getInstance().newsCommentList(item.getNews_id(), roleId, new ICallBack() {
                                                    public void succeed(Object object) {
                                                        commenList = (NewCommentList) object;
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

    private void deleteComment(String hnr_id) {
        HeadModel.getInstance().newsCommentDelete(hnr_id,roleId, new ICallBack() {
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


    class ViewHolder {
        private TextView tv_item_comment_title;
        private TextView tv_item_comment_time;
        private TextView tv_item_comment_contact;
        private CircleImageView circleImageView;
        private ImageView iv_item_comment_delete;
        private TextView tv_item_huifu;
        private ImageView picture;
        private LinearLayout layout;
    }

    public void refreshData(List<NewCommentList.ListBean> itemList) {
        if (mItemList.size() != 0 && mItemList != null) {
            mItemList.clear();
        }
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addData(List<NewCommentList.ListBean> itemList) {
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

}
