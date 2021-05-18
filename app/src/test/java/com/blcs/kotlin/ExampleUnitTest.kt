package com.blcs.kotlin

import com.blcs.comlibs.common.LinMMKV
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
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