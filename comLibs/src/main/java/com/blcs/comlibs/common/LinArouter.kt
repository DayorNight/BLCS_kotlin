package com.blcs.comlibs.common

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.R

object LinArouter {
    /**
     * 跳转Activity
     * @param url "/main/MainActivity"
     */
    fun toActivity(url: String) {
        ARouter.getInstance().build(url).withTransition(R.anim.left_push_in, R.anim.right_push_out).navigation()
    }

    /**
     * 跳转Activity
     */
    fun toActivity(url: String, bundle: Bundle) {
        ARouter.getInstance().build(url)
            .withTransition(R.anim.left_push_in, R.anim.right_push_out)
            .with(bundle)
            .navigation()
    }

}