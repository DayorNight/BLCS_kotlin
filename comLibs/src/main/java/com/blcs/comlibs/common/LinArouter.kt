package com.blcs.comlibs.common

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.R

/**
 * Arouter 组件化工具
 */
object LinArouter {
    /**
     * 跳转Activity
     * @param url "/main/MainActivity"
     */
    fun toActivity(url: String) {
        ARouter.getInstance().build(url).navigation()
    }

    /**
     * 跳转Activity
     * @param url "/main/MainActivity"
     */
    fun toActivity(url: String,context: Context) {
        ARouter.getInstance().build(url).withTransition(R.anim.alpha_push_in,R.anim.alpha_push_out).navigation(context)
    }

    /**
     * 跳转Activity 携带参数
     */
    fun toActivity(url: String, bundle: Bundle) {
        ARouter.getInstance().build(url)
            .with(bundle)
            .navigation()
    }

}