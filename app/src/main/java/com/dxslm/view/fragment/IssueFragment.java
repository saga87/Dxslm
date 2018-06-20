package com.dxslm.view.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.dxslm.view.activity.LoginActivity;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fj.edittextcount.lib.FJEditTextCount;

/**
 * Created by fxn on 2017/12/11.
 */

public class IssueFragment extends TakePhotoFragment {

    @Bind(R.id.iv_f_issue_back)
    ImageView ivFIssueBack;
    @Bind(R.id.iv_f_issue_ddd)
    ImageView ivFIssueDdd;
    @Bind(R.id.sp_f_issue_fblx)
    Spinner spFIssueFblx;
    @Bind(R.id.fj_f_issue_wzms)
    FJEditTextCount fjFIssueWzms;
    @Bind(R.id.btn_f_issue_bc)
    Button btnFIssueBc;
    @Bind(R.id.et_f_issue_add)
    EditText etFIssueAdd;
    @Bind(R.id.tv_f_issue_add)
    TextView tvFIssueAdd;
    @Bind(R.id.sp_f_issue_esmm)
    Spinner spFIssueEsmm;
    @Bind(R.id.tv_f_issue_esmm)
    LinearLayout tvFIssueEsmm;
    @Bind(R.id.rv_f_issue_sc)
    RecyclerView rvFIssueSc;
    TextView textView_biaoti;
    EditText et_biaoti;
    private PhotoAdapter adapter;
    private List<String> urls = new ArrayList<>();
    private List<Bitmap> imgs = new ArrayList<>();
    private  List<String> orderurl = new ArrayList<>();

