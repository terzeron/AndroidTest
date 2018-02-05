package net.terzeron.kotlin

import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */
class ExceptionTest9 {
    fun toInt1(s: String): Int {
        try {
            return s.toInt()
        } catch (e: Exception) {
            println("failed ${e.message}")
            throw e
        } finally {
            println("finished")
        }
    }

    fun toInt2(s: String): Int = try {
        s.toInt()
    } catch (e: Exception) {
        println("failed ${e}")
        throw e
    } finally {
        println("finished")
    }

    @Test
    fun testToInt() {
        println(toInt1("34"))
        println(toInt1("hello"))
        println(toInt2("56a"))
        println(toInt2("world"))
    }
}