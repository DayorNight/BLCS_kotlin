package com.blcs.comlibs.base

import android.app.Application
import android.content.Context
import android.os.Process
import com.blcs.comlibs.BuildConfig
import com.blcs.comlibs.common.*
import com.blcs.comlibs.crash.Cockroach
import com.blcs.comlibs.manage.MrAppBlockCanary
import com.blcs.comlibs.manage.MrBugly
import com.github.moduth.blockcanary.BlockCanary
import com.tencent.bugly.Bugly
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV

open class BaseApp : Application() {
    companion object {
        var appContext: Context? = null
        fun getContext(): Context {
            return appContext!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        /**
         * 异常捕获上传
         */
        Cockroach.init(this, null)
        /**
         * 卡顿初始化
         */
        BlockCanary.install(this, MrAppBlockCanary()).start()
        /**
         * MMKV
         */
        val rootDir: String = MMKV.initialize(this)
        Lg.e( "onCreate: $rootDir")
        /**
         * bugly
         */
        initBugly()
    }

    private fun initBugly() {
        val processName = LinApp.getProcessName(Process.myPid())
        val strategy = CrashReport.UserStrategy(appContext)
        strategy.isUploadProcess =( processName == null || processName == LinApp.getAppPackageName(this))
        strategy.appChannel =LinApp.getChannel(this)  //设置渠道
        strategy.appVersion=LinApp.getVersionName(this)    //App的版本
        strategy.appPackageName=LinApp.getAppPackageName(this) //App的包名
        MrBugly.autoCheckUpgrade(false)
        MrBugly.autoDownloadOnWifi(true)
        MrBugly.initDelay(5000)
        /* 升级 */
        Bugly.init(this, constants.Bugly_AppID, BuildConfig.DEBUG,strategy)
        /**
         * 监听H5 异常 建议在WebChromeClient的onProgressChanged函数中调用接口：
         * autoInject 是否自动注入Bugly.js文件
         */
//        webView.setWebChromeClient(object : WebChromeClient() {
//            override fun onProgressChanged(webView: WebView, progress: Int) {
//                // 增加Javascript异常监控
//                CrashReport.setJavascriptMonitor(webView, true)
//                super.onProgressChanged(webView, progress)
//            }
//        })
    }

//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(MyApplication.context)
//        Multidex.install(this)
//    }
}