package com.dxslm.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxslm.R;
import com.dxslm.adapter.PictureAdapter;
import com.dxslm.adapter.SmallImageAdapter;
import com.dxslm.model.ICallBack;
import com.dxslm.model.MyModel;
import com.dxslm.ui.GridViewForScrollview;
import com.dxslm.util.Util;
import com.dxslm.view.activity.OtherPersonActivity;
import com.dxslm.view.activity.PhotoActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherCameraFragment extends Fragment {
    @Bind(R.id.other_xc_view)
    GridViewForScrollview otherXcView;
    private String userId;
    private View view;

    public OtherCameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_other_camera, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        MyModel.getInstance().picList(userId,new ICallBack() {
            public void succeed(Object object) {
                List<String> imgs = (List<String>) object;
                SmallImageAdapter adapter = new SmallImageAdapter(imgs,getActivity());
                otherXcView.setAdapter(adapter);
            }
            public void error(Object object) {
                Util.showToast("数据获取失败");
            }
        });
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        userId = ((OtherPersonActivity) context).getUserId();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
