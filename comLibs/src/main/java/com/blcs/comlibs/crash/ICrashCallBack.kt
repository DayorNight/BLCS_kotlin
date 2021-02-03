package com.blcs.comlibs.crash

import android.content.Context

interface ICrashCallBack {
    fun uncaughtException(ctx: Context?, t: Thread?, e: Throwable?)
}