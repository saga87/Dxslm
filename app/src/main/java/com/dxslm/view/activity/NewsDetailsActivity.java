package com.dxslm.view.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dxslm.R;
import com.dxslm.adapter.EmojiAdapter;
import com.dxslm.adapter.NewsCommentAdapter;
import com.dxslm.adapter.ImageAdapter;
import com.dxslm.entity.Success;
import com.dxslm.entity.news.NewCommentList;
import com.dxslm.entity.news.NewDetails;
import com.dxslm.entity.person.Pinglun;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.share.ShareDialog;
import com.dxslm.ui.CameraBottomMenu;
import com.dxslm.ui.ListViewForScrollView;
import com.dxslm.util.DateUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
/**
 * 热点详情
 */
public class NewsDetailsActivity extends TakePhotoActivity {

    @Bind(R.id.iv_news_details_back)
    ImageView ivNewsDetailsBack;
    @Bind(R.id.iv_news_details_ddd)
    ImageView ivNewsDetailsDdd;
    @Bind(R.id.tv_news_details_title)
    TextView tvNewsDetailsTitle;
    @Bind(R.id.tv_news_details_time)
    TextView tvNewsDetailsTime;
    @Bind(R.id.tv_news_details_contact)
    TextView tvNewsDetailsContact;
    @Bind(R.id.lv_news_details)
    ListViewForScrollView lvNewsDetails;
    @Bind(R.id.lv_news_details_comment)
    ListViewForScrollView lvNewsDetailsComment;
    @Bind(R.id.et_news_details_comment)
    EditText etNewsDetailsComment;
    // PictureEditText etNewsDetailsComment;
    @Bind(R.id.btn_news_details_confirm)
    Button btnNewsDetailsConfirm;
    private List<String> imgs = new ArrayList<>();
    //   ArrayList<String> urls = new ArrayList<>();
    private NewDetails details;
    private String deptId;
    private String newsId;
    private NewCommentList commenList;
    private NewsCommentAdapter adapter;
    private String userId;
    private Success success;
    private String roleId;
    private ImageButton mFaceImageButton;

    private ViewPager mEmojiViewPager;
    private LinearLayout mPointContain;
    private RelativeLayout mFaceContainRelativeLayout;
    private boolean isFaceShow = false;


    private ImageView img_pic;
    private CameraBottomMenu menuWindow;
    private List<String> urls = new ArrayList<>();

    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private List<Pinglun> pingluns;

    private String inputId;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                HeadModel.getInstance().newsCommentList(newsId, roleId, new ICallBack() {
                    public void succeed(Object object) {
                        commenList = (NewCommentList) object;
                        adapter.refreshData(commenList.getList());
                        Util.showToast("删除成功");
                    }
                    public void error(Object object) {
                        Util.showToast("删除失败"+success.getMsg());
                    }
                });
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        img_pic = (ImageView) findViewById(R.id.img_tupian_picture );
        //etNewsDetailsComment = (PictureEditText) findViewById(R.id.et_news_details_comment);
        setView();

