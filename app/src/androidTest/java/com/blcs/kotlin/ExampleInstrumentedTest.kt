package com.blcs.kotlin

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.blcs.comlibs.common.LinMMKV

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val put = LinMMKV.put("111", "我是1")
        println("====1 $put")

        val sss = LinMMKV.get("111", "")
        println("====2 $sss")

        val xxx = LinMMKV.put("2222", true)
        println("====3 $xxx")

        val qqq = LinMMKV.get("2222", false)
        println("====4 $qqq")
    }

}