    private List<Bitmap> orderimgs = new ArrayList<>();
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private AlertDialog.Builder normalDialog;
    private String userId;
    private int typeId = 0;
    String buyorsellId = "";
    private String deptId;
    private SharedPreferencesHelper helper;
    private ProgressDialog waitingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue, null);
        ButterKnife.bind(this, view);
        ivFIssueBack.setVisibility(View.GONE);
        textView_biaoti = view.findViewById(R.id.tv_f_issue_biaoti1);
        et_biaoti = view.findViewById(R.id.et_f_issue_baiotishuru);
        helper = new SharedPreferencesHelper(getActivity(), "user");
        userId = (String) helper.getSharedPreference("userId", "");
        deptId = (String) helper.getSharedPreference("deptId", "");
        setView();
        setListener();
        return view;
    }

    private void setView() {
        fjFIssueWzms.setEtHint("描述一下您具体的要求（最多500字）")//设置提示文字
                .setEtMinHeight(50)//设置最小高度，单位px
                .setLength(500)//设置总字数
                //TextView显示类型(SINGULAR单数类型)(PERCENTAGE百分比类型)
                .setType(FJEditTextCount.PERCENTAGE)
                .setLineColor("#ffffff")//设置横线颜色
                .show();
        //添加拍照图片
        orderimgs.add(BitmapFactory.decodeResource(getResources(), R.drawable.photo));
        rvFIssueSc.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new PhotoAdapter(orderimgs);
        rvFIssueSc.setAdapter(adapter);
        //spinner
        List<String> list = new ArrayList<>();
        list.add("失物招领");
        list.add("表白墙");
        list.add("二手");
        list.add("本校论坛");
        list.add("高校圈");
        ArrayAdapter adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFIssueFblx.setAdapter(adapter1);
        //spinner
        List<String> list1 = new ArrayList<>();
        list1.add("求购");
        list1.add("出售");
        ArrayAdapter adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list1);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFIssueEsmm.setAdapter(adapter2);
    }

    private void setListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (urls.size() == position) {
                    CameraBottomMenu menuWindow = new CameraBottomMenu(getActivity(), clickListener);
                    menuWindow.show();
                } else {
                    showNormalDialog(position);
                }
            }
        });
        spFIssueFblx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spFIssueFblx.getSelectedItem().toString().equals("表白墙")) {
                    tvFIssueAdd.setVisibility(View.VISIBLE);
                    etFIssueAdd.setVisibility(View.VISIBLE);
                } else {
                    tvFIssueAdd.setVisibility(View.GONE);
                    etFIssueAdd.setVisibility(View.GONE);
                }
                if (spFIssueFblx.getSelectedItem().toString().equals("二手")) {
                    tvFIssueEsmm.setVisibility(View.VISIBLE);
                    spFIssueEsmm.setVisibility(View.VISIBLE);
                } else {
                    tvFIssueEsmm.setVisibility(View.GONE);
                    spFIssueEsmm.setVisibility(View.GONE);
                }
                if (spFIssueFblx.getSelectedItem().toString().equals("本校论坛")
                        ||spFIssueFblx.getSelectedItem().toString().equals("高校圈")
                        ) {
                    textView_biaoti.setVisibility(View.VISIBLE);
                    et_biaoti.setVisibility(View.VISIBLE);
                } else {
                    textView_biaoti.setVisibility(View.GONE);
                    et_biaoti.setVisibility(View.GONE);
                }
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * 删除图片对话框
     */
    private void showNormalDialog(final int position) {
        normalDialog = new AlertDialog.Builder(getActivity());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_f_issue_back, R.id.iv_f_issue_ddd, R.id.btn_f_issue_bc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_f_issue_back:
                getActivity().finish();
                break;
            case R.id.iv_f_issue_ddd:
                break;
            case R.id.btn_f_issue_bc:
                showWaitingDialog();
                send();
                break;
        }
    }

    private void showWaitingDialog() {

    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
            waitingDialog=
                    new ProgressDialog(getActivity());
            waitingDialog.setMessage("发布中  请等待...");
            waitingDialog.setIndeterminate(true);
            waitingDialog.setCancelable(false);
            waitingDialog.show();
    }

    /**
     * 上传
     */
    private void send() {
        deptId = (String) helper.getSharedPreference("deptId", "");
        String type = spFIssueFblx.getSelectedItem().toString();
        String buyorsell = spFIssueEsmm.getSelectedItem().toString();
        String wzms = fjFIssueWzms.getText().toString();
        String address = etFIssueAdd.getText().toString();
        String title = et_biaoti.getText().toString();
        if (type.equals("") || wzms.equals("")) {
            Util.showToast("请补全数据");
            waitingDialog.dismiss();
            return;
        }
        if (type.equals("表白墙")) {
            if (address.equals("")) {
                Util.showToast("请补全数据");
                waitingDialog.dismiss();
                return;
            }

        }
        if (type.equals("本校论坛")) {
            if (title.equals("")) {
                Util.showToast("请补全数据");
                waitingDialog.dismiss();
                return;
            }

        }
        if (type.equals("失物招领")) {
            if (urls.size() == 0) {
                Util.showToast("请补全数据");
                waitingDialog.dismiss();
                return;
            }
        }


        if (type.equals("高校圈")) {
            if (title.equals("")) {
                Util.showToast("请补全数据");
                return;
            }

        }
        switch (type) {
            case "失物招领":
                typeId = 3;
                break;
            case "表白墙":
                typeId = 1;
                break;
            case "二手":
                typeId = 2;
                break;
            case "本校论坛":
                typeId = 4;
                break;
            case "高校圈":
                typeId = 5;
                break;
        }
        switch (buyorsell) {
            case "求购":
                buyorsellId = "0";
                break;
            case "出售":
                buyorsellId = "1";
                break;
        }
        if(!type.equals("二手")){
            buyorsellId = "";
        }
        MyModel.getInstance().setIssue(userId, deptId,typeId + "", wzms, address,title, buyorsellId, urls, new ICallBack() {
            @Override
            public void succeed(Object object) {
                waitingDialog.dismiss();
                Success success = (Success) object;
                if (success.isSuccess()) {
                    //刷新界面
                    etFIssueAdd.setText("");
                    fjFIssueWzms.setText("");
                    et_biaoti.setText("");
                    urls.clear();
                    orderimgs.clear();
                    orderimgs.add(BitmapFactory.decodeResource(getResources(), R.drawable.photo));
                    adapter.replaceData(orderimgs);
                    Util.showToast("发布成功");
                } else {
                    Util.showToast("发布失败，请过会再试"+success.getMsg());
                }
            }

            @Override
            public void error(Object object) {
                waitingDialog.dismiss();
                Util.showToast("发布失败，请过会再试");
            }
        });
    }

    /**
     * 拍照的一类方法
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();  //设置压缩、裁剪参数
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();
        String tpath = Environment.getExternalStorageDirectory()+"/temp_ys/" + System.currentTimeMillis() + ".jpg";
        String compressPath =  PictureUtil.compressImage(iconPath,tpath,40);
        if (urls.size() < 10) {
//            imgs.add(0, BitmapFactory.decodeFile(compressPath));//替换iconPath 原图容易oom
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
                    //不裁剪
                    takePhoto.onPickFromCapture(imageUri);
                    //拍照并裁剪
//                    takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                    break;
                case R.id.btn2:
                    imageUri = getImageCropUri();
                    //不裁剪
                    takePhoto.onPickFromGallery();
                    //从相册中选取图片并裁剪
//                    takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                    break;
            }
        }
    };
}
