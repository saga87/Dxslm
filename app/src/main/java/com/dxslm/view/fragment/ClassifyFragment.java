package com.dxslm.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dxslm.R;
import com.dxslm.adapter.ClassifyFragmentAdapter;
import com.dxslm.entity.ClassifyOne;
import com.dxslm.model.ICallBack;
import com.dxslm.model.LoginModel;
import com.dxslm.util.Util;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClassifyFragment extends Fragment {

    @Bind(R.id.recyclerview_fclassify)
    RecyclerView recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_classify, null);
        ButterKnife.bind(this, view);
        //得到数据
        ClassifyOne.ListBean info = (ClassifyOne.ListBean) getArguments().getSerializable("info");

        getModel(info);
        return view;
    }

    private void getModel(ClassifyOne.ListBean info) {
        if(info==null){
            Util.showToast("数据传送失败");
            return;
        }
        LoginModel.getInstance().schoolClassifyOne(info.getDept_id(),new ICallBack() {
            @Override
            public void succeed(Object object) {
                ClassifyOne twoList = (ClassifyOne) object;
                recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                ClassifyFragmentAdapter adapter = new ClassifyFragmentAdapter(getActivity(),twoList.getList());
                recyclerview.setAdapter(adapter);
            }
            @Override
            public void error(Object object) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
