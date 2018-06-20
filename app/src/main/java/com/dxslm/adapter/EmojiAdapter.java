package com.dxslm.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dxslm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class EmojiAdapter extends PagerAdapter{
    private Context mContext;
    private EditText mMessageEditText;
    private ViewPager mEmojiViewPager;
    private LinearLayout mPointContain;
    private List<Integer> emojiList = new ArrayList<Integer>();
    private List<GridView> mGridViewList = new ArrayList<GridView>();
    private int totalPage;
    private SparseArray<String> mSparseArray = new SparseArray<String>();
    private List<View> pointViewList = new ArrayList<View>();

    public EmojiAdapter(Context context, EditText editText, ViewPager mEmojiViewPager, LinearLayout linearLayout) {
        this.mContext = context;
        this.mMessageEditText = editText;
        this.mEmojiViewPager = mEmojiViewPager;
        this.mPointContain = linearLayout;
        initEmoji();
        computeGridView();
        initPoint();
        mEmojiViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < pointViewList.size(); i++) {
                    if (position == i) {
                        pointViewList.get(i).setBackgroundResource(R.drawable.point_select);
                    } else {
                        pointViewList.get(i).setBackgroundResource(R.drawable.point);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化小点
     */
    private void initPoint() {
        if (mPointContain!=null){
            mPointContain.removeAllViews();
        }
        for (int i = 0; i < totalPage; i++) {
            View view = new View(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = dp2px(8);
            params.width = dp2px(10);
            params.height = dp2px(10);
            if (i == 0) {
                view.setBackgroundResource(R.drawable.point_select);
            } else {
                view.setBackgroundResource(R.drawable.point);
            }
            view.setLayoutParams(params);
            mPointContain.addView(view);
            pointViewList.add(view);
            //打了个log看看他们的循环次数
          //  Log.e("++++++++++",i+""+"77777");

        }

    }

    //初始化表情
    private void initEmoji() {
        for (int i = 1; i <= 117; i++) {
            String drawableName;
            if (i < 10) {
                drawableName = "emoji_0" + i;
            } else {
                drawableName = "emoji_" + i;
            }
            int drawableId = getDrawableId(drawableName);
            mSparseArray.put(drawableId, drawableName);
            emojiList.add(drawableId);
        }
    }

    private void computeGridView() {
        totalPage = (int) (emojiList.size() / 20f + 0.5f);//计算装表情的总的页数
//        Log.e("399", "totalPage  " + totalPage);
        for (int i = 0; i < totalPage; i++) {

            GridView gridView = (GridView) View.inflate(mContext, R.layout.gridview, null);
            int currentPage = i;
            gridView.setAdapter(new EmojiGridViewAdapter(currentPage));
            mGridViewList.add(gridView);
        }


    }

    @Override
    public int getCount() {
        if (mGridViewList != null && mGridViewList.size() > 0) {
            return mGridViewList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(mGridViewList.get(position));
        return mGridViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mGridViewList.get(position));
    }


    private int getDrawableId(String drawableName) {
        return mContext.getResources().getIdentifier(drawableName, "drawable", mContext.getPackageName());
    }


    /**
     * 表情适配器
     */
    class EmojiGridViewAdapter extends BaseAdapter {

        int currentPage = 0;

        public EmojiGridViewAdapter(int currentPage) {
            this.currentPage = currentPage;

        }

        @Override
        public int getCount() {
            if (currentPage == totalPage - 1) {//最后一页
                return emojiList.size() - (totalPage - 1) * 20 + 1;//加上最后一个删除表情
            }
            return 21;
        }

        @Override
        public Object getItem(int i) {
            return emojiList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = View.inflate(mContext, R.layout.item_emoji, null);
                holder.mEmj = (ImageView) view.findViewById(R.id.iv_emj);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            i = currentPage * 20 + i;//表情的索引
            final int j = i;

            ImageView mEmjImageView = holder.mEmj;
            int delPos = 20 * currentPage + 20;//每页的最后一个的位置
            if (i == delPos || i == 117) {//如果位置是每一页的最后一个或者最后一页的最后一个表情的话就插入删除表情
                mEmjImageView.setImageResource(R.drawable.face_del_ico_dafeult);
            } else {
                mEmjImageView.setImageResource(emojiList.get(i));
            }
            mEmjImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (20 * currentPage + 20 == j || j == 117) {//点击的是删除表情
                        doDelete();
                    } else {
                        String enojiName = mSparseArray.get(emojiList.get(j));
//                    mMessageEditText.setText(enojiName);
                        enojiName = "[" + enojiName + "]";// [emoji_01]
                        //将表情包使用SpannableStringBuilder包装，并显示到EditText中
                        putEmojiToEditText(enojiName, j);
                    }
                }
            });
            return view;
        }
    }

    /**
     * 删除表情或者文字
     */
    private void doDelete() {
//        Log.e("399", "删除");
        int selectionStart = mMessageEditText.getSelectionStart();// 获取光标的位置
        if (selectionStart > 0) {
            String body = mMessageEditText.getText().toString();
            if (!TextUtils.isEmpty(body)) {
                String tempStr = body.substring(0, selectionStart);
                String lastString = tempStr.substring(selectionStart - 1);//获取最后一个字符串
                if("]".equals(lastString)) {//判断最后一个字符串是不是"]"
                    int i = tempStr.lastIndexOf("[");// 获取最后一个表情的位置
                    if (i != -1) {
                        CharSequence cs = tempStr
                                .subSequence(i, selectionStart);
                        if (cs.toString().contains("[emoji_")) {// 判断是否是一个表情
                            mMessageEditText.getEditableText().delete(i, selectionStart);
                            return;
                        }
                    }
                }else {
                    mMessageEditText.getEditableText().delete(tempStr.length() - 1,
                            selectionStart);
                }
            }
        }
    }

    private void putEmojiToEditText(String enojiName, int j) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(enojiName);
        //获取表情的drawable
        Drawable drawable = mContext.getResources().getDrawable(emojiList.get(j));
        //屏幕适配，这种宽高为35dp
        int size = (int) (35 * mContext.getResources().getDisplayMetrics().density);
        drawable.setBounds(0, 0, size, size);
        //将图片使用ImageSpan包装起来
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableStringBuilder.setSpan(imageSpan, 0, enojiName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Editable editable = mMessageEditText.getText();
        int selectionEnd = mMessageEditText.getSelectionEnd();
        if (selectionEnd < editable.length()) {
            editable.insert(selectionEnd, spannableStringBuilder);
        } else {
            editable.append(spannableStringBuilder);
        }
    }

    static class ViewHolder {
        ImageView mEmj;
    }

    private int dp2px(int dp) {
        return (int) (dp * mContext.getResources().getDisplayMetrics().density);
    }
}
