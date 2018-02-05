package net.terzeron.kotlin

import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */

class StringTemplateTest6 {
    @Test
    fun testStringTemplate() {
        val p: String = "Hello"
        val p2: String = "$p World ${20 + 30}"
        val multiple: String = """
        Hello
        World
        "using double quote"
        \no escaping
        | trim margin
    """.trimMargin()
        println(p)
        println(p2)
        println(multiple)
    }
}