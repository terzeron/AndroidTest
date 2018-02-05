package net.terzeron.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by terzeron on 2018. 1. 29..
 */
class WhenTest3 {
    @Test
    fun testWhen() {
        val obj: Any = "string object"
        when (obj) {
            is String -> println("string type = ${obj.length}")
            is Int -> println("int type = ${obj + 2}")
            else -> println("unknown")
        }

        val value: Int = 10
        val list: List<Int> = listOf(10, 20, 30)
        val message: String = when {
            value > 20 -> "greater than 20"
            value in list -> "in list"
            else -> "else"
        }
        print(message)

        assertEquals("in list", message)
    }
}
