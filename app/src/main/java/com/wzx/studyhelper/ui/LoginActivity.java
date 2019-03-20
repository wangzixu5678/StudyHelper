package com.wzx.studyhelper.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.githang.statusbar.StatusBarCompat;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.http.HttpManager;
import com.wzx.studyhelper.http.ResponseCallback;
import com.wzx.studyhelper.utils.SkipUtils;
import com.wzx.studyhelper.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements TextWatcher {


    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initIntent() {
        mEtPhone.addTextChangedListener(this);
        mEtPassword.addTextChangedListener(this);
    }

    @Override
    protected void initCircle() {
        setCustomTitle(null, "登录", null);
    }




    @OnClick({R.id.btn_login,R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                SkipUtils.goRegisterAct(this);
                break;
            case R.id.btn_login:
                HttpManager.getInstance().login(this, StringUtil.judgeString(mEtPhone.getText().toString()), mEtPassword.getText().toString(), new ResponseCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ToastUtils.show("登录成功");
                        SkipUtils.goHomeAct(getContext());
                    }
                });
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEtPhone.length()>0&&mEtPassword.length()>0){
            mBtnLogin.setBackgroundResource(R.drawable.btn_theme_bg);
            mBtnLogin.setEnabled(true);
        }else {
            mBtnLogin.setEnabled(false);
            mBtnLogin.setBackgroundResource(R.drawable.btn_gray_bg);
        }
    }
}
