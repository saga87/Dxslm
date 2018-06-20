package com.dxslm.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.dxslm.MyApplication;
import com.dxslm.R;
import com.dxslm.adapter.ClassifyAdapter;
import com.dxslm.entity.ClassifyOne;
import com.dxslm.model.ICallBack;
import com.dxslm.model.LoginModel;
import com.dxslm.util.SharedPreferencesHelper;
import com.dxslm.view.fragment.ClassifyFragment;
import com.dxslm.view.fragment.HeadFragment;

import java.io.Serializable;

public class SchoolClassifyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ClassifyAdapter adapter;
    private ClassifyFragment myFragment;
    private ClassifyOne oneList;
    private LoginBroadcastReceiver mReceiver;
    private ImageView iv_classify_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_classify);
        listView = (ListView) findViewById(R.id.listview);
        iv_classify_back = (ImageView) findViewById(R.id.iv_classify_back);
        initView();

        //注册广播方法
        mReceiver = new SchoolClassifyActivity.LoginBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("school");
        LocalBroadcastManager.getInstance(MyApplication.getInstance()).registerReceiver(mReceiver,intentFilter);
    }

    /**
     * 自定义广播接受器,用来处理登录广播
     */
    private class LoginBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("school")) {
                ClassifyOne.ListBean item = (ClassifyOne.ListBean) intent.getSerializableExtra("item");
                //处理我们具体的逻辑,更新UI
                SharedPreferencesHelper helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user");
                helper.put("deptId", item.getDept_id());
                finish();

            }
        }
    }
    /**
     * 初始化view
     */
    private void initView() {
        iv_classify_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LoginModel.getInstance().schoolClassifyOne("",new ICallBack() {
            @Override
            public void succeed(Object object) {
                oneList = (ClassifyOne) object;
                //默认
                oneList.getList().get(0).setChick(true);
                adapter = new ClassifyAdapter(SchoolClassifyActivity.this, oneList.getList());
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(SchoolClassifyActivity.this);
                //创建MyFragment对象
                myFragment = new ClassifyFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, myFragment);

                Bundle mBundle=new Bundle();
                mBundle.putSerializable("info", (Serializable) oneList.getList().get(0));
                myFragment.setArguments(mBundle);
                fragmentTransaction.commit();

            }
            @Override
            public void error(Object object) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ClassifyOne.ListBean info = oneList.getList().get(position);
        for (int i = 0; i < oneList.getList().size(); i++) {
            if(oneList.getList().get(i).getDept_name().equals(info.getDept_name())){
                oneList.getList().get(i).setChick(true);
            }else {
                oneList.getList().get(i).setChick(false);
            }
        }

        adapter.notifyDataSetChanged();


        //创建MyFragment对象
        myFragment = new ClassifyFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragment);

        Bundle mBundle=new Bundle();
        mBundle.putSerializable("info", (Serializable) oneList.getList().get(position));
        myFragment.setArguments(mBundle);
        fragmentTransaction.commit();
    }
}
