package com.dxslm.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.dxslm.MyApplication;
import com.dxslm.R;


/**
 * Created by fxn on 2017/12/12.
 */

public class GlideUtil {
    /**
     * gilde 获取图片
     * @return
     */
    public static void getBitmap(String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).load(url)
                .placeholder(R.drawable.dengdai)
                .error(R.drawable.error)
                .skipMemoryCache(false)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .dontAnimate()
                .into(imageView);
    }


    public static void getBitmapWH(String url, ImageView imageView,int width,int height) {
        Glide.with(MyApplication.getInstance()).load(url)
                .placeholder(R.drawable.dengdai)
                .error(R.drawable.error)
                .skipMemoryCache(false)
                .override(width, height)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .dontAnimate()
                .into(imageView);
    }


    /**
     * fitCenter形式
     */
    public static void getBitmapFitCenter(String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).load(url)
                .placeholder(R.drawable.dengdai)
                .error(R.drawable.error)
                .skipMemoryCache(false)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);

    }

    /**
     * 根据view调整高度
     * @param url
     * @param imageView
     */
    public static void getBitmapByWH(String url, final ImageView imageView) {

        Glide.with(MyApplication.getInstance()).load(url)
                .placeholder(R.drawable.dengdai)
                .error(R.drawable.error)
                .skipMemoryCache(false)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        int imageWidth = resource.getIntrinsicWidth();
                        int imageHeight = resource.getIntrinsicHeight();
                        int height = getScreenWidth(MyApplication.getInstance()) * imageHeight / imageWidth;
                        ViewGroup.LayoutParams para = imageView.getLayoutParams();
                        para.height = height;
                        para.width = getScreenWidth(MyApplication.getInstance());
                        imageView.setLayoutParams(para);
                        return false;
                    }
                })
                .into(imageView);
    }


//    new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//        @Override
//        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//            int imageWidth = resource.getWidth();
//            int imageHeight = resource.getHeight();
//            int height = getScreenWidth(MyApplication.getInstance()) * imageHeight / imageWidth;
//            ViewGroup.LayoutParams para = imageView.getLayoutParams();
//            para.height = height;
//            para.width = getScreenWidth(MyApplication.getInstance());
//            imageView.setImageBitmap(resource);
//        }
//    }


    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }






    /**
     * gilde 获取头像图片
     * @return
     */
    public static void getIconBitmap(String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).load(url)
                .error(R.drawable.error)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }


    /**
     * gilde 获取头像图片
     * @return
     */
    public static void getDefaultIconBitmap(int url, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).load(url)
                .error(R.drawable.error)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }




}
