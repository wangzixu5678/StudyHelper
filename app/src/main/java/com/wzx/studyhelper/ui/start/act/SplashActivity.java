package com.wzx.studyhelper.ui.start.act;

import com.githang.statusbar.StatusBarCompat;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.base.BaseActivity;
import com.wzx.studyhelper.utils.Constants;
import com.wzx.studyhelper.utils.SharedPreferencesUtil;
import com.wzx.studyhelper.utils.SkipUtils;
import com.wzx.studyhelper.utils.StringUtil;

import butterknife.BindView;
import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.pv_logo)
    ParticleView mPvLogo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initCircle() {
        mPvLogo.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {

                if (StringUtil.isEmpty(SharedPreferencesUtil.getInstance().getString(Constants.USER_ID))){
                    SkipUtils.goLoginAct(getContext());
                }else {
                    SkipUtils.goHomeAct(getContext());
                }


                finish();
            }
        });
        mPvLogo.startAnim();
    }

    @Override
    protected void setStatusBar() {
        StatusBarCompat.setTranslucent(getWindow(), true);
    }


}
