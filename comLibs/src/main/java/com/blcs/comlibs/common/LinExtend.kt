package com.blcs.comlibs.common

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


var makeText:Toast? = null

/**
 * 系统提示
 */
fun Any.toast(context: Context?, duration: Int = Toast.LENGTH_SHORT): Toast? {
    makeText?.cancel()
    makeText = Toast.makeText(context, this.toString(), duration)
    return makeText?.apply { show() }
}
/**
 * 底部提示
 */
var snackbar:Snackbar? = null
fun Any.toastBottom(view: View): Snackbar?{
     snackbar?.dismiss()
     snackbar = Snackbar.make(view, this.toString(), Snackbar.LENGTH_SHORT);
    return snackbar?.apply { show() }
}

/**
 * 获取Assets文件夹下的JSON数据
 */
fun String.getJson(context: Context?): String? {
    val stringBuilder = StringBuilder()
    val bf = BufferedReader(
        InputStreamReader(
            context?.assets?.open(this)
        )
    )
    var line: String?
    while (bf.readLine().also { line = it } != null) {
        stringBuilder.append(line)
    }
    return stringBuilder.toString()
}