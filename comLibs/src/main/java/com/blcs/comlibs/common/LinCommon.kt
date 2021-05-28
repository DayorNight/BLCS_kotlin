package com.blcs.comlibs.common

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log

object LinCommon {
    /**
     * 复制文字
     */
    fun copyText(context: Context?, value: String) {
        val cD = ClipData.newPlainText("CopyText", value)
        val clipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.setPrimaryClip(cD)
        "已复制".toast(context)
    }
}