        //获取数据
        Bundle bundle = getIntent().getExtras();
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        deptId = (String) helper.getSharedPreference("deptId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        newsId = (String) bundle.get("newsId");
        setModel();


        initData();
        initEmoji();
        mFaceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOrHideFace();
                hideSoftInput();
            }
        });
        img_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuWindow = new CameraBottomMenu(NewsDetailsActivity.this, clickListener);
                menuWindow.show();
            }
        });

    }

    private void setModel() {
        HeadModel.getInstance().newsDetails(newsId, deptId, new ICallBack() {
            public void succeed(Object object) {
                details = (NewDetails) object;
                tvNewsDetailsTitle.setText(details.getList().get(0).getNews_title());
                tvNewsDetailsTime.setText(DateUtil.getDateToString(details.getList().get(0).getInput_time(), "yyyy年MM月dd日"));
                tvNewsDetailsContact.setText(details.getList().get(0).getNews_content());

                inputId = details.getList().get(0).getInput_user();

                //GlideUtil.getIconBitmap(UrlUtil.URL+item.getHeadpic(), (ImageView) helper.getView(R.id.item_civ_love_icon));

                String str = details.getList().get(0).getPic_url();
                String[] strs = str.split(",");
                for (int i = 0, len = strs.length; i < len; i++) {
                    imgs.add(strs[i].toString());
                }
                if (imgs.size() > 0) {
                    lvNewsDetails.setAdapter(new ImageAdapter(NewsDetailsActivity.this, imgs));

                }

            }

            public void error(Object object) {
            }
        });
        HeadModel.getInstance().newsCommentList(newsId, roleId, new ICallBack() {
            public void succeed(Object object) {
                commenList = (NewCommentList) object;
                adapter = new NewsCommentAdapter(NewsDetailsActivity.this, commenList.getList(),handler);
                lvNewsDetailsComment.setAdapter(adapter);

            }
            public void error(Object object) {

            }
        });

    }

    /**
     * 分享sdk
     */
    public void testShareSdk() {
        String content = details.getList().get(0).getNews_content();
        ShareDialog dialog = new ShareDialog(this,"1",content,userId,newsId,roleId);
        dialog.setShareType(Platform.SHARE_TEXT);
        dialog.setShareTitle(details.getList().get(0).getNews_title());
        dialog.setShareText(details.getList().get(0).getNews_content()+"\n来自：大学生联盟");
        dialog.show();
    }

    @OnClick({R.id.iv_news_details_back, R.id.iv_news_details_ddd, R.id.btn_news_details_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_news_details_back:
                finish();
                break;
            case R.id.iv_news_details_ddd:
                testShareSdk();
                break;
            case R.id.btn_news_details_confirm:
                if(Util.isFastClick()){
                    return;
                }
                //发送评论
                String etComment = etNewsDetailsComment.getText().toString();
                if(!etComment.equals("")){
                    etNewsDetailsComment.setText("");
                    mFaceContainRelativeLayout.setVisibility(View.GONE);
                    HeadModel.getInstance().newsComment(newsId, roleId,userId, etComment,"","",inputId,new ICallBack() {
                        public void succeed(Object object) {
                            success = (Success) object;
                            //请求是否成功
                            if(success.isSuccess()){
                                HeadModel.getInstance().newsCommentList(newsId, roleId, new ICallBack() {
                                    public void succeed(Object object) {
                                        commenList = (NewCommentList) object;
                                        adapter.refreshData(commenList.getList());
                                        lvNewsDetailsComment.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                                        lvNewsDetailsComment.setStackFromBottom(true);
                                        Util.showToast("评论成功");
                                    }
                                    public void error(Object object) {
                                        Util.showToast("评论失败"+success.getMsg());
                                    }
                                });
                            }else {
                                Util.showToast("评论失败"+success.getMsg());
                            }
                        }
                        public void error(Object object) {
                        }
                    });
                }else {
                    Util.showToast("评论内容不能为空");
                }
                break;
        }
    }
    /**
     * 初始化表情
     */
    private void initEmoji() {
        mEmojiViewPager.setAdapter(new EmojiAdapter(this, etNewsDetailsComment, mEmojiViewPager, mPointContain));
    }
    private void showOrHideFace() {
        if (isFaceShow) {
            mFaceContainRelativeLayout.setVisibility(View.GONE);
        } else {
            mFaceContainRelativeLayout.setVisibility(View.VISIBLE);

        }
        isFaceShow = !isFaceShow;
    }
    private void setView(){
        mFaceImageButton = (ImageButton) findViewById(R.id.ib_face);
        mEmojiViewPager = (ViewPager) findViewById(R.id.vp_emoji);
        mPointContain = (LinearLayout) findViewById(R.id.ll_point_contain);
        mFaceContainRelativeLayout = (RelativeLayout) findViewById(R.id.rl_face_contain);

    }
    private void hideSoftInput() {
        //1.得到InputMethodManager对象
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //2.调用hideSoftInputFromWindow方法隐藏软键盘
        imm.hideSoftInputFromWindow(etNewsDetailsComment.getWindowToken(), 0); //强制隐藏键盘

    }
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
          urls.clear();
        String iconPath = result.getImage().getOriginalPath();
        urls.add(iconPath);
        MyModel.getInstance().personIcon(urls, new ICallBack() {
            @Override
            public void succeed(Object object) {
                pingluns = (List<Pinglun>) object;
                String url = pingluns.get(0).getFileurl();
                HeadModel.getInstance().newsComment(newsId, roleId,userId, "","",url,inputId,new ICallBack() {
                    public void succeed(Object object) {
                        success = (Success) object;
                        //请求是否成功
                        if(success.isSuccess()){
                            HeadModel.getInstance().newsCommentList(newsId, roleId, new ICallBack() {
                                public void succeed(Object object) {
                                    commenList = (NewCommentList) object;
                                    adapter.refreshData(commenList.getList());
                                    lvNewsDetailsComment.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                                    lvNewsDetailsComment.setStackFromBottom(true);
                                    Util.showToast("评论成功");
                                }
                                public void error(Object object) {
                                    Util.showToast("评论失败"+success.getMsg());
                                }
                            });
                        }else {
                            Util.showToast("评论失败"+success.getMsg());
                        }
                    }
                    public void error(Object object) {
                    }
                });


            }

            @Override
            public void error(Object object) {

            }
        });

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(NewsDetailsActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    private void initData() {
        ////获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, true);  //设置为需要压缩
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    imageUri = getImageCropUri();
                    //拍照并裁剪
//                    takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                    //仅仅拍照不裁剪
                    takePhoto.onPickFromCapture(imageUri);
                    break;
                case R.id.btn2:
                    imageUri = getImageCropUri();
                    //从相册中选取图片并裁剪
//                    takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                    //从相册中选取不裁剪
                    takePhoto.onPickFromGallery();
                    break;
            }
        }
    };
}
