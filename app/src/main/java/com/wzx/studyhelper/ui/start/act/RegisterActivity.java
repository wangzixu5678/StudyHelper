package com.wzx.studyhelper.ui.start.act;

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
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.InputMethodManagerUtils;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements TextWatcher {


    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_password2)
    EditText mEtPassword2;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void setStatusBar() {
        StatusBarCompat.setTranslucent(getWindow(),true);
    }

    @Override
    protected void initCircle() {
        setBackTitle("注册",null);
        mEtName.addTextChangedListener(this);
        mEtPhone.addTextChangedListener(this);
        mEtPassword.addTextChangedListener(this);
        mEtPassword2.addTextChangedListener(this);
    }

    public void btnGo(View view) {

    }


    @OnClick(R.id.btn_register)
    public void onViewClicked(View view) {
        InputMethodManagerUtils.hideSoftInput(this,view);
        if (mEtPassword.getText().toString().equals(mEtPassword2.getText().toString())) {
            HttpManager.getInstance().register(this,
                    StringUtil.judgeString(mEtPhone.getText().toString()),
                    StringUtil.judgeString(mEtPassword.getText().toString()),
                    StringUtil.judgeString(mEtName.getText().toString()),
                    new ResponseCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            SharedPreferencesUtil.getInstance().putString(Constants.USER_PHONE,mEtPhone.getText().toString().trim());
                            ToastUtils.show("恭喜您，注册成功!");
                            finish();
                        }
                    });
        } else {
            ToastUtils.show("两次密码不一致，请检查后提交。");
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
        if (mEtName.length() > 0 && mEtPhone.length() > 0 && mEtPassword.length() > 0 && mEtPassword2.length() > 0) {
            mBtnRegister.setEnabled(true);
            mBtnRegister.setBackgroundResource(R.drawable.btn_theme_bg);
        } else {
            mBtnRegister.setEnabled(false);
            mBtnRegister.setBackgroundResource(R.drawable.btn_gray_bg);
        }
    }
}


