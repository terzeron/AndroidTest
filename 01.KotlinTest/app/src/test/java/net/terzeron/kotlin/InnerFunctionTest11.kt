package net.terzeron.kotlin

import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */
class InnerFunctionTest11 {
    @Test
    fun testInnerFunction() {
        fun checkLength(s: String) = s.length > 3

        if (checkLength("ASDE")) println("true")
        else println("false")
    }
}