package com.blcs.kotlin.activity

import android.animation.Animator
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.*
import com.blcs.kotlin.R
import com.blcs.kotlin.common.Const
import com.blcs.kotlin.databinding.ActivitySplashBinding

/**
 * 欢迎页
 */
@Route(path = Const.Activit_Splash)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun setFullScreen() = true

    override fun setLayoutId() = R.layout.activity_splash

    override fun initUI(binding: ActivitySplashBinding) {
        /* 动画监听 */
        binding.viewAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator) {
                binding.data = "跳过动画 0 "
                startNetActivity()
            }
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        //倒计时
        binding.viewAnimation.addAnimatorUpdateListener {
            val animatedValue = it.animatedValue as Float
            val time = animatedValue.times(10).div(2).toInt()
            val minus = 5.minus(time)
            binding.data = "跳过动画 $minus "
        }
    }

    /**
     * 前往下一页
     */
    private fun startNetActivity() {
        //判断是否已经进入过引导页
        val firstStart = LinMMKV.get(KEY_GUIDE, false) as Boolean
        if (firstStart){
            toActivity(Const.Activit_Login, this)
        }else{
            toActivity(Const.Activit_Guide, this)
        }
        finish()
    }

    /**
     * 点击事件
     */
    fun onClickView(view: View) {
        startNetActivity()
    }
}

