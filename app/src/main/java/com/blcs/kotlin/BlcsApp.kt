package com.blcs.kotlin

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.base.BaseApp

class BlcsApp : BaseApp() {

    override fun initSDK() {
        /* 组件化 */
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}