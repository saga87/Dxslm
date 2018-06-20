package com.dxslm.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
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
import com.dxslm.adapter.ImageAdapter;
import com.dxslm.adapter.PostCommentAdapter;
import com.dxslm.entity.Success;
import com.dxslm.entity.person.Pinglun;
import com.dxslm.entity.posts.PostCommentList;
import com.dxslm.entity.posts.PostDetails;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.share.ShareDialog;
import com.dxslm.ui.CameraBottomMenu;
import com.dxslm.ui.ListViewForScrollView;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.liji.circleimageview.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;

/**
 * 高校互动详情
 */
public class PostDetailsActivity extends TakePhotoActivity {

    @Bind(R.id.iv_post_details_back)
    ImageView ivPostDetailsBack;
    @Bind(R.id.iv_post_details_ddd)
    ImageView ivPostDetailsDdd;
    @Bind(R.id.tv_post_details_title)
    TextView tvPostDetailsTitle;
    @Bind(R.id.tv_post_details_contact)
    TextView tvPostDetailsContact;
    @Bind(R.id.lv_post_details)
    ListViewForScrollView lvPostDetails;
    @Bind(R.id.lv_post_details_comment)
    ListViewForScrollView lvPostDetailsComment;
    @Bind(R.id.et_post_details_comment)
    EditText etPostDetailsComment;
    @Bind(R.id.btn_post_details_confirm)
    Button btnPostDetailsConfirm;
    @Bind(R.id.iv_touxiang_post_detail)
    CircleImageView ivTouxiangPostDetail;
    @Bind(R.id.item_tv_post_detail_school)
    TextView itemTvPostDetailSchool;
    @Bind(R.id.item_tv_post_detail_contact)
    TextView itemTvPostDetailContact;
    @Bind(R.id.ib_face_post)
    ImageButton ibFacePost;
    @Bind(R.id.img_tupian_picture_post)
    ImageView imgTupianPicturePost;
    @Bind(R.id.vp_emoji)
    ViewPager vpEmoji;
    @Bind(R.id.ll_point_contain)
    LinearLayout llPointContain;
    @Bind(R.id.rl_face_contain)
    RelativeLayout rlFaceContain;
    private String deptId;
    private List<String> imgs = new ArrayList<>();
    //  ArrayList<String> urls = new ArrayList<>();
    private PostDetails details;
    private String postId;
    private String userId;
    private String roleId;
    private PostCommentList commenList;
    private PostCommentAdapter adapter;
    private Success success;

