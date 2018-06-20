package com.dxslm.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.adapter.IssueImageAdapter;
import com.dxslm.entity.issue.IssueDetails;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.util.DateUtil;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.util.UrlUtil;
import com.liji.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IssueDetailsActivity extends AppCompatActivity {

    @Bind(R.id.iv_issue_details_back)
    ImageView ivIssueDetailsBack;
    @Bind(R.id.iv_issue_details_ddd)
    ImageView ivIssueDetailsDdd;
    @Bind(R.id.civ_issue_details_icon)
    CircleImageView civIssueDetailsIcon;
    @Bind(R.id.tv_issue_details_name)
    TextView tvIssueDetailsName;
    @Bind(R.id.tv_issue_details_time)
    TextView tvIssueDetailsTime;
    @Bind(R.id.tv_issue_details_context)
    TextView tvIssueDetailsContext;
    @Bind(R.id.recyclerview_issue_details)
    RecyclerView recyclerview;
    private String deptId;
    private String roleId;
    private String userId;
    private List<String> imgs = new ArrayList<>();
    private IssueImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details);
        ButterKnife.bind(this);

        getData();

    }

    private void getData() {
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this, "user");
        deptId = (String) helper.getSharedPreference("orideptId", "");
        roleId = (String) helper.getSharedPreference("roleId", "");
        userId = (String) helper.getSharedPreference("userId", "");
        String id = getIntent().getStringExtra("id");
        MyModel.getInstance().issueDetails(roleId, userId, id, new ICallBack() {
            @Override
            public void succeed(Object object) {
                IssueDetails details = (IssueDetails) object;
                tvIssueDetailsName.setText(details.getList().get(0).getNickname());
                tvIssueDetailsTime.setText(DateUtil.getDateToString(details.getList().get(0).getTime(), "yyyy-MM-dd"));
                tvIssueDetailsContext.setText(details.getList().get(0).getContent());
                String pic_url = details.getList().get(0).getHeadpic();
                if (null==pic_url||"".equals(pic_url)){
                    GlideUtil.getDefaultIconBitmap(R.drawable.mydefault,civIssueDetailsIcon);
                }else {
                    GlideUtil.getIconBitmap(UrlUtil.URL+pic_url, civIssueDetailsIcon);
                }
//                GlideUtil.getIconBitmap(UrlUtil.URL+pic_url,civIssueDetailsIcon);
                String str = details.getList().get(0).getPic_url();
                if(str!=null&&!str.equals("")) {
                    String[] strs = str.split(",");
                    for (int i = 0, len = strs.length; i < len; i++) {
                        imgs.add(strs[i].toString());
                    }
                    recyclerview.setLayoutManager(new LinearLayoutManager(IssueDetailsActivity.this));
                    adapter = new IssueImageAdapter(imgs);
                    recyclerview.setAdapter(adapter);
                }else {
                }
            }
            @Override
            public void error(Object object) {
            }
        });
    }

    @OnClick({R.id.iv_issue_details_back, R.id.iv_issue_details_ddd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_issue_details_back:
                finish();
                break;
            case R.id.iv_issue_details_ddd:
                break;
        }
    }
}
