package com.blcs.kotlin.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.LinArouter
import com.blcs.kotlin.R
import com.blcs.kotlin.common.Const
import com.blcs.kotlin.databinding.ActivitySplashBinding

@Route(path = Const.Activit_Splash)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun setLayoutId() = R.layout.activity_splash

    override fun initUI(binding: ActivitySplashBinding) {
        binding.root.postDelayed({
            LinArouter.toActivity(Const.Activit_Main)
            finish()
        }, 3000)
    }
}

