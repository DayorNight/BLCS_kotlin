package com.blcs.comlibs.common

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

/**
 * 界面
 */
var makeText:Toast? = null

fun Any.toast(context: Context?, duration: Int = Toast.LENGTH_SHORT): Toast? {
    makeText?.cancel()
    makeText = Toast.makeText(context, this.toString(), duration)
    return makeText?.apply { show() }
}