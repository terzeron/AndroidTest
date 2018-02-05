package net.terzeron.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale.filter

/**
 * Created by terzeron on 2018. 1. 29..
 */
class LambdaTest12 {
    fun filter(list: List<Int>, f: (Int) -> Boolean): List<Int> {
        val result = mutableListOf<Int>()
        for (value in list) {
            if (f(value)) result.add(value)
        }
        return result
    }

    @Test
    fun testLambda() {
        val list = listOf(1, 2, 3, 4, 5)
        assertEquals(listOf(2, 4), filter(list, { x -> x % 2 == 0 }))
        assertEquals(listOf(1, 3, 5), filter(list) { it % 2 != 0 })
    }
}