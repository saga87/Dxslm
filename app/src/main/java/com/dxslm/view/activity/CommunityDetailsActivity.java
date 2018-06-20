package com.dxslm.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.adapter.SmallImageAdapter;
import com.dxslm.entity.community.CommunityDetails;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.ui.GridViewForScrollview;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 校园社团详情
 */
public class CommunityDetailsActivity extends BaseActivity {

    @Bind(R.id.iv_community_details_back)
    ImageView ivCommunityDetailsBack;
    @Bind(R.id.iv_community_details_ddd)
    ImageView ivCommunityDetailsDdd;
    @Bind(R.id.tv_community_details_title)
    TextView tvCommunityDetailsTitle;
    @Bind(R.id.tv_community_details_name)
    TextView tvCommunityDetailsName;
    @Bind(R.id.tv_community_details_num)
    TextView tvCommunityDetailsNum;
    @Bind(R.id.tv_community_details_activity)
    TextView tvCommunityDetailsActivity;
    @Bind(R.id.tv_community_details_tel)
    TextView tvCommunityDetailsTel;
    @Bind(R.id.tv_community_details_add)
    TextView tvCommunityDetailsAdd;
    @Bind(R.id.tv_community_details_award)
    TextView tvCommunityDetailsAward;
    @Bind(R.id.gv_community_details)
    GridViewForScrollview gvCommunityDetails;
    private String roleId;
    private String communityId;
    private CommunityDetails details;
    private List<String> imgs = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
//    TextView tvCommunitySize;
//    private int postion = 1;
//    private int size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_details);
        ButterKnife.bind(this);
       // tvCommunitySize = (TextView) findViewById(R.id.tv_community_details_size);
        //获取数据
        Bundle bundle = getIntent().getExtras();
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        roleId = (String) helper.getSharedPreference("roleId", "");
        communityId = (String) bundle.get("communityId");

        setModel();
       // setClick();
    }

    private void setModel() {
        HeadModel.getInstance().communityDetails(roleId, communityId, new ICallBack() {
            public void succeed(Object object) {
                details = (CommunityDetails) object;
                tvCommunityDetailsTitle.setText(details.getList().get(0).getCommunity_name());
                tvCommunityDetailsName.setText("社长:  "+details.getList().get(0).getShe_zhang());
                tvCommunityDetailsNum.setText("成员:  "+details.getList().get(0).getCommunity_scope());
                tvCommunityDetailsActivity.setText("主要活动:  "+details.getList().get(0).getCommunity_instruction());
                tvCommunityDetailsTel.setText("联系方式:  "+details.getList().get(0).getContact_way());
                tvCommunityDetailsAdd.setText("地址:  "+details.getList().get(0).getCommunity_address());
                tvCommunityDetailsAward.setText("荣誉奖励:  "+details.getList().get(0).getHonoraward());
                String str = details.getList().get(0).getPic_url();
                String[] strs = str.split(",");
                for (int i = 0, len = strs.length; i < len; i++) {
                    imgs.add(strs[i].toString());
                }
                if (imgs.size() > 0) {
                    gvCommunityDetails.setAdapter(new SmallImageAdapter(imgs,CommunityDetailsActivity.this));
                }
            }

            public void error(Object object) {
            }
        });
    }
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
//        tvCommunityDetailsTitle.setTextSize(size+2);
//        tvCommunityDetailsName.setTextSize(size+2);
//        tvCommunityDetailsNum.setTextSize(size+2);
//        tvCommunityDetailsActivity.setTextSize(size+2);
//        tvCommunityDetailsTel.setTextSize(size+2);
//        tvCommunityDetailsAdd.setTextSize(size+2);
//        tvCommunityDetailsAward.setTextSize(size+2);
//        //   tv_contect.setTextSize(size);
//    }
//    private void setClick(){
//        tvCommunitySize.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new AlertDialog.Builder(CommunityDetailsActivity.this)
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

    @OnClick({R.id.iv_community_details_back, R.id.iv_community_details_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_community_details_back:
                finish();
                break;
            case R.id.iv_community_details_ddd:
                break;
        }
    }
}
