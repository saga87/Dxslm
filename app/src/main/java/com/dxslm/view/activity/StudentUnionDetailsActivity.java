package com.dxslm.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.dxslm.R;
import com.dxslm.adapter.ImageAdapter;
import com.dxslm.adapter.StudentCommentAdapter;
import com.dxslm.entity.student.StudentActivityDetails;
import com.dxslm.entity.student.StudentCommentList;
import com.dxslm.entity.student.StudentDetails;
import com.dxslm.entity.student.StudentSponsordetails;
import com.dxslm.model.HeadModel;
import com.dxslm.model.ICallBack;
import com.dxslm.ui.ListViewForScrollView;
import com.dxslm.util.DateUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.dxslm.util.Util;
import com.dxslm.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentUnionDetailsActivity extends BaseActivity {

    @Bind(R.id.iv_student_union_details_back)
    ImageView ivStudentUnionDetailsBack;
    @Bind(R.id.iv_student_union_details_ddd)
    ImageView ivStudentUnionDetailsDdd;
    @Bind(R.id.tv_student_union_details_title)
    TextView tvStudentUnionDetailsTitle;
    @Bind(R.id.tv_student_union_details_time)
    TextView tvStudentUnionDetailsTime;
    @Bind(R.id.tv_student_union_details_contact)
    TextView tvStudentUnionDetailsContact;
    @Bind(R.id.lv_student_union_details)
    ListViewForScrollView lvStudentUnionDetails;
    private String activityId;
    private String roleId;
    private List<String> imgs = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private String supportId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_union_details);
        ButterKnife.bind(this);

        //获取数据
        Bundle bundle = getIntent().getExtras();
        activityId = (String) bundle.get("activityId");
        supportId = (String) bundle.get("supportId");


        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        roleId = (String) helper.getSharedPreference("roleId", "");

        setModel();
    }

    private void setModel() {
        if(activityId!=null) {
            HeadModel.getInstance().activityDetails(roleId, activityId, new ICallBack() {
                public void succeed(Object object) {
                    try {
                        StudentActivityDetails details = (StudentActivityDetails) object;
                        tvStudentUnionDetailsTitle.setText(details.getList().get(0).getActivity_theme());
                        tvStudentUnionDetailsTime.setText(DateUtil.getDateToString(details.getList().get(0).getInput_time(), "yyyy年MM月dd日"));
                        tvStudentUnionDetailsContact.setText(details.getList().get(0).getActivity_content());

                        String str = details.getList().get(0).getPic_url();
                        String[] strs = str.split(",");
                        for (int i = 0, len = strs.length; i < len; i++) {
                            imgs.add(strs[i].toString());
                            list.add(UrlUtil.URL+strs[i].toString());
                        }
                        if (imgs.size() > 0) {
                            lvStudentUnionDetails.setAdapter(new ImageAdapter(StudentUnionDetailsActivity.this, imgs));
                        }

                        lvStudentUnionDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Util.showPhotos(list,StudentUnionDetailsActivity.this,i);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void error(Object object) {
                }
            });
        }else {
            HeadModel.getInstance().sponsorDetails(roleId, supportId, new ICallBack() {
                public void succeed(Object object) {
                    try {
                        StudentSponsordetails details = (StudentSponsordetails) object;
                        tvStudentUnionDetailsTitle.setText(details.getList().get(0).getDept_name());
                        tvStudentUnionDetailsTime.setText(DateUtil.getDateToString(details.getList().get(0).getInput_time(), "yyyy年MM月dd日"));
                        tvStudentUnionDetailsContact.setText(details.getList().get(0).getActivity_name());

                        String str = details.getList().get(0).getPic_url();
                        String[] strs = str.split(",");
                        for (int i = 0, len = strs.length; i < len; i++) {
                            imgs.add(strs[i].toString());
                            list.add(UrlUtil.URL+strs[i].toString());
                        }
                        if (imgs.size() > 0) {
                            lvStudentUnionDetails.setAdapter(new ImageAdapter(StudentUnionDetailsActivity.this, imgs));
                        }
                        lvStudentUnionDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Util.showPhotos(list,StudentUnionDetailsActivity.this,i);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void error(Object object) {
                }
            });
        }
    }
    @OnClick({R.id.iv_student_union_details_back, R.id.iv_student_union_details_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_student_union_details_back:
                finish();
                break;
            case R.id.iv_student_union_details_ddd:
                break;
        }
    }
}
