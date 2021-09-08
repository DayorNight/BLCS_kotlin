package com.blcs.mainmodule

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
        val single = Single()
        println(single.getName())
        println(single.getAge())
        single.change()
        println(single.getName())
        println(single.getAge())


        val arrayOfs = arrayOf("1", "2", "3", "4")
        for (item in arrayOfs){
            println(item)
        }
    }
}