package com.blcs.comlibs.common

import android.util.Log
import com.blcs.comlibs.BuildConfig

object Lg {
    private const val TAG = "V1.0.0========"
    /*类名*/
    private var className : String? = null

    /*方法名*/
    private var methodName : String? = null

    /*行数*/
    private var lineNumber = 0

    /**
     * 判断是否可以调试
     */
    private val isDebuggable = BuildConfig.DEBUG

    private fun createLog(log: String): String {
        val buffer = StringBuffer()
        buffer.append(TAG).append(methodName)
        buffer.append("(").append(className).append(":").append(lineNumber).append(")==:")
        buffer.append(log)
        return buffer.toString()
    }

    /**
     * 获取文件名、方法名、所在行数
     * @param sElements
     */
    private fun getMethodNames(sElements: Array<StackTraceElement>) {
        className = sElements[1].fileName
        methodName = sElements[1].methodName
        lineNumber = sElements[1].lineNumber
    }

    fun e(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.e(className, createLog(message))
    }

    fun i(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.i(className, createLog(message))
    }

    fun d(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.d(className, createLog(message))
    }

    fun v(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.v(className, createLog(message))
    }

    fun w(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.w(className, createLog(message))
    }
}