package com.dxslm.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dxslm.R;
import com.dxslm.entity.PhotoSuccess;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.ui.CameraBottomMenu;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.dxslm.view.activity.CollectActivity;
import com.dxslm.view.activity.GywmActivity;
import com.dxslm.view.activity.IssueActivity;
import com.dxslm.view.activity.PhotoActivity;
import com.dxslm.view.activity.UserMessageActivity;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
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


public class MyFragment extends TakePhotoFragment {
    @Bind(R.id.iv_my_back)
    ImageView ivMyBack;
    @Bind(R.id.iv_my_ddd)
    ImageView ivMyDdd;
    @Bind(R.id.ll_my_wdfb)
    LinearLayout llMyWdfb;
    @Bind(R.id.ll_my_wdxx)
    LinearLayout llMyWdxx;
    @Bind(R.id.ll_my_wdsc)
    LinearLayout llMyWdsc;
    @Bind(R.id.ll_my_gywm)
    LinearLayout llMyGywm;
    @Bind(R.id.iv_my_touxiang)
    CircleImageView ivMyTouxiang;
    @Bind(R.id.ll_my_wdxc)
    LinearLayout llMyWdxc;
    private CameraBottomMenu menuWindow;
    private List<String> urls = new ArrayList<>();

    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private String userId;
    private String pic;
    private TextView textView;
    private int postion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.bind(this, view);
        textView = view.findViewById(R.id.size_shezhi_quanju);
        final SharedPreferencesHelper helper = new SharedPreferencesHelper(getActivity(), "user");
        userId = (String) helper.getSharedPreference("userId", "");
        pic = (String) helper.getSharedPreference("pic", "");

        if (null == pic || "".equals(pic.trim())) {
            GlideUtil.getDefaultIconBitmap(R.drawable.mydefault, ivMyTouxiang);
        } else {
            GlideUtil.getIconBitmap(UrlUtil.URL + pic, ivMyTouxiang);
        }
//        GlideUtil.getIconBitmap(UrlUtil.URL+pic, ivMyTouxiang);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] fontsize = getResources().getStringArray(R.array.fontsize);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("选择字体");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                SharedPreferencesHelper helper1 = new SharedPreferencesHelper(getActivity(), "user");
                postion = (Integer) helper1.getSharedPreference("which", new Integer(1));
                builder.setSingleChoiceItems(fontsize, postion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        postion = which;
                        String str = fontsize[which];
                        SharedPreferencesHelper helper = new SharedPreferencesHelper(getActivity(), "user");
                        helper.put("fontsize", str);
                        helper.put("which", postion);
                        Toast.makeText(getContext(), "设置" + str + "字体成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }

                    ;
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();  //设置压缩、裁剪参数
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        urls.clear();
        String iconPath = result.getImage().getOriginalPath();
        urls.add(iconPath);
        MyModel.getInstance().setIcon(userId, urls, new ICallBack() {
            @Override
            public void succeed(Object object) {
                PhotoSuccess success = (PhotoSuccess) object;
                if (success.isSuccess()) {
                    String url = UrlUtil.URL + success.getObj().get(0).getPic_url();
                    GlideUtil.getIconBitmap(url, ivMyTouxiang);
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
        Toast.makeText(getActivity(), "Error:" + msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_my_back, R.id.iv_my_ddd, R.id.iv_my_touxiang, R.id.ll_my_wdfb, R.id.ll_my_wdxx, R.id.ll_my_wdsc, R.id.ll_my_gywm,
            R.id.ll_my_wdxc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_back:
                logout();
                break;
            case R.id.ll_my_wdxc:
                Intent intentxc = new Intent(getActivity(), PhotoActivity.class);
                startActivity(intentxc);
                break;
            case R.id.iv_my_ddd:
                break;
            case R.id.iv_my_touxiang:
                menuWindow = new CameraBottomMenu(getActivity(), clickListener);
                menuWindow.show();
                break;
            case R.id.ll_my_wdfb:
                Intent intent1 = new Intent(getActivity(), IssueActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_my_wdxx:
                Intent intent = new Intent(getActivity(), UserMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_wdsc:
                Intent intent2 = new Intent(getActivity(), CollectActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_my_gywm:
                Intent intent3 = new Intent(getActivity(), GywmActivity.class);
                startActivity(intent3);
                break;
        }
    }

    private void logout() {
        new android.app.AlertDialog.Builder(getContext()).setTitle("确认注销吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        SharedPreferencesHelper helper = new SharedPreferencesHelper(getActivity(), "login");
                        helper.clear();
                        getActivity().finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }


    //
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
