package com.blcs.comlibs.crash

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import me.weishu.reflection.Reflection

/**
 * 应用闪退crash 处理
 */
class Cockroach private constructor(ctx: Context, callBack: ICrashCallBack?) {
    /**
     * 替换ActivityThread.mH.mCallback，实现拦截Activity生命周期，直接忽略生命周期的异常的话会导致黑屏，目前
     * 会调用ActivityManager的finishActivity结束掉生命周期抛出异常的Activity
     */
    private fun initActivityKiller() {
        //各版本android的ActivityManager获取方式，finishActivity的参数，token(binder对象)的获取不一样
        if (Build.VERSION.SDK_INT >= 28) {
            sActivityKiller = ActivityKillerV28()
        } else if (Build.VERSION.SDK_INT >= 26) {
            sActivityKiller = ActivityKillerV26()
        } else if (Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24) {
            sActivityKiller = ActivityKillerV24_V25()
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23) {
            sActivityKiller = ActivityKillerV21_V23()
        } else if (Build.VERSION.SDK_INT >= 15 && Build.VERSION.SDK_INT <= 20) {
            sActivityKiller = ActivityKillerV15_V20()
        } else if (Build.VERSION.SDK_INT < 15) {
            sActivityKiller = ActivityKillerV15_V20()
        }
        try {
            val activityThreadClass = Class.forName("android.app.ActivityThread")
            val activityThread =
                activityThreadClass.getDeclaredMethod("currentActivityThread").invoke(null)
            val mhField = activityThreadClass.getDeclaredField("mH")
            mhField.isAccessible = true
            val mhHandler = mhField[activityThread] as Handler
            val callbackField = Handler::class.java.getDeclaredField("mCallback")
            callbackField.isAccessible = true
            callbackField[mhHandler] = getValue(mhHandler)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "initActivityKiller: " + e.stackTrace)
        }
    }

    private fun getValue(mhHandler: Handler): Handler.Callback {
        return Handler.Callback { msg: Message ->
            Log.e(TAG, "handleMessage: $msg")
            Log.e(TAG, "handleMessage: " + msg.what)
            try {
                mhHandler.handleMessage(msg)
            } catch (throwable: Throwable) {
                when (msg.what) {
                    EXECUTE_TRANSACTION, LAUNCH_ACTIVITY -> sActivityKiller!!.finishLaunchActivity(
                        msg
                    )
                    RESUME_ACTIVITY -> sActivityKiller!!.finishResumeActivity(msg)
                    PAUSE_ACTIVITY_FINISHING, PAUSE_ACTIVITY -> sActivityKiller!!.finishPauseActivity(
                        msg
                    )
                    STOP_ACTIVITY_HIDE -> sActivityKiller!!.finishStopActivity(msg)
                }
                throwable.printStackTrace()
                Log.e(TAG, "getValue: " + throwable.stackTrace)
            }
            true
        }
    }

    companion object {
        private var sActivityKiller: IActivityKiller? = null
        private const val TAG = "Cockroach"
        const val EXECUTE_TRANSACTION = 159
        const val LAUNCH_ACTIVITY = 100
        const val PAUSE_ACTIVITY = 101
        const val PAUSE_ACTIVITY_FINISHING = 102
        const val STOP_ACTIVITY_HIDE = 104
        const val RESUME_ACTIVITY = 107
        const val DESTROY_ACTIVITY = 109
        const val NEW_INTENT = 112
        const val RELAUNCH_ACTIVITY = 126
        private var cockroach: Cockroach? = null
        fun init(ctx: Context, crashHandler: ICrashCallBack?) {
            if (cockroach == null) {
                cockroach = Cockroach(ctx, crashHandler)
            }
        }
    }

    init {
        val iCrashCallBack = callBack ?: DefaultCrashCallBack()
        /*解除 android P 反射限制*/Reflection.unseal(ctx)
        initActivityKiller()
        Thread.setDefaultUncaughtExceptionHandler { thread: Thread, throwable: Throwable? ->
            iCrashCallBack.uncaughtException(ctx, thread, throwable)
            //主线程闪退抛出
            if (thread === Looper.getMainLooper().thread) {
                while (true) {
                    try {
                        //异常处理
                        Looper.loop()
                    } catch (exception: Exception) {
                        iCrashCallBack.uncaughtException(ctx, thread, exception)
                    }
                }
            }
        }
    }
}