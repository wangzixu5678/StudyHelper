package com.wzx.studyhelper.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wzx.studyhelper.R;


/**
 * Created by wzx on 2018/8/16.
 */

public class LoadingPopWindown extends ProgressDialog {

    private Context mContext;
    private TextView mTvHint;
    private final View mView;

    public LoadingPopWindown(Context context) {
        super(context, R.style.loading);
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.loading_layout, null);
        ImageView imgLoading = (ImageView) mView.findViewById(R.id.img_loading);
        Glide.with(mContext).load(R.drawable.loading).asGif().into(imgLoading);
        mTvHint = ((TextView) mView.findViewById(R.id.tv_loading_hint));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(false);
        setContentView(mView);
    }

    public void setLoadingText(String content) {
        if (mTvHint!=null){
            mTvHint.setText(content);
        }
    }
}
