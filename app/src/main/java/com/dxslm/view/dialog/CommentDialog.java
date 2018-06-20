package com.dxslm.view.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dxslm.R;
import com.dxslm.adapter.EmojiAdapter;
import com.dxslm.model.DialogCallback;
import com.dxslm.model.ICallBack;
import com.dxslm.util.Util;


/**
 * Created by fxn on 2017/10/13.
 */

public class CommentDialog extends Dialog{
    private Context context;
    private String username;
    private DialogCallback callBack;

    //可以看到两个构造器，想自定义样式的就用第二个啦
    public CommentDialog(Context context, String username, DialogCallback callBack) {
        super(context);
        this.context = context;
        this.username = username;
        this.callBack = callBack;
    }

    public CommentDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    //控件的声明
    EditText dialog_et_context;
    Button dialog_btn_confirm;
    private ImageButton mFaceImageButton;

    private ViewPager mEmojiViewPager;
    private LinearLayout mPointContain;
    private RelativeLayout mFaceContainRelativeLayout;
    private boolean isFaceShow = false;
    private void init() {
        //以view的方式引入，然后回调activity方法，setContentView，实现自定义布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null);
        setContentView(view);
        //radiobutton的初始化
        dialog_et_context = view.findViewById(R.id.dialog_et_context);
        dialog_btn_confirm =  view.findViewById(R.id.dialog_btn_confirm);
        mFaceImageButton = view.findViewById(R.id.ib_face_huifu);
        mEmojiViewPager = view.findViewById(R.id.vp_emoji);
        mPointContain = view.findViewById(R.id.ll_point_contain);
        mFaceContainRelativeLayout = view.findViewById(R.id.rl_face_contain);
        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.BOTTOM);//设置对话框位置
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
//        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        params.width = (int) (d.getWidth());
        dialogWindow.setAttributes(params);
        setListener();
    }

    private void setListener() {
        dialog_et_context.setHint("回复"+username);
        dialog_btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.succeed(dialog_et_context.getText().toString());
            }
        });
        this.setOnShowListener(new OnShowListener(){
            public void onShow(DialogInterface dialog){
                InputMethodManager imm= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(dialog_et_context,InputMethodManager.SHOW_IMPLICIT);
            }
        });
        mFaceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmojiViewPager.setAdapter(new EmojiAdapter(context, dialog_et_context, mEmojiViewPager, mPointContain));
                if (isFaceShow) {
                    mFaceContainRelativeLayout.setVisibility(View.GONE);
                } else {
                    mFaceContainRelativeLayout.setVisibility(View.VISIBLE);

                }
                isFaceShow = !isFaceShow;
            }
        });
    }
}
