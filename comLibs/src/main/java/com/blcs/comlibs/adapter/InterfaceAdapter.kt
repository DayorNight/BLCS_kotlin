package com.blcs.comlibs.adapter

import android.annotation.TargetApi
import android.os.Build
import android.view.View

/**
 * @Des 当监听器有多个方法时，必须将它拆分为多个监听器
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
interface OnViewDetachedFromWindow {
    fun onViewDetachedFromWindow(v: View)
}

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
interface OnViewAttachedToWindow {
    fun onViewAttachedToWindow(v: View)
}

