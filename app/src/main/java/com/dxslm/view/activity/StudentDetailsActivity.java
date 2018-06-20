package com.dxslm.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.adapter.CommentAdapter;
import com.dxslm.adapter.EmojiAdapter;
import com.dxslm.adapter.ImageAdapter;
import com.dxslm.adapter.PostCommentAdapter;
import com.dxslm.adapter.StudentCommentAdapter;

import com.dxslm.entity.Success;
import com.dxslm.entity.news.NewCommentList;
import com.dxslm.entity.person.Pinglun;
import com.dxslm.entity.posts.PostCommentList;
import com.dxslm.entity.student.StudentCommentList;
import com.dxslm.entity.student.StudentDetails;
import com.dxslm.model.DialogCallback;
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
import com.dxslm.view.BaseActivity;
import com.dxslm.view.dialog.CommentDialog;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;

/**
 * 大学生详情
 */
public class StudentDetailsActivity extends TakePhotoActivity {

    @Bind(R.id.iv_student_details_back)
    ImageView ivStudentDetailsBack;
    @Bind(R.id.iv_student_details_ddd)
    ImageView ivStudentDetailsDdd;
    @Bind(R.id.tv_student_details_title)
    TextView tvStudentDetailsTitle;
    @Bind(R.id.tv_student_details_time)
    TextView tvStudentDetailsTime;
    @Bind(R.id.tv_student_details_contact)
    TextView tvStudentDetailsContact;
    @Bind(R.id.lv_student_details)
    ListViewForScrollView lvStudentDetails;
    @Bind(R.id.lv_student_details_comment)
    ListViewForScrollView lvStudentDetailsComment;
    @Bind(R.id.et_student_details_comment)
    EditText etStudentDetailsComment;
    @Bind(R.id.btn_student_details_confirm)
    Button btnStudentDetailsConfirm;
    private String deptId;
    private List<String> imgs = new ArrayList<>();
    //    ArrayList<String> urls = new ArrayList<>();
    private StudentDetails details;
    private String userId;
    private String roleId;
    private String articleId;
    private Success success;
    private StudentCommentList commenList;
    private StudentCommentAdapter adapter;


    private ImageButton mFaceImageButton;
    private ViewPager mEmojiViewPager;
    private LinearLayout mPointContain;
    private RelativeLayout mFaceContainRelativeLayout;
    private boolean isFaceShow = false;
    //上传图片
    private ImageView img_pic;
    private CameraBottomMenu menuWindow;
    private List<String> urls = new ArrayList<>();
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;
    private List<Pinglun> pingluns;
    private String inputId;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                HeadModel.getInstance().studentCommentList(articleId, roleId, new ICallBack() {
                    public void succeed(Object object) {
                        commenList = (StudentCommentList) object;
                        adapter.refreshData(commenList.getList());
                        Util.showToast("删除成功");
                    }

                    public void error(Object object) {
                    }
                });
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        ButterKnife.bind(this);
        img_pic = findViewById(R.id.img_tupian_picture_student);
        setView();

        //获取数据
        Bundle bundle = getIntent().getExtras();
        articleId = (String) bundle.get("articleId");
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        userId = (String) helper.getSharedPreference("userId", "");
        deptId = (String) helper.getSharedPreference("deptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        setModel();
//        setClick();
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
                menuWindow = new CameraBottomMenu(StudentDetailsActivity.this, clickListener);
                menuWindow.show();
            }
        });
    }


    private void initEmoji() {
        mEmojiViewPager.setAdapter(new EmojiAdapter(this, etStudentDetailsComment, mEmojiViewPager, mPointContain));
    }
    private void showOrHideFace() {
        if (isFaceShow) {
            mFaceContainRelativeLayout.setVisibility(View.GONE);
        } else {
            mFaceContainRelativeLayout.setVisibility(View.VISIBLE);

        }
        isFaceShow = !isFaceShow;
    }
    //    private void setClick(){
