package com.wzx.studyhelper.common.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.hjq.toast.ToastUtils;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;

public class TestActivity extends BaseActivity {


//    private SpeechRecognizer mIat;
    private RecognizerDialog mIatDialog;
    private Button mBtnGo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        mBtnGo = ((Button) findViewById(R.id.btn_mybtn));

        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIatDialog.show();
            }
        });


        mIatDialog = new RecognizerDialog(this, new InitListener() {
            @Override
            public void onInit(int i) {

            }
        });
        mIatDialog.setCanceledOnTouchOutside(false);
        mIatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        mIatDialog.setParameter( SpeechConstant.ENGINE_TYPE,"cloud");
        mIatDialog.setParameter(SpeechConstant.RESULT_TYPE, "plain");
        mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIatDialog.setParameter(SpeechConstant.VAD_BOS, "4000");
        mIatDialog.setParameter(SpeechConstant.ASR_PTT,"1");
        mIatDialog.setParameter(SpeechConstant.VAD_EOS, "1000");
        mIatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                String resultString = recognizerResult.getResultString();
                ToastUtils.show(resultString);
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIatDialog.create();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( null != mIatDialog ){
            // 退出时释放连接
            mIatDialog.cancel();
            mIatDialog.destroy();
        }

    }
}
