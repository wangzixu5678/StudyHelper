package com.wzx.studyhelper.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.wzx.studyhelper.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

/**
 * 知乎图片选择器工具类
 */
public class ImgSelectUtil {


    public static void selectImg(final Activity activity,final int requestCode) {
        Matisse.from(activity)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.haidaxingyi.haixing.fileprovider"))
                .maxSelectable(1)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .showSingleMediaType(true)
                .imageEngine(new GlideEngine())
                .forResult(requestCode);
    }

}
