package com.wzx.studyhelper.utils.glide;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.wzx.studyhelper.R;

/**
 * Created by wzx on 2018/6/25.
 */

public class GlideDoMain {
    public GlideDoMain() {
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideDoMain INSTANCE = new GlideDoMain();
    }

    public static GlideDoMain getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }


    public void loadGif(Context context, int source, ImageView imageView){
        if (context==null){
            return;
        }
        Glide.with(context).load(source).asGif().into(imageView);
    }


    public void loadImage(Context context, String source, ImageView imageView) {
        if (context==null){
            return;
        }
        Glide.with(context)
                .load(source)
                .transform(new CenterCrop(context))
                .placeholder(Color.parseColor("#d8d8d8"))
                .dontAnimate()
                .into(imageView);
    }

    public void loadRoundImage(Context context, String source, ImageView imageView) {
        if (context==null){
            return;
        }
        Glide.with(context)
                .load(source)
                .placeholder(Color.parseColor("#d8d8d8"))
                .dontAnimate()
                .transform(new GlideRoundTransformCenterCrop(context))
                .into(imageView);
    }


    /**
     * 加载圆角图片
     * @param context  上下文对象
     * @param source  图片资源链接
     * @param imageView 图片ImageView
     * @param round 圆角度
     */
    public void loadRoundImage(Context context, String source, ImageView imageView, int round) {
        if (context==null){
            return;
        }
        Glide.with(context)
                .load(source)
                .placeholder(Color.parseColor("#d8d8d8"))
                .transform(new GlideRoundTransformCenterCrop(context, round))
                .dontAnimate()
                .into(imageView);
    }

    public void loadRoundImageNoPlaceHolder(Context context, String source, ImageView imageView) {
        if (context==null){
            return;
        }
        Glide.with(context)
                .load(source)
                .thumbnail(0.3f)
                .dontAnimate()
                .into(imageView);
    }

    public void loadRoundImageSkipCache(Context context, String source, ImageView imageView) {
        if (context==null){
            return;
        }
        Glide.with(context)
                .load(source)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(Color.parseColor("#d8d8d8"))
                .transform(new GlideRoundTransformCenterCrop(context))
                .dontAnimate()
                .into(imageView);
    }


    public void loadCircleImageSkipCache(Context context, String source, ImageView imageView) {
        if (context==null){
            return;
        }
        Glide.with(context)
                .load(source)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(Color.parseColor("#d8d8d8"))
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    public void loadCircleImage(Context context, String source, ImageView imageView) {
        if (context==null){
            return;
        }
        Glide.with(context)
                .load(source)
                .placeholder(Color.parseColor("#d8d8d8"))
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }
}
