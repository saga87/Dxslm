package com.dxslm.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.entity.PhotoSuccess;
import com.dxslm.entity.Success;
import com.dxslm.entity.person.PersonBackground;
import com.dxslm.entity.user.User;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.ui.CameraBottomMenu;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserMessageActivity extends TakePhotoActivity {

    @Bind(R.id.iv_user_message_back)
    ImageView ivUserMessageBack;
    @Bind(R.id.iv_user_message_ddd)
    ImageView ivUserMessageDdd;
    @Bind(R.id.et_user_message_nc)
    EditText etUserMessageNc;
    @Bind(R.id.et_user_message_sjh)
    EditText etUserMessageSjh;
    @Bind(R.id.et_user_message_yx)
    EditText etUserMessageYx;
    @Bind(R.id.btn_user_message_bc)
    Button btnUserMessageBc;
    @Bind(R.id.btn_user_message_qx)
    Button btnUserMessageQx;
    private String userId;
    private String deptId;
    private String roleId;


    private CameraBottomMenu menuWindow;
    private List<String> urls = new ArrayList<>();

    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private ImageView imageView;
    private PersonBackground success;
    private String back;
    private CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        ButterKnife.bind(this);
        imageView = findViewById(R.id.img_tupian_person);
        circleImageView = findViewById(R.id.iv_my_touxiang_per);
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        userId = (String) helper.getSharedPreference("userId", "");
        deptId = (String) helper.getSharedPreference("orideptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        setModel();
        initData();
        back = (String) helper.getSharedPreference("bgpic", "");
        GlideUtil.getBitmap(UrlUtil.URL+back, imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuWindow = new CameraBottomMenu(UserMessageActivity.this, clickListener);
                menuWindow.show();
            }
        });
    }
    private void setModel() {
        MyModel.getInstance().getMessage(roleId,userId, new ICallBack() {
            public void succeed(Object object) {
                User user = (User) object;
                etUserMessageNc.setText(user.getList().get(0).getUser_realname());
                etUserMessageSjh.setText(user.getList().get(0).getUser_tel());
                etUserMessageYx.setText(user.getList().get(0).getUser_email());

                String pic_url = user.getList().get(0).getHeadpic();
                if (null==pic_url||"".equals(pic_url)){
                    GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,circleImageView);
                }else {
                    GlideUtil.getIconBitmap(UrlUtil.URL+pic_url, circleImageView);
                }

           //     GlideUtil.getIconBitmap(UrlUtil.URL+user.getList().get(0).getHeadpic(),circleImageView);
            }
            public void error(Object object) {
                Util.showToast("加载失败");
            }
        });
    }


    @OnClick({R.id.iv_user_message_back, R.id.iv_user_message_ddd, R.id.btn_user_message_bc, R.id.btn_user_message_qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_message_back:
                finish();
                break;
            case R.id.iv_user_message_ddd:
                break;
            case R.id.btn_user_message_bc:
                String name = etUserMessageNc.getText().toString();
                String tel = etUserMessageSjh.getText().toString();
                String email = etUserMessageYx.getText().toString();
                if(!name.equals("")||!tel.equals("")||!email.equals("")){
                    MyModel.getInstance().alterMessage(roleId, userId, tel, name, email, new ICallBack() {
                        @Override
                        public void succeed(Object object) {
                            Success s = (Success) object;
                            if(s.isSuccess()){
                                Util.showToast("修改成功");
                            }else {
                                Util.showToast("修改失败");
                            }
                        }
                        @Override
                        public void error(Object object) {
                            Util.showToast("修改失败");
                        }
                    });
                }else {
                    Util.showToast("数据不能为空");
                }
                break;
            case R.id.btn_user_message_qx:
                finish();
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
         urls.clear();
        String iconPath = result.getImage().getOriginalPath();
         urls.add(iconPath);
        MyModel.getInstance().personBackground(userId,urls, new ICallBack() {
            @Override
            public void succeed(Object object) {
                success = (PersonBackground) object;
                if (success.isSuccess()) {
                    String url = UrlUtil.URL + success.getObj();
                    GlideUtil.getBitmap(url, imageView);

                } else {
                    Util.showToast("上传失败");
                }

            }
            @Override
            public void error(Object object) {
                Util.showToast("上传失败");
            }
        });

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(UserMessageActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
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
