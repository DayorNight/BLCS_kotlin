package com.blcs.comlibs.common

import android.app.Activity
import android.content.Context
import android.view.WindowManager

/**
 * 界面
 */
object LinUI {
    fun setFullScreen(context: Activity){
        //去掉窗口标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        隐藏顶部状态栏
        context.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}