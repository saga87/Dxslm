package com.dxslm.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.entity.love.DzNum;
import com.dxslm.entity.otherwork.OtherWorkCommentList;
import com.dxslm.entity.otherwork.OtherWorkList;
import com.dxslm.model.ActivityModel;
import com.dxslm.model.DialogCallback;
import com.dxslm.model.ICallBack;
import com.dxslm.ui.GridViewForScrollview;
import com.dxslm.ui.ListViewForScrollView;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.dxslm.view.dialog.CommentDialog;

import java.util.ArrayList;
import java.util.List;


public class OtherWorkAdapter extends BaseQuickAdapter<OtherWorkList.ListBean , BaseViewHolder> {
    private final String deptId;
    private final String roleId;
    private final String userId;
    private boolean isVisibility = true;
    private OtherWorkCommentList user;
    private OtherWorkCommentAdapter adapter;
    private Context context;
    private CommentDialog dialog;


    private ImageButton mFaceImageButton;
    private EditText  etcomment;
    private ViewPager mEmojiViewPager;
    private LinearLayout mPointContain;
    private RelativeLayout mFaceContainRelativeLayout;
    private    boolean isFaceShow =false;
    private    EmojiAdapter emojiAdapter;
    public OtherWorkAdapter(List data, Context context) {
        super(R.layout.item_love, data);
        this.context = context;
        SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
        deptId = (String) helper.getSharedPreference("orideptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
    }

    @Override
    protected void convert(final BaseViewHolder helper, final OtherWorkList.ListBean item) {
//        GlideUtil.getIconBitmap(UrlUtil.URL+item.getHeadpic(), (ImageView) helper.getView(R.id.item_civ_love_icon));
        String pic_url = item.getHeadpic();
        if (null==pic_url||"".equals(pic_url)){
            GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,(ImageView) helper.getView(R.id.item_civ_love_icon));
        }else {
            GlideUtil.getIconBitmap(UrlUtil.URL+pic_url, (ImageView) helper.getView(R.id.item_civ_love_icon));
        }
        helper.setText(R.id.item_tv_love_name, item.getUsernc());
        helper.setText(R.id.item_tv_love_time,  DateUtil.getDateToString(item.getInput_time(),"yyyy-MM-dd"));
        helper.setText(R.id.item_tv_love_context, item.getPtj_content());
        helper.getView(R.id.item_tv_love_add).setVisibility(View.GONE);
        helper.setText(R.id.item_tv_love_comment, item.getReply_num()+"");
        helper.setText(R.id.item_tv_love_dz, item.getPtj_dz()+"");

        final LinearLayout commentzone = helper.getView(R.id.commentzone);
        commentzone.setVisibility(View.GONE);

        GridViewForScrollview gv = helper.getView(R.id.item_lv_love);
        final ListViewForScrollView lv = helper.getView(R.id.item_lv_love_comment);
        String str = item.getPic_url();
        //清空以前的图片
        List<String> imgs = new ArrayList<>();
        if(str!=null&&!str.equals("")) {
            String[] strs = str.split(",");
            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(strs[i].toString());
            }
            if (imgs.size() > 0) {
                //显示图片
                SmallImageAdapter adapter = new SmallImageAdapter(imgs,context);
                gv.setAdapter(adapter);
            } else {
                imgs.clear();
            }
        }else {
            //显示图片
            imgs.clear();
            SmallImageAdapter adapter = new SmallImageAdapter(imgs,context);
            gv.setAdapter(adapter);
        }
        //显示评论
        helper.getView(R.id.item_ll_love_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lv.getVisibility()==View.GONE){
                    commentzone.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    ActivityModel.getInstance().otherWorkCommentList(item.getPtj_id(), roleId, new ICallBack() {
                        @Override
                        public void succeed(Object object) {
                            user = (OtherWorkCommentList) object;
                            adapter = new OtherWorkCommentAdapter(mContext,user.getList());
                            lv.setAdapter(adapter);
                        }
                        @Override
                        public void error(Object object) {}
                    });
                 //   isVisibility = false;
                }else {
                    commentzone.setVisibility(View.GONE);
                    lv.setVisibility(View.GONE);
                  //  isVisibility = true;
                }
            }
        });
        mFaceImageButton = helper.getView(R.id.ib_face_love);
        final RelativeLayout rl = helper.getView(R.id.rl_face_contain);
        mEmojiViewPager  =helper.getView(R.id.vp_emoji);
        etcomment=helper.getView(R.id.item_et_love_comment);
        mPointContain=helper.getView(R.id.ll_point_contain);
        emojiAdapter = new EmojiAdapter(context, etcomment, mEmojiViewPager, mPointContain);
        mEmojiViewPager.setAdapter(emojiAdapter);
        mFaceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //多条不能用true和false判断
                RelativeLayout rl = helper.getView(R.id.rl_face_contain);
                if (rl.getVisibility() == View.VISIBLE) {
                    rl.setVisibility(View.GONE);
                } else {
                    rl.setVisibility(View.VISIBLE);
                }


            }
        });
        final EditText etcomment = helper.getView(R.id.item_et_love_comment);
        //发表评论
        helper.getView(R.id.item_btn_love_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = etcomment.getText().toString();
                ActivityModel.getInstance().otherWorkComment(roleId, item.getPtj_id(),comment,userId,"",item.getInput_user(), new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success success = (Success) object;
                        if(success.isSuccess()){
                            etcomment.setText("");
                            rl.setVisibility(View.GONE);
                            commentList(item,lv,helper);
                            Util.showToast("评论成功");
                        }else {
                            Util.showToast("发送失败");
                        }
                    }
                    @Override
                    public void error(Object object) {}
                });
            }
        });
        //回复评论
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                dialog = new CommentDialog(context, user.getList().get(i).getUsername(), new DialogCallback() {
                            @Override
                            public void succeed(Object object) {
                                String str = (String) object;
                                if(!str.equals("")){
                                    String bbrUset = user.getList().get(i).getPtr_user();
                                    ActivityModel.getInstance().otherWorkComment(roleId, item.getPtj_id() ,str ,userId ,bbrUset ,bbrUset, new ICallBack() {
                                        @Override
                                        public void succeed(Object object) {
                                            Success success = (Success) object;
                                            if(success.isSuccess()){
                                                commentList(item,lv,helper);
                                                dialog.dismiss();
                                                Util.closeFocus(context);
                                                Util.showToast("回复成功");
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
        //是否显示点赞图标
        if(item.getDz()==1){
            helper.getView(R.id.item_iv_love_dz).setBackgroundResource(R.drawable.checkdianzan);
        }else {
            helper.getView(R.id.item_iv_love_dz).setBackgroundResource(R.drawable.dianzan);
        }
        //点赞
        helper.getView(R.id.item_ll_love_dz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityModel.getInstance().otherWorkDz(roleId, item.getPtj_id(),userId, new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success success = (Success) object;
                        if(success.isSuccess()){
                            Util.showToast(success.getMsg());
                            helper.getView(R.id.item_iv_love_dz).setBackgroundResource(R.drawable.checkdianzan);
                            ActivityModel.getInstance().otherWorkDzNum(roleId, item.getPtj_id(), new ICallBack() {
                                @Override
                                public void succeed(Object object) {
                                    DzNum num = (DzNum) object;
                                    helper.setText(R.id.item_tv_love_dz, num.getDzs()+"");
                                }
                                @Override
                                public void error(Object object) {
                                }
                            });
                        }else {
                            Util.showToast(success.getMsg());
                            helper.getView(R.id.item_iv_love_dz).setBackgroundResource(R.drawable.checkdianzan);
                        }
                    }
                    @Override
                    public void error(Object object) {}
                });
            }
        });
    }

    /**
     * 更新评论
     */
    private void commentList(OtherWorkList.ListBean item, final ListViewForScrollView lv, final BaseViewHolder helper) {
        ActivityModel.getInstance().otherWorkCommentList(item.getPtj_id(), roleId, new ICallBack() {
            @Override
            public void succeed(Object object) {
                user = (OtherWorkCommentList) object;
                if(adapter!=null) {
                    adapter.refreshData(user.getList());
                }else {
                    adapter = new OtherWorkCommentAdapter(mContext,user.getList());
                    lv.setAdapter(adapter);
                }
                lv.setVisibility(View.VISIBLE);
                isVisibility = false;
                helper.setText(R.id.item_tv_love_comment, user.getList().size() + "");
            }
            @Override
            public void error(Object object) {}
        });
    }
}
