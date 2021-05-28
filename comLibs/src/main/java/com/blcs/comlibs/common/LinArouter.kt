package com.blcs.comlibs.common

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.R
import com.blcs.comlibs.common.LinArouter.getArouter


object LinArouter {
    fun getArouter(url: String): Postcard = ARouter.getInstance().build(url)
        .withTransition(R.anim.alpha_push_in, R.anim.alpha_push_out)

}

/**
 * 跳转Activity
 * @param url "/main/MainActivity"
 */
fun Activity.toActivity(url: String) {
    getArouter(url).navigation()
}

fun Fragment.toActivity(url: String) {
    getArouter(url).navigation()
}

/**
 * 跳转Activity 携带参数
 */
fun Activity.toActivity(url: String, bundle: Bundle) {
    getArouter(url).withBundle("bundle", bundle)
        .navigation()
}

fun Fragment.toFragment(fragmentTag: String) {
    getArouter(ConstKey.Activit_Public).withString(ConstKey.WHERE, fragmentTag)
        .navigation()
}