//        tvStudentSize.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new AlertDialog.Builder(StudentDetailsActivity.this)
//                        .setTitle("请选择字体大小")
//                        .setIcon(android.R.drawable.ic_dialog_info)
//                        .setSingleChoiceItems(new String[] {"最小号","小号","中号","大号" },
//                                postion, new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,
//                                                        int which) {
//                                        postion = which;
//                                        setSize(which);
//                                        dialog.dismiss();
//                                    }
//                                })
//                        .setNegativeButton("取消",null).show();
//            }
//        });
//    }
//    /**
//     *   设置字体大小
//     */
//    private void setSize(int i) {
//        if(i == 0) {
//            size = 12;
//        }else if(i == 1){
//            size = 14;
//        }else if(i == 2){
//            size = 16;
//        }else if(i == 3){
//            size = 18;
//        }
//        tvStudentDetailsTitle.setTextSize(size+2);
//        tvStudentDetailsTime.setTextSize(size+2);
//        tvStudentDetailsContact.setTextSize(size+2);
//        //   tv_contect.setTextSize(size);
//    }
    private void setModel() {
        HeadModel.getInstance().studentDetails(deptId, articleId, new ICallBack() {
            public void succeed(Object object) {
                try {
                    details = (StudentDetails) object;
                    tvStudentDetailsTitle.setText(details.getList().get(0).getArticle_title());
                    tvStudentDetailsTime.setText(DateUtil.getDateToString(details.getList().get(0).getInput_time(), "yyyy年MM月dd日"));
                    tvStudentDetailsContact.setText(details.getList().get(0).getArticle_content());
                    inputId = details.getList().get(0).getInput_user();
                    String str = details.getList().get(0).getPic_url();
                    String[] strs = str.split(",");
                    for (int i = 0, len = strs.length; i < len; i++) {
                        imgs.add(strs[i].toString());
                    }
                    if (imgs.size() > 0) {
                        lvStudentDetails.setAdapter(new ImageAdapter(StudentDetailsActivity.this, imgs));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void error(Object object) {
            }
        });
        HeadModel.getInstance().studentCommentList(articleId, roleId, new ICallBack() {
            public void succeed(Object object) {
                commenList = (StudentCommentList) object;
                adapter = new StudentCommentAdapter(StudentDetailsActivity.this, commenList.getList(),handler);
                lvStudentDetailsComment.setAdapter(adapter);
            }

            public void error(Object object) {
            }
        });
    }
    /**
     * 分享sdk
     */
    public void testShareSdk() {
        String content = details.getList().get(0).getArticle_content();
        ShareDialog dialog = new ShareDialog(this,"2",content,userId,articleId,roleId);
        dialog.setShareType(Platform.SHARE_TEXT);
        dialog.setShareTitle(details.getList().get(0).getArticle_title());
        dialog.setShareText(details.getList().get(0).getArticle_content()+"\n来自：大学生联盟");
        dialog.show();
    }

    @OnClick({R.id.iv_student_details_back, R.id.iv_student_details_ddd, R.id.btn_student_details_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_student_details_back:
                finish();
                break;
            case R.id.iv_student_details_ddd:
                testShareSdk();
                break;
            case R.id.btn_student_details_confirm:
                if(Util.isFastClick()){
                    return;
                }
                //发送评论
                String etComment = etStudentDetailsComment.getText().toString();
                if(!etComment.equals("")){
                    etStudentDetailsComment.setText("");
                    mFaceContainRelativeLayout.setVisibility(View.GONE);
                    HeadModel.getInstance().studentComment(articleId, roleId,userId, etComment,"","",inputId,new ICallBack() {
                        public void succeed(Object object) {
                            success = (Success) object;
                            //请求是否成功
                            if(success.isSuccess()){
                                HeadModel.getInstance().studentCommentList(articleId, roleId, new ICallBack() {
                                    public void succeed(Object object) {
                                        commenList = (StudentCommentList) object;
                                        adapter.refreshData(commenList.getList());
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
    private void setView(){
        mFaceImageButton = (ImageButton) findViewById(R.id.ib_face_student);

        mEmojiViewPager = (ViewPager) findViewById(R.id.vp_emoji);
        mPointContain = (LinearLayout) findViewById(R.id.ll_point_contain);
        mFaceContainRelativeLayout = (RelativeLayout) findViewById(R.id.rl_face_contain);
        //     textView = (TextView) findViewById(R.id.tv_news);
    }
    private void hideSoftInput() {
        //1.得到InputMethodManager对象
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //2.调用hideSoftInputFromWindow方法隐藏软键盘
        imm.hideSoftInputFromWindow(etStudentDetailsComment.getWindowToken(), 0); //强制隐藏键盘
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

                HeadModel.getInstance().studentComment(articleId, roleId,userId, "","",url,inputId,new ICallBack() {
                    public void succeed(Object object) {
                        success = (Success) object;
                        //请求是否成功
                        if(success.isSuccess()){
                            HeadModel.getInstance().studentCommentList(articleId, roleId, new ICallBack() {
                                public void succeed(Object object) {
                                    commenList = (StudentCommentList) object;
                                    adapter.refreshData(commenList.getList());
                                    lvStudentDetailsComment.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                                    lvStudentDetailsComment.setStackFromBottom(true);
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
        Toast.makeText(StudentDetailsActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
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
