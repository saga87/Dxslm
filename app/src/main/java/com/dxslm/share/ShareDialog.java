package com.dxslm.share;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dxslm.R;
import com.dxslm.entity.Success;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.Util;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Created by renzhiqiang on 16/8/13.
 */
public class ShareDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private DisplayMetrics dm;

    /**
     * UI
     */
    private LinearLayout mWeixinLayout;
    private LinearLayout mWeixinMomentLayout;
    private LinearLayout mQQLayout;
    private LinearLayout mQZoneLayout;
    private LinearLayout mPinglun;
    private LinearLayout mShoucang;
    private TextView mCancelView;

    /**
     * share relative
     */
    private int mShareType; //指定分享类型
    private String mShareTitle; //指定分享内容标题
    private String mShareText; //指定分享内容文本
    private String mSharePhoto; //指定分享本地图片
    private String mShareTileUrl;
    private String mShareSiteUrl;
    private String mShareSite;
    private String mUrl;
    private String mResourceUrl;

    private boolean isShowDownload;

    /**
     * 收藏数据
     */
    private String type;
    private String content;
    private String userId;
    private String id;
    private String roleId;

    public ShareDialog(Context context) {
        super(context, R.style.SheetDialogStyle);
        mContext = context;
        dm = mContext.getResources().getDisplayMetrics();
        this.isShowDownload = false;
    }

    public ShareDialog(Context context,String type,String content,String userId,String id,String roleId) {
        super(context, R.style.SheetDialogStyle);
        mContext = context;
        dm = mContext.getResources().getDisplayMetrics();
        this.isShowDownload = false;

        this.type = type;
        this.content = content;
        this.userId = userId;
        this.id = id;
        this.roleId = roleId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share_layout);
        initView();
    }

    private void initView() {
        /**
         * 通过获取到dialog的window来控制dialog的宽高及位置
         */
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = dm.widthPixels; //设置宽度
        dialogWindow.setAttributes(lp);

        mWeixinLayout = (LinearLayout) findViewById(R.id.weixin_layout);
        mWeixinLayout.setOnClickListener(this);
        mWeixinMomentLayout = (LinearLayout) findViewById(R.id.moment_layout);
        mWeixinMomentLayout.setOnClickListener(this);
        mQQLayout = (LinearLayout) findViewById(R.id.qq_layout);
        mQQLayout.setOnClickListener(this);
        mQZoneLayout = (LinearLayout) findViewById(R.id.qzone_layout);
        mQZoneLayout.setOnClickListener(this);
        mCancelView = (TextView) findViewById(R.id.cancel_view);
        mCancelView.setOnClickListener(this);
        mShoucang = findViewById(R.id.shoucang_layout);
        mShoucang.setOnClickListener(this);
    }

    public void setResourceUrl(String resourceUrl) {
        mResourceUrl = resourceUrl;
    }

    public void setShareTitle(String title) {
        mShareTitle = title;
    }

    public void setImagePhoto(String photo) {
        mSharePhoto = photo;
    }

    public void setShareType(int type) {
        mShareType = type;
    }

    public void setShareSite(String site) {
        mShareSite = site;
    }

    public void setShareTitleUrl(String titleUrl) {
        mShareTileUrl = titleUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setShareSiteUrl(String siteUrl) {
        mShareSiteUrl = siteUrl;
    }

    public void setShareText(String text) {
        mShareText = text;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weixin_layout:
                shareData(ShareManager.PlatofrmType.WeChat);
                break;
            case R.id.moment_layout:
                shareData(ShareManager.PlatofrmType.WechatMoments);
                break;
            case R.id.qq_layout:
                shareData(ShareManager.PlatofrmType.QQ);
                break;
            case R.id.qzone_layout:
                shareData(ShareManager.PlatofrmType.QZone);
                break;
            case R.id.cancel_view:
                dismiss();
                break;
            case R.id.shoucang_layout:
                MyModel.getInstance().setCollect(userId, roleId, id, content, type, new ICallBack() {
                    @Override
                    public void succeed(Object object) {
                        Success success = (Success) object;
                        if(success.isSuccess()){
                           Util.showToast("保存成功");
                       //     Util.showToast(success.getMsg());
                            dismiss();
                        }else {
                            Util.showToast("保存失败"+success.getMsg());
                        }
                    }
                    @Override
                    public void error(Object object) {
                        Util.showToast("保存失败"+object.toString());
                    }
                });
                break;
        }
    }

    private PlatformActionListener mListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            //分享成功的回调
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
        }

        @Override
        public void onCancel(Platform platform, int i) {
            //取消分享的回调
        }
    };

    private void shareData(ShareManager.PlatofrmType platofrm) {
        ShareData mData = new ShareData();
//
//        if(mShareTitle==null){
//            mShareTitle = "标题";
//        }
//
//        if(mShareTileUrl==null){
//            mShareTileUrl = "http://sharesdk.cn";
//        }

        Platform.ShareParams params = new Platform.ShareParams();
        params.setShareType(mShareType);
        params.setTitle(mShareTitle);
        params.setTitleUrl(mShareTileUrl);
        params.setSite(mShareSite);
        params.setSiteUrl(mShareSiteUrl);
        params.setText(mShareText);
        params.setImagePath(mSharePhoto);
        params.setUrl(mUrl);
        mData.mPlatformType = platofrm;
        mData.mShareParams = params;
        ShareManager.getInstance().shareData(mData, mListener);
    }
}
