package com.dxslm.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.entity.user.PersonMessage;
import com.dxslm.model.ICallBack;
import com.dxslm.model.OtherModel;
import com.dxslm.util.GlideUtil;
import com.dxslm.util.UrlUtil;
import com.dxslm.view.activity.OtherPersonActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherMessageFragment extends Fragment {
    @Bind(R.id.et_otheruser)
    TextView etOtheruser;
    @Bind(R.id.et_otheruser_mobile)
    TextView etOtheruserMobile;
    @Bind(R.id.et_otheruser_email)
    TextView etOtheruserEmail;
    private String userId;
    private View view;

    public OtherMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_other_message, container, false);
        ButterKnife.bind(this, view);
        setModel();
        return view;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        userId = ((OtherPersonActivity) context).getUserId();
    }


    private void setModel() {
        OtherModel.getInstance().getMessage(userId, new ICallBack() {
            @Override
            public void succeed(Object object) {
                PersonMessage personMessage = (PersonMessage) object;
                etOtheruser.setText(personMessage.getList().get(0).getUser_realname());
                etOtheruserMobile.setText(personMessage.getList().get(0).getUser_tel());
                etOtheruserEmail.setText(personMessage.getList().get(0).getUser_email());
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
