package com.blcs.kotlin.activity

import android.animation.Animator
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.Lg
import com.blcs.comlibs.common.LinArouter
import com.blcs.kotlin.R
import com.blcs.kotlin.common.Const
import com.blcs.kotlin.databinding.ActivitySplashBinding

@Route(path = Const.Activit_Splash)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun setFullScreen() = true

    override fun setLayoutId() = R.layout.activity_splash

    override fun initUI(binding: ActivitySplashBinding) {
        /* 动画监听 */
        binding.viewAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator) {
                LinArouter.toActivity(Const.Activit_Guide,this@SplashActivity)
            }
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        /* 动画监听 */

    }

}

