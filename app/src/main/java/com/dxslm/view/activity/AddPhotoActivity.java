package com.dxslm.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dxslm.R;
import com.dxslm.adapter.PhotoAdapter;
import com.dxslm.entity.Success;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.ui.CameraBottomMenu;
import com.dxslm.util.PictureUtil;
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

public class AddPhotoActivity extends TakePhotoActivity {

    @Bind(R.id.addxc_back)
    ImageView addxcBack;
    @Bind(R.id.add_pic)
    RecyclerView addPic;
    @Bind(R.id.add_pic_upload)
    Button addPicUpload;

    private PhotoAdapter adapter;
    private List<String> urls = new ArrayList<>();
    private  List<String> orderurl = new ArrayList<>();

    private List<Bitmap> orderimgs = new ArrayList<>();
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private AlertDialog.Builder normalDialog;
    private String userId;
    private SharedPreferencesHelper helper;
    private ProgressDialog waitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        ButterKnife.bind(this);
        helper = new SharedPreferencesHelper(this, "user");
        userId = (String) helper.getSharedPreference("userId", "");
        initData();
        setView();
        setListener();
    }


    @OnClick({R.id.addxc_back,R.id.add_pic_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addxc_back:
            finish();
            break;
            case R.id.add_pic_upload:
                showWaitingDialog();
                upload();
            break;
        }
    }

    private void upload() {
        MyModel.getInstance().uploadPics(userId, urls, new ICallBack() {
            @Override
            public void succeed(Object object) {
                waitingDialog.dismiss();
                Success success = (Success) object;
                if (success.isSuccess()) {
                    //刷新界面
                    urls.clear();
                    orderimgs.clear();
                    orderimgs.add(BitmapFactory.decodeResource(getResources(), R.drawable.photo));
                    adapter.replaceData(orderimgs);
                    Util.showToast("上传成功");
                } else {
                    Util.showToast("上传失败，请过会再试"+success.getMsg());
                }
            }

            @Override
            public void error(Object object) {
                waitingDialog.dismiss();
                Util.showToast("上传失败，请过会再试");
            }
        });
    }

    private void setListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (urls.size() == position) {
                    CameraBottomMenu menuWindow = new CameraBottomMenu(AddPhotoActivity.this, clickListener);
                    menuWindow.show();
                } else {
                    showNormalDialog(position);
                }
            }
        });
    }


    /**
     * 删除图片对话框
     */
    private void showNormalDialog(final int position) {
        normalDialog = new AlertDialog.Builder(AddPhotoActivity.this);
        normalDialog.setTitle("删除");
        normalDialog.setMessage("您是否要删除这张图片");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove1(position);
            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                normalDialog.create();
            }
        });
        normalDialog.show();
    }

    /**
     * 删除图片
     */
    private void remove1(int position) {
        if (urls.size() > 8) {
            orderimgs.add(BitmapFactory.decodeResource(getResources(), R.drawable.photo));
            urls.remove(urls.size()-1-position);
            orderimgs.remove(position);
        } else {
            urls.remove(urls.size()-1-position);
            orderimgs.remove(position);
        }
        adapter.replaceData(orderimgs);
    }
    private void showWaitingDialog() {

    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        waitingDialog=
                new ProgressDialog(AddPhotoActivity.this);
        waitingDialog.setMessage("上传中  请等待...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();
        String tpath = Environment.getExternalStorageDirectory()+"/temp_ys/" + System.currentTimeMillis() + ".jpg";
        String compressPath =  PictureUtil.compressImage(iconPath,tpath,40);
        if (urls.size() < 10) {
            urls.add(0, compressPath);
            orderimgs.clear();
            for(String s:converse(urls)){
                orderimgs.add(BitmapFactory.decodeFile(s));
            }
            orderimgs.add(BitmapFactory.decodeResource(getResources(), R.drawable.photo));
            adapter.replaceData(orderimgs);

        } else {
            Util.showToast("最多添加九张");
        }
        if (orderimgs.size() == 10) {
            orderimgs.remove(9);
            adapter.replaceData(orderimgs);
        }
    }

    private List<String> converse(List<String> urls) {
        orderurl.clear();
        for(int i = 0;i<urls.size();i++){
            orderurl.add(0,urls.get(i));
        }
        return orderurl;
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(AddPhotoActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
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
        compressConfig = new CompressConfig.Builder().setMaxSize(40 * 1024).setMaxPixel(800).create();
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
                    takePhoto.onPickFromCapture(imageUri);
                    break;
                case R.id.btn2:
                    imageUri = getImageCropUri();
                    takePhoto.onPickFromGallery();
                    break;
            }
        }
    };

    private void setView() {
        //添加拍照图片
        orderimgs.add(BitmapFactory.decodeResource(getResources(), R.drawable.photo));
        addPic.setLayoutManager(new GridLayoutManager(AddPhotoActivity.this, 3));
        adapter = new PhotoAdapter(orderimgs);
        addPic.setAdapter(adapter);
    }
}
