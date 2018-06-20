package com.dxslm.view.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.dxslm.adapter.ChwlAdapter;
import com.dxslm.adapter.ChwlCommentAdapter;
import com.dxslm.adapter.ChwlDetailsAdapter;
import com.dxslm.adapter.EmojiAdapter;
import com.dxslm.entity.Success;
import com.dxslm.entity.chwl.ChwlCommentList;
import com.dxslm.entity.chwl.ChwlDetails;
import com.dxslm.entity.chwl.ChwlGoods;
import com.dxslm.entity.news.NewCommentList;
import com.dxslm.entity.person.Pinglun;
import com.dxslm.model.ActivityModel;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.ui.CameraBottomMenu;
import com.dxslm.ui.ListViewForScrollView;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.Util;
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

public class ChwlDetailsActivity extends TakePhotoActivity {

    @Bind(R.id.iv_chwl_details_back)
    ImageView ivChwlDetailsBack;
    @Bind(R.id.iv_chwl_details_ddd)
    ImageView ivChwlDetailsDdd;
    @Bind(R.id.tv_chwl_details_add)
    TextView tvChwlDetailsAdd;
    @Bind(R.id.tv_chwl_details_tel)
    TextView tvChwlDetailsTel;
    @Bind(R.id.tv_chwl_details_price)
    TextView tvChwlDetailsPrice;
    @Bind(R.id.lv_chwl_details_comment)
    ListViewForScrollView lvChwlDetailsComment;
    @Bind(R.id.et_chwl_details_comment)
    EditText etChwlDetailsComment;
    @Bind(R.id.btn_chwl_details_confirm)
    Button btnChwlDetailsConfirm;
    @Bind(R.id.lv_chwl_details)
    RecyclerView lvChwlDetails;
    private List<String> imgs = new ArrayList<>();
   // ArrayList<String> urls = new ArrayList<>();
    private ChwlDetails details;
    private String deptId;
    private String sellerId;
    private ChwlCommentList commenList;
    private ChwlCommentAdapter adapter;
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
                ActivityModel.getInstance().chwlCommentList(sellerId, roleId, new ICallBack() {
                    public void succeed(Object object) {
                        commenList = (ChwlCommentList) object;
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
        setContentView(R.layout.activity_chwl_details);
        ButterKnife.bind(this);
        img_pic = (ImageView) findViewById(R.id.img_tupian_picture_chwl );
        //绑定控件
        setView();
        //获取数据

        Bundle bundle = getIntent().getExtras();
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        deptId = (String) helper.getSharedPreference("orideptId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        sellerId = (String) bundle.get("sellerId");
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
                menuWindow = new CameraBottomMenu(ChwlDetailsActivity.this, clickListener);
                menuWindow.show();
            }
        });
    }

    private void setModel() {
        ActivityModel.getInstance().chwlDetails(roleId, sellerId, new ICallBack() {
            public void succeed(Object object) {
                details = (ChwlDetails) object;
                tvChwlDetailsAdd.setText("地址：" + details.getList().get(0).getSeller_address());
                tvChwlDetailsTel.setText("电话：" + details.getList().get(0).getSeller_tel());
                tvChwlDetailsPrice.setText("人均消费：" + details.getList().get(0).getSeller_perfee() + "元/人");
                inputId = details.getList().get(0).getInput_user();
            }

            public void error(Object object) {
            }
        });
        ActivityModel.getInstance().chwlGoodsDetails(roleId, sellerId, new ICallBack() {
            @Override
            public void succeed(Object object) {
                ChwlGoods goods = (ChwlGoods) object;
                lvChwlDetails.setLayoutManager(new GridLayoutManager(ChwlDetailsActivity.this,2));
                ChwlDetailsAdapter adapter1 = new ChwlDetailsAdapter(goods.getList(),ChwlDetailsActivity.this);
                lvChwlDetails.setAdapter(adapter1);
            }
            public void error(Object object) {

            }
        });
        ActivityModel.getInstance().chwlCommentList(sellerId, roleId, new ICallBack() {
            public void succeed(Object object) {
                commenList = (ChwlCommentList) object;
                adapter = new ChwlCommentAdapter(ChwlDetailsActivity.this, commenList.getList(),handler );
                lvChwlDetailsComment.setAdapter(adapter);
            }
            public void error(Object object) {
            }
        });
    }

    @OnClick({R.id.iv_chwl_details_back, R.id.iv_chwl_details_ddd, R.id.btn_chwl_details_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_chwl_details_back:
                finish();
                break;
            case R.id.iv_chwl_details_ddd:
                break;
            case R.id.btn_chwl_details_confirm:
                if(Util.isFastClick()){
                    return;
                }
                //发送评论
                String etComment = etChwlDetailsComment.getText().toString();
                if (!etComment.equals("")) {
                    etChwlDetailsComment.setText("");
                    mFaceContainRelativeLayout.setVisibility(View.GONE);
                    ActivityModel.getInstance().chwlComment(roleId, sellerId, etComment, userId,"","",inputId, new ICallBack() {
                        public void succeed(Object object) {
                            success = (Success) object;
                            //请求是否成功
                            if (success.isSuccess()) {
                                ActivityModel.getInstance().chwlCommentList(sellerId, roleId, new ICallBack() {
                                    public void succeed(Object object) {
                                        commenList = (ChwlCommentList) object;
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
                } else {
                    Util.showToast("评论内容不能为空");
                }
                break;
        }
    }
    /**
     * 初始化表情
     */
    private void initEmoji() {
        mEmojiViewPager.setAdapter(new EmojiAdapter(this, etChwlDetailsComment, mEmojiViewPager, mPointContain));
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
        mFaceImageButton = (ImageButton) findViewById(R.id.ib_face_chwl);

        mEmojiViewPager = (ViewPager) findViewById(R.id.vp_emoji);
        mPointContain = (LinearLayout) findViewById(R.id.ll_point_contain);
        mFaceContainRelativeLayout = (RelativeLayout) findViewById(R.id.rl_face_contain);
        //     textView = (TextView) findViewById(R.id.tv_news);
    }
    private void hideSoftInput() {
        //1.得到InputMethodManager对象
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //2.调用hideSoftInputFromWindow方法隐藏软键盘
        imm.hideSoftInputFromWindow(etChwlDetailsComment.getWindowToken(), 0); //强制隐藏键盘
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
                ActivityModel.getInstance().chwlComment(roleId,sellerId,"", userId,"",url,inputId,new ICallBack() {
                    public void succeed(Object object) {
                        success = (Success) object;
                        //请求是否成功
                        if(success.isSuccess()){
                            ActivityModel.getInstance().chwlCommentList(sellerId, roleId, new ICallBack() {
                                public void succeed(Object object) {
                                    commenList = (ChwlCommentList) object;
                                    adapter.refreshData(commenList.getList());
                                    lvChwlDetailsComment.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                                    lvChwlDetailsComment.setStackFromBottom(true);
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
        Toast.makeText(ChwlDetailsActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
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
