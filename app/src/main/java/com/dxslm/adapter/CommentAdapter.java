package com.dxslm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.entity.love.LoveCommentList;
import com.dxslm.model.DialogCallback;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
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
public class CommentAdapter extends BaseAdapter {
    // private final String roleId;
    private Context mContext;
    private List<LoveCommentList.ListBean> mItemList;
    private LayoutInflater mInflater;
    // private Handler handler;
    private CommentDialog dialog;
    private Success success;
    private LoveCommentList commentList;
    private  final String roleId;
    private  final String userId;

    private String isGly;
    private String deptId;

    public CommentAdapter(Context context, List<LoveCommentList.ListBean> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        mInflater = LayoutInflater.from(context);
        //  this.handler = handler;
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
            convertView = mInflater.inflate(R.layout.item_comment, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_item_comment_title = convertView.findViewById(R.id.tv_item_commentall_title);
            viewHolder.tv_item_commentall_two = convertView.findViewById(R.id.tv_item_commentall_two);
            viewHolder.tv_item_comment_contect = convertView.findViewById(R.id.tv_item_commentall_time);
            viewHolder.item_iv_biaobai_delete = convertView.findViewById(R.id.item_iv_biaobai_delete);
            viewHolder.circleImageView = convertView.findViewById(R.id.iv_my_touxiang_love_dainji);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final LoveCommentList.ListBean item = mItemList.get(position);
        viewHolder.tv_item_comment_title.setText(item.getUsername()+":");
        if(item.getBusername()!=null&&!item.getBusername().equals("")) {
            viewHolder.tv_item_commentall_two.setText("回复:" + item.getBusername());
        }else {
            viewHolder.tv_item_commentall_two.setText("");
        }
        if (null==item.getHeadPic()||"".equals(item.getHeadPic())){
            GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,viewHolder.circleImageView);
        }else{
            String url = UrlUtil.URL+item.getHeadPic();
            GlideUtil.getIconBitmap(url,viewHolder.circleImageView);
        }
        parseMessage(item.getBbr_content(),viewHolder.tv_item_comment_contect);

        viewHolder.tv_item_comment_contect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new CommentDialog(mContext, item.getUsername(),
                        new DialogCallback() {
                            @Override
                            public void succeed(Object object) {
                                String str = (String) object;
                                if(!object.equals("")){
                                    String bbrUset = item.getBbr_user();
                                    HeadModel.getInstance().loveComment(roleId, item.getBb_id() ,str ,userId ,bbrUset ,bbrUset, new ICallBack() {
                                        @Override
                                        public void succeed(Object object) {
                                            success = (Success) object;
                                            if(success.isSuccess()){
                                                HeadModel.getInstance().loveCommentList(item.getBb_id(), roleId, new ICallBack() {
                                                    public void succeed(Object object) {
                                                        commentList = (LoveCommentList) object;
                                                        refreshData(commentList.getList());

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
        viewHolder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userId.equals(item.getBbr_user())){
                    Intent intent = new Intent(mContext, PersonNewsActivity.class);
                    intent.putExtra("userId",item.getBbr_user());
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, OtherPersonActivity.class);
                    intent.putExtra("userId",item.getBbr_user());
                    mContext.startActivity(intent);
                }
            }
        });


        viewHolder.item_iv_biaobai_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("1".equals(isGly)){
                    if (deptId.equals(item.getDept_id())){
                        deleteComment(item);
                    }else {
                        Util.showToast("无法删除他校评论" );
                    }
                }else if(item.getBbr_user().equals(userId)) {
                    deleteComment(item);
                }else {
                    Util.showToast("这不是你的评论，无法删除" );
                }
            }
        });


        return convertView;
    }

    private void deleteComment(final LoveCommentList.ListBean item) {
        HeadModel.getInstance().commentDelete(roleId, item.getBbr_id(), new ICallBack() {
            @Override
            public void succeed(Object object) {
                Success success = (Success) object;
                if (success.isSuccess()) {
                    HeadModel.getInstance().loveCommentList(item.getBb_id(), roleId, new ICallBack() {
                        public void succeed(Object object) {
                            commentList = (LoveCommentList) object;
                            refreshData(commentList.getList());

                            Util.showToast("删除成功");
                        }
                        public void error(Object object) {
                            Util.showToast("删除加载失败");
                        }
                    });
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
        private TextView tv_item_comment_contect;
        private TextView tv_item_commentall_two;
        private ImageView item_iv_biaobai_delete;
        private CircleImageView circleImageView;
    }

    public void refreshData(List<LoveCommentList.ListBean> itemList) {
        if (mItemList.size() != 0 && mItemList != null) {
            mItemList.clear();
        }
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addData(List<LoveCommentList.ListBean> itemList) {
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

}
