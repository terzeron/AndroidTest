package net.terzeron.kotlin

import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */
class LoopTest8 {
    @Test
    fun testLoop() {
        val n = 4
        for (i in 1..n) {
            println("Hello")
        }

        for (i in 0 until n) {
            println("World")
        }

        for (i in n downTo 1 step 2) {
            println(i)
        }
    }
}