    private String inputId;


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
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                HeadModel.getInstance().psotCommentList(postId, roleId, new ICallBack() {
                    public void succeed(Object object) {
                        commenList = (PostCommentList) object;
                        adapter.refreshData(commenList.getList());
                        Util.showToast("删除成功");
                    }

                    public void error(Object object) {
                        Util.showToast("删除失败" + success.getMsg());
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);
        img_pic = (ImageView) findViewById(R.id.img_tupian_picture_post);
        setView();

        //获取数据
        Bundle bundle = getIntent().getExtras();
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        userId = (String) helper.getSharedPreference("userId", "");
        deptId = (String) helper.getSharedPreference("orideptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        postId = (String) bundle.get("postId");
        setModel();
        initEmoji();
        initData();
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
                menuWindow = new CameraBottomMenu(PostDetailsActivity.this, clickListener);
                menuWindow.show();
            }
        });
    }

    private void setModel() {
//        HeadModel.getInstance().psotDetails(deptId, postId, new ICallBack() {
        HeadModel.getInstance().psotDetails(roleId, postId, new ICallBack() {
            public void succeed(Object object) {
                try {
                    details = (PostDetails) object;

                    String pic = details.getList().get(0).getHeadpic();
                    if (null==pic||"".equals(pic.trim())){
                        GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,ivTouxiangPostDetail);
                    }else {
                        GlideUtil.getIconBitmap(UrlUtil.URL+pic, ivTouxiangPostDetail);
                    }

                    ivTouxiangPostDetail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (userId.equals(details.getList().get(0).getInput_user())){
                                Intent intent = new Intent(PostDetailsActivity.this, PersonNewsActivity.class);
                                intent.putExtra("userId",details.getList().get(0).getInput_user());
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(PostDetailsActivity.this, OtherPersonActivity.class);
                                intent.putExtra("userId",details.getList().get(0).getInput_user());
                                startActivity(intent);
                            }
                        }
                    });

//                    ivTouxiangPostDetail.setText(details.getList().get(0).getPost_title());
                    tvPostDetailsTitle.setText(details.getList().get(0).getPost_title());
                    itemTvPostDetailContact.setText(details.getList().get(0).getUser_name());


                    if (details.getList().get(0).getDept_name()==null||"".equals(details.getList().get(0).getDept_name())){
                        itemTvPostDetailSchool.setText(DateUtil.getDateToString(details.getList().get(0).getInput_time(), "yyyy年MM月dd日"));
                    }else{
                        itemTvPostDetailSchool.setText(DateUtil.getDateToString(details.getList().get(0).getInput_time(), "yyyy年MM月dd日")
                                +" | "+details.getList().get(0).getDept_name() );
                    }
                    tvPostDetailsContact.setText(details.getList().get(0).getPost_content());
                    inputId = details.getList().get(0).getInput_user();
                    String str = details.getList().get(0).getPic_url();
                    String[] strs = str.split(",");
                    for (int i = 0, len = strs.length; i < len; i++) {
                        imgs.add(strs[i].toString());
                    }
                    if (imgs.size() > 0) {
                        lvPostDetails.setAdapter(new ImageAdapter(PostDetailsActivity.this, imgs));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void error(Object object) {
            }
        });
        HeadModel.getInstance().psotCommentList(postId, roleId, new ICallBack() {
            public void succeed(Object object) {
                commenList = (PostCommentList) object;
                adapter = new PostCommentAdapter(PostDetailsActivity.this, commenList.getList(), handler);
                lvPostDetailsComment.setAdapter(adapter);
            }

            public void error(Object object) {
            }
        });
    }

    /**
     * 分享sdk
     */
    public void testShareSdk() {
        String content = details.getList().get(0).getPost_content();
        ShareDialog dialog = new ShareDialog(this, "5", content, userId, postId, roleId);
        dialog.setShareType(Platform.SHARE_TEXT);
        dialog.setShareTitle(details.getList().get(0).getPost_title());
        dialog.setShareText(details.getList().get(0).getPost_content() + "\n来自：大学生联盟");
        dialog.show();
    }

    @OnClick({R.id.iv_post_details_back, R.id.iv_post_details_ddd, R.id.btn_post_details_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_post_details_back:
                finish();
                break;
            case R.id.iv_post_details_ddd:
                testShareSdk();
                break;
            case R.id.btn_post_details_confirm:
                //发送评论
                String etComment = etPostDetailsComment.getText().toString();
                if (!etComment.equals("")) {
                    etPostDetailsComment.setText("");
                    mFaceContainRelativeLayout.setVisibility(View.GONE);
                    HeadModel.getInstance().psotComment(postId, roleId, userId, etComment, "", "", inputId, new ICallBack() {
                        public void succeed(Object object) {
                            success = (Success) object;
                            //请求是否成功
                            if (success.isSuccess()) {
                                HeadModel.getInstance().psotCommentList(postId, roleId, new ICallBack() {
                                    public void succeed(Object object) {
                                        commenList = (PostCommentList) object;
                                        adapter.refreshData(commenList.getList());
                                        Util.showToast("评论成功");
                                    }

                                    public void error(Object object) {
                                        Util.showToast("评论失败" + success.getMsg());
                                    }
                                });
                            } else {
                                Util.showToast("评论失败" + success.getMsg());
                            }
                        }

                        public void error(Object object) {
                        }
                    });
                }
                break;
        }
    }

    /**
     * 初始化表情
     */
    private void initEmoji() {
        mEmojiViewPager.setAdapter(new EmojiAdapter(this, etPostDetailsComment, mEmojiViewPager, mPointContain));
    }

    private void showOrHideFace() {
        if (isFaceShow) {
            mFaceContainRelativeLayout.setVisibility(View.GONE);
        } else {
            mFaceContainRelativeLayout.setVisibility(View.VISIBLE);

        }
        isFaceShow = !isFaceShow;
    }

    private void hideSoftInput() {
        //1.得到InputMethodManager对象
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //2.调用hideSoftInputFromWindow方法隐藏软键盘
        imm.hideSoftInputFromWindow(etPostDetailsComment.getWindowToken(), 0); //强制隐藏键盘
    }

    private void setView() {
        mFaceImageButton = (ImageButton) findViewById(R.id.ib_face_post);

        mEmojiViewPager = (ViewPager) findViewById(R.id.vp_emoji);
        mPointContain = (LinearLayout) findViewById(R.id.ll_point_contain);
        mFaceContainRelativeLayout = (RelativeLayout) findViewById(R.id.rl_face_contain);
        //     textView = (TextView) findViewById(R.id.tv_news);
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

                HeadModel.getInstance().psotComment(postId, roleId, userId, "", "", url, inputId, new ICallBack() {
                    public void succeed(Object object) {
                        success = (Success) object;
                        //请求是否成功
                        if (success.isSuccess()) {
                            HeadModel.getInstance().psotCommentList(postId, roleId, new ICallBack() {
                                public void succeed(Object object) {
                                    commenList = (PostCommentList) object;
                                    adapter.refreshData(commenList.getList());
                                    lvPostDetailsComment.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                                    lvPostDetailsComment.setStackFromBottom(true);
                                    Util.showToast("评论成功");
                                }

                                public void error(Object object) {
                                    Util.showToast("评论失败" + success.getMsg());
                                }
                            });
                        } else {
                            Util.showToast("评论失败" + success.getMsg());
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
        Toast.makeText(PostDetailsActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
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
